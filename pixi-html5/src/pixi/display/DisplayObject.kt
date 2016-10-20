package pixi.display

import pixi.Color
import pixi.math.Matrix
import pixi.observable.Transform
import pixi.renderers.WebGLRenderer
import pixi.Real
import pixi.observable.TransformBase.Point

open class DisplayObject {
//    var transform: TransformBase = Transform()
    val transform = Transform()

    open val children: MutableList<DisplayObject>?
        get() = null

    var position: Point
        get() = transform.position
        set(value) {
            transform.position.copy(value)
        }

    var scale: Point
        get() = transform.scale
        set(value) {
            transform.scale.copy(value)
        }

    var skew: Point
        get() = transform.skew
        set(value) {
            transform.skew.copy(value)
        }

    var pivot: Point
        get() = transform.pivot
        set(value) {
            transform.pivot.copy(value)
        }

    var x: Real
        get() = transform.position.x
        set(value) {
            transform.position.x = value
        }

    var y: Real
        get() = transform.position.y
        set(value) {
            transform.position.y = value
        }

    val localTransform: Matrix
        get() = transform.localTransform

    val worldTransform: Matrix
        get() = transform.worldTransform

    val mulColor = Color()

    var alpha: Float
        get() = mulColor.alpha
        set(value: Float) {
            mulColor.alpha = value
        }

    var tint: Int
        get() = mulColor.rgbInt
        set(value: Int) {
            mulColor.rgbInt = value
        }

    var worldMulColor = Color()

    //TODO: localColor, worldColor, like transform

    val worldAlpha: Float
        get() = worldMulColor.alpha

    val worldTint: Int
        get() = worldMulColor.rgbInt

    var visible: Boolean = true

    var renderable: Boolean = true

    //TODO: make NullParent or NullContainer

    var parent: Container? = null

    var js: dynamic = js("({})")

    open fun objectUpdateTransform() {
        val _parent = parent

        transform.updateTransform(_parent?.transform ?: Transform.IDENTITY)

        if (_parent != null) {
            worldMulColor.copy(this.mulColor)
            worldMulColor.mul(_parent.worldMulColor)
        }
    }

    open fun objectRenderWebGL(renderer: WebGLRenderer) {

    }

    open fun updateTransform() {
        objectUpdateTransform()
    }

    open fun renderWebGL(renderer: WebGLRenderer) {
        objectRenderWebGL(renderer)
    }
}
