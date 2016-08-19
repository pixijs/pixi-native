package pixi.observable

import pixi.observable.PointObserver

class Point(var _x: Float = 0f, var _y: Float = 0f) : PointObserver() {
    var cb: PointObserver = this

    constructor(_cb: PointObserver, _x: Float = 0f, _y: Float = 0f) : this(_x, _y) {
        cb = _cb
    }

    var x: Float
        get() = _x
        set(value: Float) {
            if (_x == value) {
                return
            }
            _x = value
            cb.invalidate()
        }

    var y: Float
        get() = _y
        set(value: Float) {
            if (_y == value) {
                return
            }
            _y = value
            cb.invalidate()
        }

    fun setAll(v: Float) {
        if (_x == v && _y == v) {
            return
        }
        _x = v
        _y = v
        cb.invalidate()
    }

    fun set(x: Float, y: Float) {
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
