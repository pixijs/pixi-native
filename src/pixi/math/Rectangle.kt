package pixi.math

class Rectangle(var x: Float = 0f, var y: Float = 0f, var width: Float = 0f, var height: Float = 0f) {

    fun copy(rect: Rectangle) {
        x = rect.x;
        y = rect.y;
        width = rect.width;
        height = rect.height;
    }

    companion object {
        val EMPTY = Rectangle();
    }
}
