package pixi.gl_test.shader2

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int32Array
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLUniformLocation
import pixi.gl_test.shader.mapType
import java.util.*
import org.khronos.webgl.WebGLRenderingContext as GL

private val regex1 = Regex("\\[.*?\\]");

fun extractUniformsEx(gl: org.khronos.webgl.WebGLRenderingContext, program: WebGLProgram): Map<String, Uniform> {
    var uniforms = HashMap<String, Uniform>();

    var totalUniforms = gl.getProgramParameter(program, org.khronos.webgl.WebGLRenderingContext.ACTIVE_UNIFORMS) as Int;

    for (i in 0..totalUniforms - 1) {
        var uniformData = gl.getActiveUniform(program, i)!!;
        var name = regex1.replace(uniformData.name, "")
        var type = mapType(uniformData.type);

        var location = gl.getUniformLocation(program, name)!!;
        var size = uniformData.size;

        if (size == 1) {
            uniforms[name] = when (name) {
                "float" -> UniformFloat(gl, location);
                "vec2" -> UniformVec2(gl, location);
                "vec3" -> UniformVec3(gl, location);
                "vec4" -> UniformVec4(gl, location);
                "int" -> UniformInt(gl, location);
                "ivec2" -> UniformIVec2(gl, location);
                "ivec3" -> UniformIVec3(gl, location);
                "ivec4" -> UniformIVec4(gl, location);
                "bool" -> UniformInt(gl, location);
                "bvec2" -> UniformIVec2(gl, location);
                "bvec3" -> UniformIVec3(gl, location);
                "bvec4" -> UniformIVec4(gl, location);
                "sampler2D" -> UniformSampler2D(gl, location);
                "mat2" -> UniformMat2(gl, location, size);
                "mat3" -> UniformMat3(gl, location, size);
                "mat4" -> UniformMat4(gl, location, size);
                else -> UniformFloat(gl, location);
            }
        } else {
            uniforms[name] = when (name) {
                "float" -> UniformFloatV(gl, location, size);
                "vec2" -> UniformVec2V(gl, location, size);
                "vec3" -> UniformVec3V(gl, location, size);
                "vec4" -> UniformVec4V(gl, location, size);
                "int" -> UniformIntV(gl, location, size);
                "ivec2" -> UniformIVec2V(gl, location, size);
                "ivec3" -> UniformIVec3V(gl, location, size)
                "ivec4" -> UniformIVec4V(gl, location, size);
                "bool" -> UniformIntV(gl, location, size);
                "bvec2" -> UniformIVec2V(gl, location, size);
                "bvec3" -> UniformIVec3V(gl, location, size);
                "bvec4" -> UniformIVec4V(gl, location, size);
                "sampler2D" -> UniformSampler2DV(gl, location, size);
                "mat2" -> UniformMat2(gl, location, size);
                "mat3" -> UniformMat3(gl, location, size);
                "mat4" -> UniformMat4(gl, location, size);
                else -> UniformFloatV(gl, location, size);
            }
        }
    }

    return uniforms;
};

abstract class Uniform(val gl: org.khronos.webgl.WebGLRenderingContext, val location: WebGLUniformLocation, val size: Int) {
    abstract val type: String;

    abstract var anyValue: Any;

    abstract fun upload();
}


class UniformFloat(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "float";
    var value: Float = 0f;

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float;
        };

    override fun upload() {
        gl.uniform1f(location, value);
    }
};

class UniformVec2(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "vec2";
    var value = Float32Array(2);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniform2f(location, value.get(0), value.get(1));
    }
};

class UniformVec3(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "vec3";
    var value = Float32Array(3);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniform3f(location, value.get(0), value.get(1), value.get(2));
    }
};

class UniformVec4(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "vec4";
    var value = Float32Array(4);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniform4f(location, value.get(0), value.get(1), value.get(2), value.get(3));
    }
};

class UniformFloatV(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "float";
    var value = Float32Array(size);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniform1fv(location, value);
    }
};

class UniformVec2V(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "vec2";
    var value = Float32Array(size * 2);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniform2fv(location, value);
    }
};

class UniformVec3V(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "vec3";
    var value = Float32Array(size * 3);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniform3fv(location, value);
    }
};

class UniformVec4V(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "vec4";
    var value = Float32Array(size * 4);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniform3fv(location, value);
    }
};

class UniformInt(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "int";
    var value: Int = 0;

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int;
        };

    override fun upload() {
        gl.uniform1i(location, value);
    }
};

class UniformIVec2(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "ivec2";
    var value = Int32Array(2);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform2i(location, value.get(0), value.get(1));
    }
};

class UniformIVec3(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "ivec3";
    var value = Int32Array(3);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform3i(location, value.get(0), value.get(1), value.get(2));
    }
};

class UniformIVec4(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "ivec4";
    var value = Int32Array(4);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform4i(location, value.get(0), value.get(1), value.get(2), value.get(3));
    }
};

class UniformIntV(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "int";
    var value = Int32Array(size);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform1iv(location, value);
    }
};

class UniformIVec2V(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "ivec2";
    var value = Int32Array(size * 2);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform2iv(location, value);
    }
};

class UniformIVec3V(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "ivec3";
    var value = Int32Array(size * 3);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform3iv(location, value);
    }
};

class UniformIVec4V(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "ivec4";
    var value = Int32Array(size * 4);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform4iv(location, value);
    }
};

class UniformSampler2D(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation) : Uniform(gl, location, 1) {
    override val type: String = "sampler2D";
    var value: Int = 0;

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int;
        };

    override fun upload() {
        gl.uniform1i(location, value);
    }
};

class UniformSampler2DV(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "sampler2D";
    var value = Int32Array(size);

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Int32Array;
        };

    override fun upload() {
        gl.uniform1iv(location, value);
    }
};

class UniformMat2(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "mat2";
    var value = Float32Array(floatArrayOf(1.0f, 0.0f, 0.0f, 1.0f).toTypedArray());

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniformMatrix2fv(location, false, value);
    }
};

class UniformMat3(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "mat3";
    var value = Float32Array(floatArrayOf(
            1f, 0f, 0f,
            0f, 1f, 0f,
            0f, 0f, 1f).toTypedArray());

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniformMatrix3fv(location, false, value);
    }
};

class UniformMat4(gl: org.khronos.webgl.WebGLRenderingContext, location: WebGLUniformLocation, size: Int) : Uniform(gl, location, size) {
    override val type: String = "mat4";
    var value = Float32Array(floatArrayOf(
            1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f,
            0f, 0f, 1f, 0f,
            0f, 0f, 0f, 1f).toTypedArray());

    override var anyValue: Any
        get() = value
        set(_value) {
            value = _value as Float32Array;
        };

    override fun upload() {
        gl.uniformMatrix4fv(location, false, value);
    }
};