package pixi.observable

import pixi.Real

class Skew(val transform: Transform, var _x: Real = 0.0, var _y: Real = 0.0) : TransformBase() {
    var x: Real
        get() = _x
        set(value: Real) {
            if (_x != value) {
                _x = value
                transform.updateSkew()
            }
        }

    var y: Real
        get() = _y
        set(value: Real) {
            if (_y != value) {
                _y = value
                transform.updateSkew()
            }
        }

    fun setAll(v: Real) {
        if (_x !== v || _y !== v) {
            _x = v;
            _y = v;
            transform.updateSkew();
        }
    }

    fun set(x: Real, y: Real) {
        if (_x !== x || _y !== y) {
            _x = x;
            _y = y;
            transform.updateSkew();
        }
    }

    fun copy(p: Skew) {
        if (_x !== p.x || _y !== p.y) {
            _x = p.x;
            _y = p.y;
            transform.updateSkew();
        }
    }

    fun copy(p: Point) {
        if (_x !== p.x || _y !== p.y) {
            _x = p.x;
            _y = p.y;
            transform.updateSkew();
        }
    }
}
