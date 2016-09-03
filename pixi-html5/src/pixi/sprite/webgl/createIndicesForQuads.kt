package pixi.sprite.webgl

import org.khronos.webgl.Uint16Array
import pixi.utils.set

fun createIndicesForQuads(size: Int): Uint16Array {
    // the total number of indices in our array, there are 6 points per quad.

    var totalIndices = size * 6

    var indices = Uint16Array(totalIndices)

    // fill the indices with the quads to draw
    var i = 0;
    var j = 0;

    while (i < totalIndices) {
        indices[i + 0] = j + 0
        indices[i + 1] = j + 1
        indices[i + 2] = j + 2
        indices[i + 3] = j + 0
        indices[i + 4] = j + 2
        indices[i + 5] = j + 3
        i += 6;
        j += 4;
    }

    return indices
}
