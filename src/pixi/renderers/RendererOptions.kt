package pixi.renderers

import org.w3c.dom.HTMLCanvasElement
import pixi.Color

data class RenderOptions(
        var view: HTMLCanvasElement? = null,
        var resolution: Float = 1f,
        var antialias: Boolean = false,
        var transparent: Boolean = false,
        var autoResize: Boolean = false,
        var forceFXAA: Boolean = false,
        var clearBeforeRender: Boolean = true,
        var roundPixels: Boolean = false,
        var backgroundColor: Color = Color(0f, 0f, 0f, 0f),
        var preserveDrawingBuffer: Boolean = false
);

fun renderOptions(init: RenderOptions.() -> Unit): RenderOptions {
    val ro = RenderOptions();
    ro.init();
    return ro;
}