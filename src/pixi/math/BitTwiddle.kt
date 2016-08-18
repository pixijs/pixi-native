package pixi.math

object BitTwiddle {
    fun isPow2(v: Int): Boolean {
        return (v and (v - 1)) == 0 && v != 0;
    }

    fun nextPow2(t: Int): Int {
        var v = t;
        if (v > 0) {
            v++;
        };
        v = v or (v shr 1);
        v = v or (v shr 2);
        v = v or (v shr 4);
        v = v or (v shr 8);
        v = v or (v shr 16);
        return v + 1;
    }

    fun log2(t: Int): Int {
        var v = t
        var r: Int
        var shift: Int;
        r = if (v > 0xFFFF) 16 else 0; v = v ushr r
        shift = if (v > 0xFF) 8 else 0; v = v ushr shift; r = r or shift
        shift = if (v > 0xF) 4 else 0; v = v ushr shift; r = r or shift
        shift = if (v > 0x3) 2 else 0; v = v ushr shift; r = r or shift
        return r or (v shr 1)
    }
}
