package pixi.gl_test.shader

import org.khronos.webgl.WebGLProgram
import java.util.*
import org.khronos.webgl.WebGLRenderingContext as GL

class Attribute(val type: String, val size: Int, val location: Int);

fun extractAttributes(gl: GL, program: WebGLProgram): Map<String, Attribute> {
    var attributes = HashMap<String, Attribute>();

    var totalAttributes = gl.getProgramParameter(program, GL.ACTIVE_ATTRIBUTES) as Int;

    for (i in 0..totalAttributes - 1) {
        var attribData = gl.getActiveAttrib(program, i)!!;
        var type = mapType(attribData.type);

        attributes[attribData.name] = Attribute(type, mapSize(type), gl.getAttribLocation(program, attribData.name));
    }

    return attributes;
};
