package pixi.observable

import pixi.observable.PointObserver

class Point(var _x: Double = 0.0, var _y: Double = 0.0) : PointObserver() {
    var cb: PointObserver = this

    constructor(_cb: PointObserver, _x: Double = 0.0, _y: Double = 0.0) : this(_x, _y) {
        cb = _cb
    }

    var x: Double
        get() = _x
        set(value) {
            if (_x == value) {
                return
            }
            _x = value
            cb.invalidate()
        }

    var y: Double
        get() = _y
        set(value) {
            if (_y == value) {
                return
            }
            _y = value
            cb.invalidate()
        }

    fun setAll(v: Double) {
        if (_x == v && _y == v) {
            return
        }
        _x = v
        _y = v
        cb.invalidate()
    }

    fun set(x: Double, y: Double) {
        if (_x == x && _y == y) {
            return
        }
        cb.invalidate();
        _x = x;
        _y = y;
    }

    fun copy(p: Point) {
        if (_x == p.x && _y == p.y) {
            return
        }
        _x = p.x;
        _y = p.y;
        cb.invalidate();
    }
}
