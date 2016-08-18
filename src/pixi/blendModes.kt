package pixi

import org.khronos.webgl.WebGLRenderingContext as GL

enum class BlendMode(val bcode: Int, val canvasName: String, val glFirstMul: Int = GL.ONE, val glSecondMul: Int = GL.ONE_MINUS_SRC_ALPHA) {
    NORMAL(0, "source-over"),
    ADD(1, "lighter", GL.ONE, GL.DST_ALPHA),
    MULTIPLY(2, "multiply", GL.DST_COLOR, GL.ONE_MINUS_SRC_ALPHA),
    SCREEN(3, "screen", GL.ONE, GL.ONE_MINUS_SRC_COLOR),
    OVERLAY(4, "overlay"),
    DARKEN(5, "darken"),
    LIGHTEN(6, "lighten"),
    COLOR_DODGE(7, "color-dodge"),
    COLOR_BURN(8, "color-burn"),
    HARD_LIGHT(9, "hard-light"),
    SOFT_LIGHT(10, "soft-light"),
    DIFFERENCE(11, "difference"),
    EXCLUSION(12, "exclusion"),
    HUE(13, "hue"),
    SATURATION(14, "saturate"),
    COLOR(15, "color"),
    LUMINOSITY(16, "luminosity");

    companion object {
        private val values = BlendMode.values();
        fun byCode(code: Int) = values[code];
    }
}
