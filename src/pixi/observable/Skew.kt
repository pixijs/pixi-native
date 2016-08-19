package pixi.observable

class Skew(val transform: Transform, var _x: Float = 0f, var _y: Float = 0f) : PointObserver() {
    var x: Float
        get() = _x
        set(value: Float) {
            if (_x != value) {
                _x = value
                transform.updateSkew()
            }
        }

    var y: Float
        get() = _y
        set(value: Float) {
            if (_y != value) {
                _y = value
                transform.updateSkew()
            }
        }

    fun setAll(v: Float) {
        if (_x !== v || _y !== v) {
            _x = v;
            _y = v;
            transform.updateSkew();
        }
    }

    fun set(x: Float, y: Float) {
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
