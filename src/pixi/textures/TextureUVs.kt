package pixi.textures

import org.khronos.webgl.Uint32Array
import pixi.math.Frame
import pixi.utils.set

class TextureUVs {
    var x0 = 0f
    var y0 = 0f

    var x1 = 1f
    var y1 = 0f

    var x2 = 1f
    var y2 = 1f

    var x3 = 0f
    var y3 = 1f

    var uvsUint32 = Uint32Array(4)

    fun set(frame: Frame, baseFrame: BaseTexture, @Suppress("UNUSED_PARAMETER") rotate: Int) {
        var tw = baseFrame.width * 1.0f
        var th = baseFrame.height * 1.0f

        x0 = frame.x / tw
        y0 = frame.y / th

        x1 = (frame.x + frame.width) / tw
        y1 = frame.y / th

        x2 = (frame.x + frame.width) / tw
        y2 = (frame.y + frame.height) / th

        x3 = frame.x / tw
        y3 = (frame.y + frame.height) / th

        uvsUint32[0] = (Math.floor(y0 * 0xFFFF) and 0xFFFF) shl 16 or (Math.floor(x0 * 0xFFFF) and 0xFFFF)
        uvsUint32[1] = (Math.floor(y1 * 0xFFFF) and 0xFFFF) shl 16 or (Math.floor(x1 * 0xFFFF) and 0xFFFF)
        uvsUint32[2] = (Math.floor(y2 * 0xFFFF) and 0xFFFF) shl 16 or (Math.floor(x2 * 0xFFFF) and 0xFFFF)
        uvsUint32[3] = (Math.floor(y3 * 0xFFFF) and 0xFFFF) shl 16 or (Math.floor(x3 * 0xFFFF) and 0xFFFF)
    }
}
