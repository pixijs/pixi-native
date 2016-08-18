package pixi.gl

import org.khronos.webgl.WebGLRenderingContext as GL

@native(name="PIXI.glCore.VertexArrayObject") public class VertexArrayObject {
    constructor(gl: GL, state: dynamic = noImpl)

    val gl: GL
        get() = noImpl

    val attributes: VertexAttributeArray
        get() = noImpl

    val indexBuffer: GLBuffer?
        get() = noImpl

    fun bind(): VertexArrayObject = noImpl
    fun unbind(): Unit = noImpl
    fun activate(): Unit = noImpl
    fun addAttribute(buffer: GLBuffer, attribute: Attribute, type: Int = noImpl, normalized: Boolean = noImpl, stride: Int = noImpl, start: Int = noImpl): VertexArrayObject = noImpl
    fun addIndex(buffer: GLBuffer): VertexArrayObject = noImpl
    fun clear(): Unit = noImpl
    fun draw(type: Int, size: Int, start: Int = noImpl): Unit = noImpl
    fun destroy()

    companion object {
        var FORCE_NATIVE: Boolean
            get() = noImpl
            set(value: Boolean) = noImpl
    }
}
