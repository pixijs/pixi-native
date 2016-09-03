package pixi.sprite.webgl

import pixi.BlendMode
import pixi.Destroyable
import pixi.gl.GLBuffer
import pixi.gl.VertexArrayObject
import pixi.math.BitTwiddle
import pixi.sprite.Sprite
import pixi.renderers.ObjectRenderer
import pixi.renderers.Shader
import pixi.renderers.WebGLRenderer
import pixi.textures.BaseTexture
import pixi.utils.checkMaxIfStatementsInShader
import org.khronos.webgl.WebGLRenderingContext as GL

class SpriteRenderer(renderer: WebGLRenderer) : ObjectRenderer(renderer), Destroyable {
    override var isDestroyed = false

    val vertSize = 5

    val vertByteSize = vertSize * 4

    val size = 4096

    val buffers = mutableListOf<BatchBuffer>()

    init {
        var i = 1
        while (i <= BitTwiddle.nextPow2(this.size)) {
            val numVertsTemp = i * 4 * this.vertByteSize
            buffers.add(BatchBuffer(numVertsTemp))
            i *= 2
        }
    }

    val indices = createIndicesForQuads(size)

    var shaders = arrayOf<Shader?>()

    var MAX_TEXTURES = 0

    var tick = 0

    var groups = Array<BatchGroup>(size, { i -> BatchGroup() })

    var vertexBuffers = mutableListOf<GLBuffer>()

    var vaoMax = 2

    var vaos = mutableListOf<VertexArrayObject>()

    var vao: VertexArrayObject? = null

    var sprites = mutableListOf<Sprite>()

    //TODO: make null GLBuffer for that case
    var indexBuffer: GLBuffer? = null

    fun render(obj: Sprite) {
        if (sprites.size >= size) {
            flush()
        }

        if (!obj.texture.valid) {
            return
        }

        sprites.add(obj)
    }

    fun onContextChange() {
        val gl = renderer.gl
        MAX_TEXTURES = Math.min(gl.getParameter(GL.MAX_TEXTURE_IMAGE_UNITS) as Int, SpriteRenderer.MAX_TEXTURES)
        MAX_TEXTURES = checkMaxIfStatementsInShader(MAX_TEXTURES, gl)
        shaders = Array(MAX_TEXTURES, { i -> null })
        shaders[0] = generateMultiTextureShader(renderer, 1)
        shaders[1] = generateMultiTextureShader(renderer, 2)
        indexBuffer = GLBuffer.Companion.createIndexBuffer(gl, indices, GL.STATIC_DRAW)

        vertexBuffers.clear()
        vaos.clear()

        for (i in 1..vaoMax) addOneMoreVao()

        vao = vaos[0]
    }

    private fun addOneMoreVao() {
        val gl = renderer.gl
        val shader = shaders[1]!!
        val vb = GLBuffer.createVertexBuffer(gl, null, GL.STREAM_DRAW)
        vertexBuffers.add(vb)

        // build the vao object that will render..
        vaos.add(renderer.createVao()
                .addIndex(indexBuffer!!)
                .addAttribute(vb, shader.attributes["aVertexPosition"]!!, GL.FLOAT, false, this.vertByteSize, 0)
                .addAttribute(vb, shader.attributes["aTextureCoord"]!!, GL.UNSIGNED_SHORT, true, this.vertByteSize, 2 * 4)
                .addAttribute(vb, shader.attributes["aColor"]!!, GL.UNSIGNED_BYTE, true, this.vertByteSize, 3 * 4)
                .addAttribute(vb, shader.attributes["aTextureId"]!!, GL.FLOAT, false, this.vertByteSize, 4 * 4))
    }

    var vertexCount = 0

    fun onPrerender() {
        vertexCount = 0
    }

