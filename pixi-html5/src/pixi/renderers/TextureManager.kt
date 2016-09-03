package pixi.renderers

import pixi.Destroyable
import pixi.ScaleMode
import pixi.WrapMode
import pixi.gl.GLTexture
import pixi.textures.BaseTexture
import pixi.textures.Texture
import org.khronos.webgl.WebGLRenderingContext as GL

class TextureManager(renderer: WebGLRenderer) : Destroyable {
    override var isDestroyed = false
    val gl: GL = renderer.gl

    val _managedTextures = listOf<BaseTexture>()

    fun updateTexture(texture: Texture) = updateTexture(texture.baseTexture)

    fun updateTexture(texture: BaseTexture): GLTexture? {
        //TODO: look into hashmap there
        val source = texture.source
        source ?: return null
        var glTexture = texture._glTexture
        if (glTexture == null) {
            //TODO: add render textures;

//            val renderTarget = RenderTarget(this.gl, texture.width, texture.height, texture.scaleMode, texture.resolution)
//            renderTarget.resize(texture.width, texture.height)
//            texture._glRenderTargets[this.renderer.CONTEXT_UID] = renderTarget
//            glTexture = renderTarget.texture

            glTexture = GLTexture(gl)
            glTexture.premultiplyAlpha = true
            glTexture.upload(source)

            //TODO: put into hashmap there
            texture._glTexture = glTexture

            //TODO: events: destroy and update

//            texture.on('update', this.updateTexture, this);
//            texture.on('dispose', this.destroyTexture, this);

            if (texture.isPowerOfTwo) {
                if (texture.mipmap) {
                    glTexture.enableMipmap()
                }
                if (texture.wrapMode === WrapMode.CLAMP) {
                    glTexture.enableWrapClamp()
                } else if (texture.wrapMode === WrapMode.REPEAT) {
                    glTexture.enableWrapRepeat()
                } else {
                    glTexture.enableWrapMirrorRepeat()
                }
            } else {
                glTexture.enableWrapClamp()
            }

            if (texture.scaleMode === ScaleMode.NEAREST) {
                glTexture.enableNearestScaling()
            } else {
                glTexture.enableLinearScaling()
            }
        } else {
//            if(isRenderTexture)
//            {
//                texture._glRenderTargets[this.renderer.CONTEXT_UID].resize(texture.width, texture.height);
//            }

            glTexture.upload(source)
        }

        return glTexture
    }
}
