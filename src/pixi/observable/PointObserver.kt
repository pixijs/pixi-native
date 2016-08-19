package pixi.observable

open class PointObserver {
    var _pointVersion: Int = 0
    inline fun invalidate() {
        _pointVersion++
    }
}
