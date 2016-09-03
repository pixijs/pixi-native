package pixi.observable

import pixi.math.Matrix
import pixi.observable.Point
import pixi.observable.PointObserver

class Transform : PointObserver() {
    fun invalidateParent() {
        _parentID = -1;
    }

    var worldTransform = Matrix();
    var localTransform = Matrix();

    var _worldID = 0;

    var _parentID = -1;

    var _currentLocalID = 0;

    val position = Point(this);

    val scale = Point(this, 1.0, 1.0);

    val pivot = Point(this);

    val skew = Point(this);

    private var _rotation: Double = 0.0;
    private var _sr: Double = 0.0;
    private var _cr: Double = 1.0;

    private var rotation: Double
        get() = _rotation
        set(value: Double) {
            _rotation = value;
            _sr = Math.sin(value);
            _cr = Math.cos(value);
        }

    private var _cy: Double = 1.0;
    private var _sy: Double = 0.0;
    private var _nsx: Double = 0.0;
    private var _cx: Double = 1.0;

    fun updateSkew() {
        _cy = Math.cos(skew.y);
        _sy = Math.sin(skew.y);
        _nsx = Math.sin(skew.x);
        _cx = Math.cos(skew.x);

        _pointVersion++;
    }

    fun updateLocalTransform() {
        val lt = localTransform;
        if (_pointVersion !== _currentLocalID) {
            val a = _cr * scale.x;
            val b = _sr * scale.x;
            val c = -_sr * scale.y;
            val d = _cr * scale.y;

            lt.a = _cy * a + _sy * c;
            lt.b = _cy * b + _sy * d;
            lt.c = _nsx * a + _cx * c;
            lt.d = _nsx * b + _cx * d;

            lt.tx = position.x - (pivot.x * lt.a + pivot.y * lt.c);
            lt.ty = position.y - (pivot.x * lt.b + pivot.y * lt.d);
            _currentLocalID = _pointVersion;

            _parentID = -1;
        }
    }

    fun updateTransform(parentTransform: Transform) {
        val pt = parentTransform.worldTransform;
        val wt = worldTransform;
        val lt = localTransform;

        if (_currentLocalID !== _pointVersion) {
            val a = _cr * scale.x;
            val b = _sr * scale.x;
            val c = -_sr * scale.y;
            val d = _cr * scale.y;

            lt.a = _cy * a + _sy * c;
            lt.b = _cy * b + _sy * d;
            lt.c = _nsx * a + _cx * c;
            lt.d = _nsx * b + _cx * d;

            lt.tx = position.x - (pivot.x * lt.a + pivot.y * lt.c);
            lt.ty = position.y - (pivot.x * lt.b + pivot.y * lt.d);
            _currentLocalID = _pointVersion;

            _parentID = -1;
        }

        if (_parentID !== parentTransform._worldID) {
            wt.a = lt.a * pt.a + lt.b * pt.c;
            wt.b = lt.a * pt.b + lt.b * pt.d;
            wt.c = lt.c * pt.a + lt.d * pt.c;
            wt.d = lt.c * pt.b + lt.d * pt.d;
            wt.tx = lt.tx * pt.a + lt.ty * pt.c + pt.tx;
            wt.ty = lt.tx * pt.b + lt.ty * pt.d + pt.ty;

            _parentID = parentTransform._worldID;

            _worldID++;
        }
    }

    companion object {
        val IDENTITY = Transform()
    }
}
