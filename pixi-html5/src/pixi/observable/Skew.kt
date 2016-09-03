package pixi.observable

class Skew(val transform: Transform, var _x: Double = 0.0, var _y: Double = 0.0) : PointObserver() {
    var x: Double
        get() = _x
        set(value: Double) {
            if (_x != value) {
                _x = value
                transform.updateSkew()
            }
        }

    var y: Double
        get() = _y
        set(value: Double) {
            if (_y != value) {
                _y = value
                transform.updateSkew()
            }
        }

    fun setAll(v: Double) {
        if (_x !== v || _y !== v) {
            _x = v;
            _y = v;
            transform.updateSkew();
        }
    }

    fun set(x: Double, y: Double) {
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
