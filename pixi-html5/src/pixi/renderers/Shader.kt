package pixi.renderers

import org.khronos.webgl.WebGLProgram
import pixi.Destroyable
import pixi.Precision
import pixi.gl.AttributeMap
import pixi.gl.GLShader

import org.khronos.webgl.WebGLRenderingContext as GL


fun checkPrecision(src: String): String {
    if (src.substring(0, 9) != "precision") {
        return "precision " + Precision.DEFAULT.glsl + " float;\n" + src;
    }
    return src;
}

class Shader(val renderer: WebGLRenderer, vertexSrc: String, fragmentSrc: String) : Destroyable {
    constructor(renderer: WebGLRenderer, vertexSrc: Array<String>, fragmentSrc: Array<String>) :
    this(renderer, vertexSrc.joinToString("\n"), fragmentSrc.joinToString("\n"))

    private val shader: GLShader;

    init {
        shader = GLShader(renderer.gl, checkPrecision(vertexSrc), checkPrecision(fragmentSrc));
    }

    val gl: GL
        get() = renderer.gl

    val program: WebGLProgram?
        get() = shader.program

    val attributes: AttributeMap
        get() = shader.attributes

    val uniforms: dynamic
        get() = shader.uniforms

    override var isDestroyed = false;

    fun bind() = shader.bind();

    override fun destroy() {
        shader.destroy();
        super.destroy();
    }
}
