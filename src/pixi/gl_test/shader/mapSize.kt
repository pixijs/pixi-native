package pixi.gl_test.shader

fun mapSize(type: String): Int {
    return GLSL_TO_SIZE[type] ?: 0;
};


private val GLSL_TO_SIZE = hashMapOf(
    "float" to 1,
    "vec2" to 2,
    "vec3" to 3,
    "vec4" to 4,

    "int" to 1,
    "ivec2" to 2,
    "ivec3" to 3,
    "ivec4" to 4,

    "bool" to 1,
    "bvec2" to 2,
    "bvec3" to 3,
    "bvec4" to 4,

    "mat2" to 4,
    "mat3" to 9,
    "mat4" to 16,

    "sampler2D" to 1
);
