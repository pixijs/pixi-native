package pixi.gl_test.shader

import org.khronos.webgl.WebGLUniformLocation
import org.khronos.webgl.WebGLRenderingContext as GL;

fun mapType(type: Int): String {
    return GL_TABLE[type] ?: "errtype";
};
private val GL_TABLE = hashMapOf(
        GL.FLOAT to "float",
        GL.FLOAT_VEC2 to "vec2",
        GL.FLOAT_VEC3 to "vec3",
        GL.FLOAT_VEC4 to "vec4",

        GL.INT to "int",
        GL.INT_VEC2 to "ivec2",
        GL.INT_VEC3 to "ivec3",
        GL.INT_VEC4 to "ivec4",

        GL.BOOL to "bool",
        GL.BOOL_VEC2 to "bvec2",
        GL.BOOL_VEC3 to "bvec3",
        GL.BOOL_VEC4 to "bvec4",

        GL.FLOAT_MAT2 to "mat2",
        GL.FLOAT_MAT3 to "mat3",
        GL.FLOAT_MAT4 to "mat4",

        GL.SAMPLER_2D to "sampler2D"
);
