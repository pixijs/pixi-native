package pixi.sprite.webgl

import org.khronos.webgl.Int32Array
import pixi.renderers.Shader
import pixi.renderers.WebGLRenderer
import pixi.utils.*

val vertexSrc = """attribute vec2 aVertexPosition;
attribute vec2 aTextureCoord;
attribute vec4 aColor;
attribute float aTextureId;

uniform mat3 projectionMatrix;

varying vec2 vTextureCoord;
varying vec4 vColor;
varying float vTextureId;

void main(void){
   gl_Position = vec4((projectionMatrix * vec3(aVertexPosition, 1.0)).xy, 0.0, 1.0);
   vTextureCoord = aTextureCoord;
   vTextureId = aTextureId;
   vColor = vec4(aColor.rgb * aColor.a, aColor.a);
}""";

val fragTemplate = """varying vec2 vTextureCoord;
varying vec4 vColor;
varying float vTextureId;
uniform sampler2D uSamplers[%count%];

void main(void){
vec4 color;
float textureId = floor(vTextureId+0.5);
%forloop%
gl_FragColor = color * vColor;
}""";

val regexCount = Regex("%count%");
val regexForLoop = Regex("%forloop%");

fun generateMultiTextureShader(renderer: WebGLRenderer, maxTextures: Int): Shader {
    var fragmentSrc = fragTemplate;

    fragmentSrc = regexCount.replace(fragmentSrc, maxTextures.toString());
    fragmentSrc = regexForLoop.replace(fragmentSrc, generateSampleSrc(maxTextures));

    var shader = Shader(renderer, vertexSrc, fragmentSrc);

    var sampleValues = Int32Array(maxTextures);
    for (i in 0..maxTextures - 1) {
        sampleValues[i] = i
    }

    shader.bind();
    shader.uniforms.uSamplers = sampleValues;

    return shader;
}

fun generateSampleSrc(maxTextures: Int): String {
    if (maxTextures == 1) {
        return "color = texture2D(uSamplers[0], vTextureCoord);";
    }

    val src = StringBuilder();

    src.append("\n");
    src.append("\n");

    for (i in 0..maxTextures - 1) {
        if (i > 0) {
            src.append("\nelse ");
        }

        if (i < maxTextures - 1) {
            src.append("if(textureId == $i.0)");
        }

        src.append("\n{");
        src.append("\n\tcolor = texture2D(uSamplers[$i], vTextureCoord);");
        src.append("\n}");
    }

    src.append("\n");
    src.append("\n");

    return src.toString();
}
