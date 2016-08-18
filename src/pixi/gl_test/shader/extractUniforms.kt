package pixi.gl_test.shader

import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.WebGLUniformLocation
import java.util.*

class Uniform(val type: String, val size: Int, val location: WebGLUniformLocation, val value: Any) {

}

private val regex1 = Regex("\\[.*?\\]");

fun extractUniforms(gl: GL, program: WebGLProgram): HashMap<String, Uniform> {
    var uniforms = HashMap<String, Uniform>();

    var totalUniforms = gl.getProgramParameter(program, GL.ACTIVE_UNIFORMS) as Int;

    for (i in 0..totalUniforms - 1) {
        var uniformData = gl.getActiveUniform(program, i)!!;
        var name = regex1.replace(uniformData.name, "");
        var type = mapType(uniformData.type);

        uniforms[name] = Uniform(type, uniformData.size, gl.getUniformLocation(program, name)!!, defaultValue(type, uniformData.size));
    }

    return uniforms;
};
