package pixi.observable

open class Point(open var x: Float = 0f, open var y: Float = 0f) {
    open fun setAll(v: Float) {
        x = v;
        y = v;
    }

    open fun set(x: Float, y: Float) {
        this.x = x;
        this.y = y;
    }

    open fun copy(p: Point) {
        x = p.x;
        y = p.y;
    }

    open fun equals(p: Point): Boolean {
        return x == p.x && y == p.y;
    }

    open fun clone(): Point {
        return Point(x, y);
    }
}
