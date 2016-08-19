package pixi.observable

open class PointObserver {
    var _localID: Int = 0
    inline fun invalidate() {
        _localID++
    }
}
