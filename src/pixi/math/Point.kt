package pixi.math

class Point(var x: Float = 0f, var y: Float = 0f) {
    fun setAll(v: Float) {
        x = v;
        y = v;
    }

    fun set(x: Float, y: Float) {
        this.x = x;
        this.y = y;
    }

    fun copy(p: Point) {
        x = p.x;
        y = p.y;
    }

    fun equals(p: Point): Boolean {
        return x == p.x && y == p.y;
    }

    fun clone(): Point {
        return Point(x, y);
    }
}
