package pixi.textures

import org.w3c.dom.CanvasImageSource
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLVideoElement
import pixi.ScaleMode
import pixi.WrapMode
import pixi.gl.GLTexture
import pixi.math.BitTwiddle

open class BaseTexture(var source: CanvasImageSource? = null, var scaleMode: ScaleMode = ScaleMode.DEFAULT, var resolution: Int = 1) {
    var touched = 0;

    var width = 100;

    var height = 100;

    var realWidth = 100;

    var realHeight = 100;

    var premultipliedAlpha = true;

    var isPowerOfTwo = false;

    var mipmap = false;

    var wrapMode = WrapMode.DEFAULT;

    //TODO: switch it to hashmap

    var _glTexture: GLTexture? = null;

    var _enabled = 0;

    //used for multitexturing
    var _id = 0;

    init {
        val source = source;
        if (source != null) {
            update();
        }
    }

    fun update() {
        val source = source!!;
        if (source is HTMLImageElement) {
            realWidth = source.naturalWidth;
            realHeight = source.naturalHeight;
        } else if (source is HTMLVideoElement) {
            realWidth = source.videoWidth;
            realHeight = source.videoHeight;
        } else if (source is HTMLCanvasElement) {
            realWidth = source.width;
            realHeight = source.height;
        }

        this.width = realWidth / resolution;
        this.height = realHeight / resolution;

        this.isPowerOfTwo = BitTwiddle.isPow2(this.realWidth) && BitTwiddle.isPow2(this.realHeight);
    }
}
