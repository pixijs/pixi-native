package pixi.sprite

import org.khronos.webgl.Float32Array
import pixi.BlendMode
import pixi.display.Container
import pixi.observable.Point
import pixi.observable.PointObserver
import pixi.renderers.WebGLRenderer
import pixi.textures.Texture
import pixi.utils.set

open class Sprite(texture: Texture = Texture.EMPTY) : Container() {
    private var _texture = texture;

    init {

    }

    var texture: Texture
        get() = _texture
        set(value: Texture) {
            _texture = value
            onTextureUpdate()
        }

    fun onTextureUpdate() {
        _textureID = -1
    }

    var blendMode = BlendMode.NORMAL

    val vertexData = Float32Array(8)

    var _textureID = -1
    var _transformID = -1

    //TODO: find that name for supression
    //val anchor = Point(@Suppress("LEAKING_THIS") this)

    val anchor = Point(transform)

    fun calculateVertices() {
        if (_transformID == transform._worldID &&
                _textureID == _texture._updateID) {
            return
        }

        _transformID = transform._worldID
        _textureID = _texture._updateID

        val texture = _texture
        val orig = texture.orig
        val trim = texture.trim
        val wt = transform.worldTransform
        var w0: Float
        var w1: Float
        var h0: Float
        var h1: Float

        if (trim != null) {
            w1 = trim.x - anchor.x * orig.width;
            w0 = w1 + trim.width;

            h1 = trim.y - anchor.y * orig.height;
            h0 = h1 + trim.height;
        } else {
            w0 = orig.width * (1 - anchor.x);
            w1 = orig.width * -anchor.x;

            h0 = orig.height * (1 - anchor.y);
            h1 = orig.height * -anchor.y;
        }

        vertexData[0] = wt.a * w1 + wt.c * h1 + wt.tx;
        vertexData[1] = wt.b * w1 + wt.d * h1 + wt.ty;

        vertexData[2] = wt.a * w0 + wt.c * h1 + wt.tx;
        vertexData[3] = wt.b * w0 + wt.d * h1 + wt.ty;

        vertexData[4] = wt.a * w0 + wt.c * h0 + wt.tx;
        vertexData[5] = wt.b * w0 + wt.d * h0 + wt.ty;

        vertexData[6] = wt.a * w1 + wt.c * h0 + wt.tx;
        vertexData[7] = wt.b * w1 + wt.d * h0 + wt.ty;
    }

    override fun objectRenderWebGL(renderer: WebGLRenderer) {
        calculateVertices();

        renderer.setObjectRenderer(renderer.spriteRenderer);
        renderer.spriteRenderer.render(this);
    }
}
