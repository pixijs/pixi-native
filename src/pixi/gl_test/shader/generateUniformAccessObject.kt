package pixi.gl_test.shader

import java.util.*
import org.khronos.webgl.WebGLRenderingContext as GL

fun generateUniformAccessObject(gl: GL, uniformData: HashMap<String, Uniform>) {
    // this is the object we will be sending back.
    // an object hierachy will be created for structs
    var uniforms = js("{data: {}}");

    uniforms.gl = gl;

    for ((fullName, uniform) in uniformData) {
        var nameTokens = fullName.split(".");
        var name = nameTokens[nameTokens.size - 1];

        var uniformGroup = getUniformGroup(nameTokens, uniforms);

        uniformGroup.data[name] = uniform;

        uniformGroup.gl = gl;

        js("Object.defineProperty(uniformGroup, name, { get: generateGetter(name), set: generateSetter(name, uniform) })");
    }

    return uniforms;
};

fun generateGetter(name: String): String
{
    var template = getterTemplate.replace("%%", name);
    return js("new Function (template)"); // jshint ignore toline
};

fun generateSetter(name: String, uniform: Uniform)
{
    var template = setterTemplate.replace("%%", name);
    var setTemplate: String?;

    if (uniform.size == 1) {
        setTemplate = GLSL_TO_SINGLE_SETTERS[uniform.type];
    } else {
        setTemplate = GLSL_TO_ARRAY_SETTERS[uniform.type];
    }

    if (setTemplate != null) {
        template += "\nthis.gl." + setTemplate + ";";
    }

    return js("new Function ('value', template)"); // jshint ignore toline
};

fun getUniformGroup(nameTokens: List<String>, uniform : dynamic) : dynamic
{
    var cur = uniform;

    for (token in nameTokens)
    {
        var o = cur[token] ?: js("{ data: {} }");
        cur[token] = o;
        cur = o;
    }

    return cur;
};

var getterTemplate =
        "return this.data.%%.value;";

var setterTemplate =
        """this.data.%%.value = value;
var location = this.data.%%.location;""";


var GLSL_TO_SINGLE_SETTERS = hashMapOf(
        "float" to "uniform1f(location, value)",

        "vec2" to "uniform2f(location, value[0], value[1])",
        "vec3" to "uniform3f(location, value[0], value[1], value[2])",
        "vec4" to "uniform4f(location, value[0], value[1], value[2], value[3])",

        "int" to "uniform1i(location, value)",
        "ivec2" to "uniform2i(location, value[0], value[1])",
        "ivec3" to "uniform3i(location, value[0], value[1], value[2])",
        "ivec4" to "uniform4i(location, value[0], value[1], value[2], value[3])",

        "bool" to "uniform1i(location, value)",
        "bvec2" to "uniform2i(location, value[0], value[1])",
        "bvec3" to "uniform3i(location, value[0], value[1], value[2])",
        "bvec4" to "uniform4i(location, value[0], value[1], value[2], value[3])",

        "mat2" to "uniformMatrix2fv(location, false, value)",
        "mat3" to "uniformMatrix3fv(location, false, value)",
        "mat4" to "uniformMatrix4fv(location, false, value)",

        "sampler2D" to "uniform1i(location, value)"
);

var GLSL_TO_ARRAY_SETTERS = hashMapOf(
        "float" to "uniform1fv(location, value)",

        "vec2" to "uniform2fv(location, value)",
        "vec3" to "uniform3fv(location, value)",
        "vec4" to "uniform4fv(location, value)",

        "int" to "uniform1iv(location, value)",
        "ivec2" to "uniform2iv(location, value)",
        "ivec3" to "uniform3iv(location, value)",
        "ivec4" to "uniform4iv(location, value)",

        "bool" to "uniform1iv(location, value)",
        "bvec2" to "uniform2iv(location, value)",
        "bvec3" to "uniform3iv(location, value)",
        "bvec4" to "uniform4iv(location, value)",

        "sampler2D" to "uniform1iv(location, value)"
);

