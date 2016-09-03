package pixi.gl

import org.khronos.webgl.*
import org.w3c.dom.ArrayBufferView
import org.khronos.webgl.WebGLRenderingContext as GL

@native(name="PIXI.glCore.GLFramebuffer") public class GLFrameBuffer {
    constructor(gl: GL, width: Int = noImpl, height: Int = noImpl)

    val gl: GL
        get() = noImpl

    var framebuffer: WebGLFramebuffer?
        get() = noImpl
        set(value) = noImpl

    val stencil: WebGLRenderbuffer?
        get() = noImpl

    val texture: GLTexture?
        get() = noImpl

    val width: Int
        get() = noImpl

    val height: Int
        get() = noImpl

    fun enableTexture(texture: GLTexture = noImpl): Unit = noImpl
    fun enableStencil(): Unit = noImpl
    fun clear(r: Float, g: Float, b: Float, a: Float): Unit = noImpl
    fun bind(): Unit = noImpl
    fun unbind(): Unit = noImpl
    fun resize(width: Int, height: Int): Unit = noImpl
    fun destroy();

    companion object {
        fun createRGBA(gl: GL, width: Int = noImpl, height: Int = noImpl): GLFrameBuffer = noImpl
        fun createFloat32(gl: GL, width: Int, height: Int, data: ArrayBuffer): GLFrameBuffer = noImpl
        fun createFloat32(gl: GL, width: Int, height: Int, data: ArrayBufferView): GLFrameBuffer = noImpl
    }
}
