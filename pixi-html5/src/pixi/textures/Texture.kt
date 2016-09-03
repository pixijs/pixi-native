package pixi.textures

import pixi.math.Frame

open class Texture(var baseTexture: BaseTexture, frame: Frame? = null, orig: Frame? = null, trim: Frame? = null, rotate: Int = 0) {

    var frame: Frame = frame ?: Frame(0, 0, baseTexture.width, baseTexture.height)
    var orig: Frame = orig ?: Frame(0, 0, this.frame.width, this.frame.height)
    var trim: Frame? = trim

    var valid = true
    var _updateID = 0

    var _uvs = TextureUVs()

    private var _rotate = rotate;

    var rotate: Int
        get() = _rotate;
        set(value: Int) {
            _rotate = value;
            _updateUVs()
        }

    private fun _updateUVs() {
        _uvs.set(frame, baseTexture, this.rotate);
        _updateID++
    }

    init {
        _updateUVs()
    }

    companion object {
        val EMPTY = Texture(BaseTexture())

        init {
            EMPTY.valid = false;
        }
    }
}

