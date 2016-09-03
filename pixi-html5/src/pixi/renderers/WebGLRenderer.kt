package pixi.renderers

import pixi.BlendMode
import pixi.Color
import org.khronos.webgl.WebGLRenderingContext as GL
import pixi.RendererType
import pixi.ScaleMode
import pixi.display.DisplayObject
import pixi.gl.VertexArrayObject
import pixi.math.Matrix
import pixi.observable.Transform
import pixi.sprite.webgl.SpriteRenderer
import pixi.textures.BaseTexture
import pixi.textures.RenderTexture
import pixi.textures.Texture
import pixi.utils.sayHello

class WebGLRenderer(width: Int = 800, height: Int = 600, options: RenderOptions = RenderOptions()) : SystemRenderer(width, height, options) {

    constructor(width: Int = 800, height: Int = 600, init: RenderOptions.() -> Unit) : this(width, height, renderOptions(init))

    override val type = RendererType.WEBGL

    val gl: GL = view.getContext("webgl") as GL

    val rootRenderTarget = RenderTarget(gl, width, height, ScaleMode.DEFAULT, this.resolution, true)

    var renderingToScreen = false

    var _lastObjectRendered: DisplayObject? = null

    init {
        sayHello(type.systemName)

        rootRenderTarget.clearColor = backgroundColor

        bindRenderTarget(rootRenderTarget, true)

        resize(width, height)
    }

    override fun render(displayObject: DisplayObject, renderTexture: RenderTexture?, clear: Boolean?, transform: Transform?, skipUpdateTransform: Boolean) {
        renderingToScreen = renderTexture == null

        spriteRenderer.onPrerender()
        //this.emit('prerender');
        //if (!this.gl || this.gl.isContextLost()) return
        if (renderingToScreen) {
            _lastObjectRendered = displayObject
        }

        if (!skipUpdateTransform) {
            displayObject.updateTransform()
        }

        bindRenderTexture(renderTexture, transform)

        currentRenderer.start()

        if (clear ?: clearBeforeRender) {
            _activeRenderTarget.clear()
        }

        displayObject.renderWebGL(this)

        currentRenderer.flush()

        textureGC.update()

        //this.emit('postrender');
    }

    fun setObjectRenderer(objectRenderer: ObjectRenderer) {
        if (currentRenderer == objectRenderer) {
            return;
        }

        currentRenderer.stop()
        currentRenderer = objectRenderer
        currentRenderer.start()
    }

    val emptyRenderer = ObjectRenderer(this)

    val spriteRenderer = SpriteRenderer(this)

    fun _initContext() {
        state.resetToDefault()

        spriteRenderer.onContextChange()
    }

    var currentRenderer: ObjectRenderer = spriteRenderer

    fun flush() {
        this.setObjectRenderer(emptyRenderer)
    }

    val textureManager = TextureManager(this);

    val textureGC = TextureGarbageCollector(this);

    fun bindRenderTexture(renderTexture: RenderTexture?, @Suppress("UNUSED_PARAMETER") transform: Transform? = null) {
        if (renderTexture != null) {
            throw NotImplementedError()
        }

        bindRenderTarget(rootRenderTarget)
    }

    var _activeRenderTarget: RenderTarget = rootRenderTarget

    var _activeShader: Shader? = null;

    fun bindRenderTarget(renderTarget: RenderTarget, force: Boolean = false) {
        if (!force && _activeRenderTarget == renderTarget) {
            return
        }

        this._activeRenderTarget = renderTarget
        renderTarget.activate()

        _activeShader?.uniforms?.projectionMatrix = renderTarget.projectionMatrix.toArray(true)

//        this.stencilManager.setMaskStack( renderTarget.stencilMaskStack );
    }

    fun bindShader(shader: Shader) {
        if (this._activeShader == shader) {
            return;
        }

        this._activeShader = shader;
        shader.bind();
        shader.uniforms.projectionMatrix = _activeRenderTarget.projectionMatrix.toArray(true);
    }

    fun bindTexture(texture: Texture, location: Int) = bindTexture(texture.baseTexture, location)

    var _activeTextureLocation = 0;
    var _activeTexture: BaseTexture? = null;

    fun bindTexture(texture: BaseTexture, location: Int = 0, force: Boolean = false) {
        if (force || _activeTextureLocation != location) {
            this._activeTextureLocation = location;
            gl.activeTexture(GL.TEXTURE0 + location);
        }

        _activeTexture = texture;

        //TODO: use map for it
        val _glTexture = texture._glTexture;
        if (_glTexture == null) {
            textureManager.updateTexture(texture);
        } else {
            texture.touched = textureGC.count;
            _glTexture.bind();
        }
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        rootRenderTarget.resize(width, height)
        if (_activeRenderTarget == rootRenderTarget) {
            rootRenderTarget.activate()
            _activeShader?.uniforms?.projectionMatrix = rootRenderTarget.projectionMatrix.toArray(true)
        }
    }

    fun setBlendMode(blendMode: BlendMode) = state.setBlendMode(blendMode)

    fun clear(clearColor: Color?) = _activeRenderTarget.clear(clearColor)

    fun setTransform(matrix: Matrix?) {
        _activeRenderTarget.transform = matrix
    }

    val state = WebGLState(gl)

    fun createVao(): VertexArrayObject = VertexArrayObject(gl, state.attribState)

    fun reset() {
        setObjectRenderer(emptyRenderer)
        _activeShader = null
        _activeRenderTarget = rootRenderTarget
        _activeTextureLocation = 9999
        _activeTexture = null

        rootRenderTarget.activate()

        state.resetToDefault()
    }

    fun destroyPlugins() {
        spriteRenderer.destroy()
    }

    override fun destroy(removeView: Boolean) {
        super.destroy()

        textureManager.destroy()

        destroyPlugins()

        gl.useProgram(null)
    }

    init {
        _initContext()
    }
}
