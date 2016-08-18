package pixi.math

class ObservablePoint(var cb: Versionable, x: Float = 0f, y: Float = 0f) : Point(x, y) {

    override var x: Float
        get() = super.x
        set(value: Float) {
            if (super.x != value) {
                super.x = value
                cb.invalidate()
            }
        }

    override var y: Float
        get() = super.y
        set(value: Float) {
            if (super.y != value) {
                super.y = value
                cb.invalidate()
            }
        }

    override fun setAll(v: Float) {
        if (super.x !== v || super.y !== v) {
            super.x = v;
            super.y = v;
            cb.invalidate();
        }
    }

    override fun set(x: Float, y: Float) {
        if (super.x !== x || super.y !== y) {
            super.x = x;
            super.y = y;
            cb.invalidate();
        }
    }

    override fun copy(p: Point) {
        if (super.x !== p.x || super.y !== p.y) {
            super.x = p.x;
            super.y = p.y;
            cb.invalidate();
        }
    }
}
