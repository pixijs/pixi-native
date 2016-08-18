package pixi.display

import pixi.Color
import pixi.math.Matrix
import pixi.math.Point
import pixi.renderers.WebGLRenderer

open class DisplayObject {
    var transform: TransformBase = Transform()

    open val children: MutableList<DisplayObject>?
        get() = null

    var position: Point
        get() = transform.position
        set(value: Point) {
            transform.position.copy(value)
        }

    var scale: Point
        get() = transform.scale
        set(value: Point) {
            transform.scale.copy(value)
        }

    var skew: Point
        get() = transform.skew
        set(value: Point) {
            transform.skew.copy(value)
        }

    var pivot: Point
        get() = transform.pivot
        set(value: Point) {
            transform.pivot.copy(value)
        }

    var x: Float
        get() = transform.position.x
        set(value: Float) {
            transform.position.x = value
        }

    var y: Float
        get() = transform.position.y
        set(value: Float) {
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

        transform.updateTransform(_parent?.transform ?: TransformBase.IDENTITY)

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
