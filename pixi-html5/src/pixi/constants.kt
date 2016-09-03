package pixi

val VERSION = "0.0.1";

enum class RendererType(val systemName: String) {
    UNKNOWN("Unknown"),
    WEBGL("WebGL"),
    CANVAS("Canvas");
}

enum class ScaleMode {
    NEAREST,
    LINEAR;

    companion object {
        var DEFAULT = NEAREST;
    }
}

enum class Precision(val glsl: String) {
    LOW("lowp"),
    MEDIUM("mediump"),
    HIGH("highp");

    companion object {
        var DEFAULT = MEDIUM;
    }
}

enum class WrapMode() {
    CLAMP,
    REPEAT,
    MIRRORED_REPEAT;

    companion object {
        val DEFAULT = CLAMP;
    }
}

enum class GcMode {
    MANUAL,
    AUTO;

    companion object {
        val DEFAULT = AUTO;
    }
}
