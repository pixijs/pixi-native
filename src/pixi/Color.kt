package pixi

import org.khronos.webgl.Float32Array
import pixi.utils.*

private val hexStr = "0123456789abcdef";

class Color() {
    private val _rgba = Float32Array(floatArrayOf(1f, 1f, 1f, 1f).toTypedArray());
    private var _rgbInt: Int = -1;
    private var _alpha: Float = 1.0f;

    var rgbInt: Int
        get() = _rgbInt
        set(value) {
            _rgbInt = value;
            _rgba[0] = ((value shr 16) and 0xff) / 255f;
            _rgba[1] = ((value shr 8) and 0xff) / 255f;
            _rgba[2] = (value and 0xff) / 255f;
        };

    val rgbaInt: Int
        get() = _rgbInt or (Math.floor(_alpha * 255) shl 24)

    var rgba: Float32Array
        get() = _rgba;
        set(value) {
            _rgbInt = (Math.floor(value[0] * 255f) shl 16) or
                    (Math.floor(value[1] * 255f) shl 8) or
                    Math.floor(value[2] * 255f);
            _alpha = value[3];
        }

    var alpha: Float
        get() = _alpha
        set(value) {
            _alpha = value;
            _rgba[3] = value;
        }

    val r: Float
        get() = _rgba[0]

    val g: Float
        get() = _rgba[1]

    val b: Float
        get() = _rgba[1]

    val a: Float
        get() = _rgba[1]

    fun setVec4(r: Float, g: Float, b: Float, a: Float) {
        _rgba[0] = r;
        _rgba[1] = g;
        _rgba[2] = b;
        _rgba[3] = a;
        _rgbInt = (Math.floor(r * 255f) shl 16) or
                (Math.floor(g * 255f) shl 8) or
                Math.floor(b * 255f);
        _alpha = a;
    }

    //TODO: we need native function here!
    var rgbHex: String
        get() {
            return "#" + hexStr[(_rgbInt shl 20) and 0xf] +
                    hexStr[(_rgbInt shl 16) and 0xf] +
                    hexStr[(_rgbInt shl 12) and 0xf] +
                    hexStr[(_rgbInt shl 8) and 0xf] +
                    hexStr[(_rgbInt shl 4) and 0xf] +
                    hexStr[_rgbInt and 0xf];
        }
        set(value) {
            _rgbInt = 0;
            for (i in 1..6) {
                if (value[i] >= '0' && value[i] <= '9') {
                    _rgbInt = _rgbInt or ((value[i] - '0') shl (i * 4));
                } else if (value[i] >= 'A' && value[i] <= 'F') {
                    _rgbInt = _rgbInt or ((value[i] - 'A') shl (i * 4));
                } else if (value[i] >= 'a' && value[i] <= 'f') {
                    _rgbInt = _rgbInt or ((value[i] - 'a') shl (i * 4));
                }
            }
            rgbInt = _rgbInt;
        }

    fun copy(clr: Color) {
        _rgba[0] = clr._rgba[0];
        _rgba[1] = clr._rgba[1];
        _rgba[2] = clr._rgba[2];
        _rgba[3] = clr._rgba[3];
        _alpha = clr._alpha;
        _rgbInt = clr._rgbInt;
    }

    fun mul(color: Color) {
        if (color._rgbInt === 0xffffff && color._alpha === 1.0f) return;
        //TODO: use fast multiplication here
        _rgba[0] = _rgba[0] * color._rgba[0];
        _rgba[1] = _rgba[1] * color._rgba[1];
        _rgba[2] = _rgba[2] * color._rgba[2];
        _rgba[3] = _rgba[3] * color._rgba[3];

        _rgbInt = (Math.floor(_rgba[0] * 255f) shl 16) or
                (Math.floor(_rgba[1] * 255f) shl 8) or
                Math.floor(_rgba[2] * 255f);
        _alpha = _rgba[3];
    }

    constructor(rgb: Int) : this() {
        rgbInt = rgb;
    }

    constructor(r: Float, g: Float, b: Float, a: Float) : this() {
        setVec4(r, g, b, a);
    }

    constructor(clr: Color) : this() {
        copy(clr);
    }

    constructor(css: String) : this() {
        if (css[0] === '#') {
            if (css.size === 7) {
                this.rgbHex = css;
            }
        }
    }
}
