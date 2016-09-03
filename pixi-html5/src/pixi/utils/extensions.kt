package pixi.utils

import org.khronos.webgl.*

fun <E> MutableList<E>.removeRange(beginIndex: Int = 0, endIndex: Int = size) {
    val range = endIndex - beginIndex
    if (range < 0) return
    for (i in beginIndex..size - range - 1) {
        set(i, get(i + range))
    }
}

inline operator fun Uint16Array.set(index: Int, value: Int) {
    this[index] = value.toShort()
}

inline operator fun Int16Array.set(index: Int, value: Int) {
    this[index] = value.toShort()
}
