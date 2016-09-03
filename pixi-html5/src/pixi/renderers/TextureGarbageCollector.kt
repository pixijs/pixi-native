package pixi.renderers

import pixi.GcMode
import pixi.ScaleMode
import pixi.WrapMode
import pixi.gl.GLTexture
import pixi.textures.BaseTexture
import pixi.textures.Texture
import org.khronos.webgl.WebGLRenderingContext as GL

class TextureGarbageCollector(renderer: WebGLRenderer) {
    val gl: GL = renderer.gl

    val _managedTextures = listOf<BaseTexture>()

    var count = 0;
    var checkCount = 0;
    var maxIdle = 60 * 60;
    var checkCountMax = 60 * 10;

    var mode = GcMode.DEFAULT;

    fun update() {

    }
}
