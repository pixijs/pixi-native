package pixi.gl_test.shader

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int32Array

fun defaultValue(type: String, size: Int): Any {
    when (type) {
        "float" ->
            return 0f;

        "vec2" ->
            return Float32Array(2 * size);

        "vec3" ->
            return Float32Array(3 * size);

        "vec4" ->
            return Float32Array(4 * size);

        "int",
        "sampler2D" ->
            return 0;

        "ivec2" ->
            return Int32Array(2 * size);

        "ivec3" ->
            return Int32Array(3 * size);

        "ivec4" ->
            return Int32Array(4 * size);

        "bool" ->
            return false;

        "bvec2" ->
            return Array<Boolean>(2 * size, { i -> false });

        "bvec3" ->
            return Array<Boolean>(3 * size, { i -> false });

        "bvec4" ->
            return Array<Boolean>(4 * size, { i -> false });

        "mat2" ->
            return Float32Array(floatArrayOf(
                    1f, 0f,
                    0f, 1f
            ).toTypedArray());

        "mat3" ->
            return Float32Array(floatArrayOf(
                    1f, 0f, 0f,
                    0f, 1f, 0f,
                    0f, 0f, 1f
            ).toTypedArray());

        "mat4" ->
            return Float32Array(floatArrayOf(
                    1f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f,
                    0f, 0f, 1f, 0f,
                    0f, 0f, 0f, 1f
            ).toTypedArray());
        else ->
            return 0f;
    }
};
