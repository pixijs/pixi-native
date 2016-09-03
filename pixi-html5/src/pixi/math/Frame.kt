package pixi.math

class Frame(var x: Int = 0, var y: Int = 0, var width: Int = 0, var height: Int = 0) {

    fun copy(frame: Frame) {
        x = frame.x;
        y = frame.y;
        width = frame.width;
        height = frame.height;
    }

    companion object {
        val EMPTY = Frame();
    }
}