package pixi.utils

import org.w3c.dom.HTMLCanvasElement
import pixi.gl.createContext
import kotlin.browser.document
import org.khronos.webgl.WebGLRenderingContext as GL

val fragTemplate = """
precision mediump float;
void main(void){
float test = 0.1;
%forloop%
gl_FragColor = vec4(0.0);
}
""";

val regexCount = Regex("%count%");
val regexForLoop = Regex("%forloop%");

fun checkMaxIfStatementsInShader(_maxIfs: Int, _gl: GL?): Int{
    var tempGL: GL? = null
    var maxIfs = _maxIfs

    if (_gl == null) {
        var tinyCanvas = document.createElement("canvas") as HTMLCanvasElement;
        tinyCanvas.width = 1;
        tinyCanvas.height = 1;

        tempGL = createContext(tinyCanvas)
    }

    var gl = _gl ?: tempGL!!

    var shader = gl.createShader(GL.FRAGMENT_SHADER);

    while (true) {
        var fragmentSrc = regexForLoop.replace(fragTemplate, generateIfTestSrc(maxIfs));

        gl.shaderSource(shader, fragmentSrc);
        gl.compileShader(shader);

        if (gl.getShaderParameter(shader, GL.COMPILE_STATUS) == null) {
            maxIfs /= 2;
        } else {
            // valid!
            break;
        }
    }

    if (tempGL != null) {
        // get rid of context
        if (gl.getExtension("WEBGL_lose_context")) {
            gl.getExtension("WEBGL_lose_context").loseContext();
        }
    }

    return maxIfs;
};



fun generateIfTestSrc(maxIfs: Int): String {
    val src = StringBuilder();

    for (i in 0..maxIfs - 1) {
        if (i > 0) {
            src.append("\nelse ");
        }

        if (i < maxIfs - 1) {
            src.append("if(test == $i.0){}");
        }
    }

    return src.toString();
}
