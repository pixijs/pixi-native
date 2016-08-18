package pixi.utils

import org.khronos.webgl.*

fun <E> MutableList<E>.removeRange(beginIndex: Int = 0, endIndex: Int = size) {
    val range = endIndex - beginIndex
    if (range < 0) return
    for (i in beginIndex..size - range - 1) {
        set(i, get(i + range))
    }
}


@nativeGetter fun Int32Array.myGet(index: Int): Int? = noImpl
operator fun Int32Array.get(index: Int): Int = this.myGet(index)!!
@nativeSetter operator fun Int32Array.set(index: Int, value: Int?): Unit = noImpl

@nativeGetter fun Int16Array.myGet(index: Int): Int? = noImpl
operator fun Int16Array.get(index: Int): Int = this.myGet(index)!!
@nativeSetter operator fun Int16Array.set(index: Int, value: Int?): Unit = noImpl

@nativeGetter fun Uint8Array.myGet(index: Int): Int? = noImpl
operator fun Uint8Array.get(index: Int): Int = this.myGet(index)!!
@nativeSetter operator fun Uint8Array.set(index: Int, value: Int?): Unit = noImpl

@nativeGetter fun Uint32Array.myGet(index: Int): Int? = noImpl
operator fun Uint32Array.get(index: Int): Int = this.myGet(index)!!
@nativeSetter operator fun Uint32Array.set(index: Int, value: Int?): Unit = noImpl

@nativeGetter fun Uint16Array.myGet(index: Int): Int? = noImpl
operator fun Uint16Array.get(index: Int): Int = this.myGet(index)!!
@nativeSetter operator fun Uint16Array.set(index: Int, value: Int?): Unit = noImpl

@nativeGetter fun Float32Array.myGet(index: Int): Float? = noImpl
operator fun Float32Array.get(index: Int): Float = this.myGet(index)!!
@nativeSetter operator fun Float32Array.set(index: Int, value: Float?): Unit = noImpl