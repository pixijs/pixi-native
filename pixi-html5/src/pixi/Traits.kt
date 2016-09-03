package pixi

interface Destroyable {
    var isDestroyed: Boolean;

    fun destroy() {
        isDestroyed = true;
    }
}


