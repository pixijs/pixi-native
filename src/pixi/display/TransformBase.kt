package pixi.display

import pixi.math.Matrix
import pixi.math.Point

interface TransformBase {
    var worldTransform: Matrix;
    var localTransform: Matrix;

    var _worldID: Int;

    var _parentID: Int;

    val position: Point;

    val scale: Point;

    val pivot: Point;

    val skew: Point;

    companion object {
        val IDENTITY = Transform();
    }

    fun updateTransform(parentTransform: TransformBase)
}
