package pixi.sprite.webgl

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint32Array

class BatchBuffer(size: Int) {
    var vertices = ArrayBuffer(size)

    var float32View = Float32Array(vertices)

    var uint32View = Uint32Array(vertices)
}