    override fun flush() {
        val currentIndex = sprites.size
        if (currentIndex == 0) {
            return
        }

        val gl = renderer.gl

        val np2 = BitTwiddle.nextPow2(currentIndex)
        val log2 = BitTwiddle.log2(np2)
        val buffer = buffers[log2]
        val float32View = buffer.float32View
        val uint32View = buffer.uint32View

        var index = 0
        var groupCount = 1
        var textureCount = 1
        var currentGroup = groups[0]
        var blendMode = sprites[0].blendMode
        var currentTexture: BaseTexture? = null

        currentGroup.textureCount = 0
        currentGroup.textures.clear()
        currentGroup.start = 0
        currentGroup.blend = BlendMode.NORMAL

        tick++

        for (i in 0..currentIndex - 1) {
            val sprite = sprites[i]
            val nextTexture = sprite.texture.baseTexture
            if (blendMode != sprite.blendMode) {
                blendMode = sprite.blendMode
                currentTexture = null
                textureCount = MAX_TEXTURES
                tick++
            }

            if (currentTexture != nextTexture) {
                currentTexture = nextTexture

                if (nextTexture._enabled !== this.tick) {
                    if (textureCount === this.MAX_TEXTURES) {
                        this.tick++;

                        textureCount = 0;

                        currentGroup.size = i - currentGroup.start;

                        currentGroup = groups[groupCount++];
                        currentGroup.textureCount = 0;
                        currentGroup.blend = blendMode;
                        currentGroup.start = i;
                        currentGroup.textures.clear()
                    }

                    nextTexture._enabled = this.tick;
                    nextTexture._id = textureCount;

                    currentGroup.textures.add(nextTexture);
                    currentGroup.textureCount++
                    textureCount++;
                }
            }

            val vertexData = sprite.vertexData
            val tint = sprite.worldMulColor.rgbaInt
            val uvs = sprite.texture._uvs.uvsUint32
            val textureId = nextTexture._id * 1f

            if (this.renderer.roundPixels) {
                val resolution = this.renderer.resolution

                //xy
                float32View[index] = Math.floor(vertexData[0] * resolution) / resolution
                float32View[index + 1] = Math.floor(vertexData[1] * resolution) / resolution

                // xy
                float32View[index + 5] = Math.floor(vertexData[2] * resolution) / resolution
                float32View[index + 6] = Math.floor(vertexData[3] * resolution) / resolution

                // xy
                float32View[index + 10] = Math.floor(vertexData[4] * resolution) / resolution
                float32View[index + 11] = Math.floor(vertexData[5] * resolution) / resolution

                // xy
                float32View[index + 15] = Math.floor(vertexData[6] * resolution) / resolution
                float32View[index + 16] = Math.floor(vertexData[7] * resolution) / resolution

            } else {
                //xy
                float32View[index] = vertexData[0]
                float32View[index + 1] = vertexData[1]

                // xy
                float32View[index + 5] = vertexData[2]
                float32View[index + 6] = vertexData[3]

                // xy
                float32View[index + 10] = vertexData[4]
                float32View[index + 11] = vertexData[5]

                // xy
                float32View[index + 15] = vertexData[6]
                float32View[index + 16] = vertexData[7]
            }

            uint32View[index + 2] = uvs[0]
            uint32View[index + 7] = uvs[1]
            uint32View[index + 12] = uvs[2]
            uint32View[index + 17] = uvs[3]

            uint32View[index + 3] = tint
            uint32View[index + 8] = tint
            uint32View[index + 13] = tint
            uint32View[index + 18] = tint

            float32View[index + 4] = textureId
            float32View[index + 9] = textureId
            float32View[index + 14] = textureId
            float32View[index + 19] = textureId

            index += 20
        }

        currentGroup.size = currentIndex - currentGroup.start

        vertexCount++

        if (vaoMax <= vertexCount) {
            addOneMoreVao()
            vaoMax++
        }

        vertexBuffers[vertexCount].upload(buffer.vertices, 0)
        vao = vaos[vertexCount].bind()

        for (i in 0..groupCount - 1) {
            val group = groups[i]
            val groupTextureCount = group.textureCount
            var shader = shaders[groupTextureCount - 1]
            if (shader == null) {
                shader = generateMultiTextureShader(renderer, groupTextureCount)
                shaders[groupTextureCount - 1] = shader
            }

            renderer.bindShader(shader)
            for (j in 0..groupTextureCount - 1) {
                renderer.bindTexture(group.textures[j], j)
            }
            renderer.state.setBlendMode(group.blend)

            gl.drawElements(GL.TRIANGLES, group.size * 6, GL.UNSIGNED_SHORT, group.start * 6 * 2);
        }

        sprites.clear()
    }

    override fun start() {
        tick %= 1000
    }

    override fun stop() {
        flush()
        vao?.unbind()
    }

    override fun destroy() {
        super.destroy()
        for (i in 0..vaoMax - 1) {
            vertexBuffers[i].destroy()
            vaos[i].destroy()
        }

        indexBuffer?.destroy()

        for (i in 0..shaders.size - 1) {
            shaders[i]?.destroy()
        }
    }

    companion object {
        val BATCH_SIZE = 4096
        //TODO check for mobile device, then find that number, 2 for mobiles, 32 for desktop
        val MAX_TEXTURES = 32
    }
}
