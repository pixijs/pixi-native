package pixi.gl_test.shader;

import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.WebGLShader

fun compileProgram(gl: GL, vertexSrc: String, fragmentSrc: String): WebGLProgram?
{
    var glVertShader = compileShader(gl, GL.VERTEX_SHADER, vertexSrc);
    var glFragShader = compileShader(gl, GL.FRAGMENT_SHADER, fragmentSrc);

    var program = gl.createProgram();

    gl.attachShader(program, glVertShader);
    gl.attachShader(program, glFragShader);
    gl.linkProgram(program);

    // if linking fails, then log and cleanup
    if (gl.getProgramParameter(program, GL.LINK_STATUS) == null)
    {
        console.error("Pixi.kt Error: Could not initialize shader.");
        console.error("gl.VALIDATE_STATUS", gl.getProgramParameter(program, GL.VALIDATE_STATUS));
        console.error("gl.getError()", gl.getError());

        // if there is a program info log, log it
        if (gl.getProgramInfoLog(program) != "")
        {
            console.warn("Pixi.kt Warning: pixi.gl.getProgramInfoLog()", gl.getProgramInfoLog(program));
        }

        gl.deleteProgram(program);
        program = null;
    }

    // clean up some shaders
    gl.deleteShader(glVertShader);
    gl.deleteShader(glFragShader);

    return program;
};

fun compileShader(gl: GL, type: Int, src: String): WebGLShader?
{
    var shader = gl.createShader(type);

    gl.shaderSource(shader, src);
    gl.compileShader(shader);

    if (gl.getShaderParameter(shader, GL.COMPILE_STATUS) != null)
    {
        console.log(gl.getShaderInfoLog(shader));
        return null;
    }

    return shader;
};