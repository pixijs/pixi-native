package pixi.gl

import org.khronos.webgl.*
import org.w3c.dom.*
import org.khronos.webgl.WebGLRenderingContext as GL

@native(name="PIXI.glCore.GLTexture") public class GLTexture {
    constructor(gl: GL, width: Int = noImpl, height: Int = noImpl, format: Int = noImpl, type: Int = noImpl)

    val gl: GL
        get() = noImpl

    var mipmap: Boolean
        get() = noImpl
        set(value: Boolean) = noImpl

    var premultiplyAlpha: Boolean
        get() = noImpl
        set(value: Boolean) = noImpl

    val width: Int
        get() = noImpl

    val height: Int
        get() = noImpl

    val format: Int
        get() = noImpl

    val type: Int
        get() = noImpl

    fun upload(source: CanvasImageSource): Unit = noImpl
    fun uploadData(data: ArrayBuffer, width: Int, height: Int): Unit = noImpl
    fun uploadData(data: ArrayBufferView, width: Int, height: Int): Unit = noImpl
    fun bind(): Unit = noImpl
    fun bind(location: Int): Unit = noImpl
    fun unbind(): Unit = noImpl
    fun minFilter(linear: Boolean): Unit = noImpl
    fun magFilter(linear: Boolean): Unit = noImpl
    fun enableMipmap(): Unit = noImpl
    fun enableLinearScaling(): Unit = noImpl
    fun enableNearestScaling(): Unit = noImpl
    fun enableWrapClamp(): Unit = noImpl
    fun enableWrapRepeat(): Unit = noImpl
    fun enableWrapMirrorRepeat(): Unit = noImpl
    fun destroy(): Unit = noImpl

    companion object {
        fun fromSource(gl: GL, source: CanvasImageSource, premultiplyAlpha: Boolean = noImpl): Unit = noImpl
        fun fromData(gl: GL, data: ArrayBuffer, width: Int, height: Int): Unit = noImpl
        fun fromData(gl: GL, data: ArrayBufferView, width: Int, height: Int): Unit = noImpl
    }
}
