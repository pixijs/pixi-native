package pixi.gl

import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement

fun createContext(canvas: HTMLCanvasElement, options: dynamic = null): WebGLRenderingContext {
    var gl = (canvas.getContext("webgl", options) ?:
            canvas.getContext("experimental-webgl", options)) as? WebGLRenderingContext;
    if (gl === null) {
        throw Throwable("This browser does not support webGL. Try using the canvas renderer");
    } else {
        return gl;
    }
}
