package pixi.gl

import org.khronos.webgl.*
import org.khronos.webgl.WebGLRenderingContext as GL

@native(name="PIXI.glCore.GLShader") public class GLShader {
    constructor(gl: GL, vertexSrc: String, fragmentSrc: String)
    constructor(gl: GL, vertexSrc: Array<String>, fragmentSrc: Array<String>)

    val gl: GL
        get() = noImpl

    val program: WebGLProgram?
        get() = noImpl

    val attributes: AttributeMap
        get() = noImpl

//    val uniformData: UniformMap
//        get() = noImpl

    val uniforms: dynamic

    fun bind(): Unit = noImpl
    fun destroy(): Unit = noImpl
}
