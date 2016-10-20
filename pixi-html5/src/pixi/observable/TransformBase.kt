package pixi.observable

import pixi.Real

open class TransformBase {
    var _pointVersion: Int = 0
    inline fun invalidate() {
        _pointVersion++
    }

    inner class Point(var _x: Real, var _y: Real) {
        var x: Real
            get() = _x
            set(value) {
                if (_x == value) {
                    return
                }
                _x = value
                invalidate()
            }

        var y: Real
            get() = _y
            set(value) {
                if (_y == value) {
                    return
                }
                _y = value
                invalidate()
            }

        fun setAll(v: Real) {
            if (_x == v && _y == v) {
                return
            }
            _x = v
            _y = v
            invalidate()
        }

        fun set(x: Real, y: Real) {
            if (_x == x && _y == y) {
                return
            }
            _x = x;
            _y = y;
            invalidate();
        }

        fun copy(p: Point) {
            if (_x == p.x && _y == p.y) {
                return
            }
            _x = p.x;
            _y = p.y;
            invalidate();
        }
    }

    fun newPoint(x: Real, y: Real): Point {
        return Point(x, y);
    }

    companion object {
        val defTransformBase = TransformBase();

        fun newPoint(x: Real = 0.0, y: Real = 0.0): Point {
            return defTransformBase.newPoint(x, y);
        }
    }
}
