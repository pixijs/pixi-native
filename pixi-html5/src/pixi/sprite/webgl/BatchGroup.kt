package pixi.sprite.webgl

import pixi.BlendMode
import pixi.textures.BaseTexture

class BatchGroup {
    var textures = mutableListOf<BaseTexture>()
    var textureCount = 0
    var size = 0
    var start = 0
    var blend = BlendMode.NORMAL
}
