package pixi.renderers

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.Node
import pixi.utils.*
import pixi.*
import pixi.display.DisplayObject
import pixi.observable.Transform
import pixi.textures.RenderTexture
import kotlin.browser.document

//TODO: EventEmitter in Kotlin

abstract class SystemRenderer(var width: Int = 800, var height: Int = 600, val options: RenderOptions = RenderOptions()) : Destroyable {

    abstract val type: RendererType

    val view: HTMLCanvasElement = options.view ?: document.createElement("canvas") as HTMLCanvasElement
    var resolution = options.resolution
    var autoResize = options.autoResize
    var clearBeforeRender = options.clearBeforeRender
    var roundPixels = options.roundPixels

    private var _backgroundColor = Color()

    var backgroundColor: Color
        get() = _backgroundColor
        set(value) {
            _backgroundColor.copy(value)
        }

    init {
        backgroundColor = options.backgroundColor
    }

    open fun resize(width: Int, height: Int) {
        this.width = Math.ceil(width * this.resolution)
        this.height = Math.ceil(height * this.resolution)

        this.view.width = this.width
        this.view.height = this.height

        if (this.autoResize) {
            this.view.style.width = (this.width / this.resolution).toString() + "px"
            this.view.style.height = (this.height / this.resolution).toString() + "px"
        }
    }

    override var isDestroyed = false

    override fun destroy() {
        super.destroy()
        destroy(false)
    }

    open fun destroy(removeView: Boolean = false) {
        if (removeView && this.view.parentNode !== null) {
            (this.view.parentNode as Node).removeChild(this.view)
        }
    }

    abstract fun render(displayObject: DisplayObject, renderTexture: RenderTexture? = null, clear: Boolean? = null, transform: Transform? = null, skipUpdateTransform: Boolean = false)
}
