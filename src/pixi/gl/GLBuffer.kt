package pixi.gl

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.w3c.dom.ArrayBufferView
import org.khronos.webgl.WebGLRenderingContext as GL

@native(name="PIXI.glCore.GLBuffer") public class GLBuffer {
    constructor(gl: GL, type: Int = noImpl, data: ArrayBuffer = noImpl, drawType: Int = noImpl)
    constructor(gl: GL, type: Int = noImpl, data: ArrayBufferView = noImpl, drawType: Int = noImpl)

    val gl: GL
        get() = noImpl

    val data: Float32Array
        get() = noImpl

    val drawType: Int
        get() = noImpl

    val type: Int
        get() = noImpl

    fun upload(data: ArrayBuffer, offset: Int = noImpl, dontBind: Boolean = noImpl): Unit = noImpl
    fun bind(): Unit = noImpl
    fun destroy(): Unit = noImpl

    companion object {
        fun createVertexBuffer(gl: GL, data: ArrayBuffer? = noImpl, drawType: Int = noImpl): GLBuffer = noImpl
        fun createVertexBuffer(gl: GL, data: ArrayBufferView = noImpl, drawType: Int = noImpl): GLBuffer = noImpl
        fun createIndexBuffer(gl: GL, data: ArrayBuffer? = noImpl, drawType: Int = noImpl): GLBuffer = noImpl
        fun createIndexBuffer(gl: GL, data: ArrayBufferView = noImpl, drawType: Int = noImpl): GLBuffer = noImpl
    }
}
