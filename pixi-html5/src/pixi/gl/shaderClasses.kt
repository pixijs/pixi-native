package pixi.gl

import org.khronos.webgl.WebGLUniformLocation

@native class Attribute {
    private constructor()

    val type: String
        get() = noImpl

    val size: Int
        get() = noImpl

    val location: Int
        get() = noImpl
}

@native class Uniform {
    private constructor()

    val type: String
        get() = noImpl

    val size: Int
        get() = noImpl

    val location: WebGLUniformLocation
        get() = noImpl

    val value: Any
        get() = noImpl
}

@native class AttributeMap {
    private constructor();

    @nativeGetter
    operator fun get(name: String): Attribute?
}

@native class UniformMap {
    private constructor();

    @nativeGetter
    operator fun get(name: String): Uniform?
}

@native class VertexArrayAttribute {
    private constructor();

    val buffer: GLBuffer
        get() = noImpl

    val attribute: Attribute
        get() = noImpl

    val type: Int
        get() = noImpl

    val normalized: Boolean
        get() = noImpl

    val stride: Int
        get() = noImpl

    val start: Int
        get() = noImpl
};

@native class VertexAttributeArray {
    private constructor();

    @nativeGetter
    fun get(i: Int): VertexArrayAttribute?
}
