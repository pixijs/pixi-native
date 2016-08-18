package pixi.renderers

import org.khronos.webgl.Uint8Array
import pixi.BlendMode
import pixi.utils.g
import pixi.utils.get
import org.khronos.webgl.WebGLRenderingContext as GL
import pixi.utils.set

class WebGLState(val gl: GL) {
    var activeState = Uint8Array(16)

    var defaultState = Uint8Array(16)

    init {
        defaultState[0] = 1
    }

    val maxAttribs = gl.getParameter(GL.MAX_VERTEX_ATTRIBS) as Int

    val attribState: dynamic = js("({})");

    init {
        //TODO: move it somewhere, may be to JS side
        attribState.attribState = js("[]")
        attribState.tempAttribState = js("[]")
        for (i in 0..maxAttribs - 1) {
            attribState.attribState.push(32)
            attribState.tempAttribState.push(32)
        }
        attribState
    }

    // check we have vao..
    val nativeVaoExtension = gl.getExtension("OES_vertex_array_object") ?:
            gl.getExtension("MOZ_OES_vertex_array_object") ?:
            gl.getExtension("WEBKIT_OES_vertex_array_object")

    fun setState(state: dynamic) {
        setBlend(state[BLEND])
        setDepthTest(state[DEPTH_TEST])
        setFrontFace(state[FRONT_FACE])
        setCullFace(state[CULL_FACE])
        setBlendMode(BlendMode.byCode(state[BLEND_FUNC]))
    }

    fun setBlend(value: Int = 0) {
        if (activeState.g(BLEND) == value) {
            return
        }

        activeState[BLEND] = value

        if (value == 1) {
            gl.enable(GL.BLEND)
        } else {
            gl.disable(GL.BLEND)
        }
    }

    fun setDepthTest(value: Int = 0) {
        if (activeState.g(DEPTH_TEST) == value) {
            return
        }

        activeState[DEPTH_TEST] = value

        if (value == 1) {
            gl.enable(GL.DEPTH_TEST)
        } else {
            gl.disable(GL.DEPTH_TEST)
        }
    }

    fun setFrontFace(value: Int = 0) {
        if (activeState.g(FRONT_FACE) == value) {
            return
        }

        activeState[FRONT_FACE] = value

        if (value == 1) {
            gl.enable(GL.CW)
        } else {
            gl.disable(GL.CW)
        }
    }

    fun setCullFace(value: Int = 0) {
        if (activeState.g(CULL_FACE) == value) {
            return
        }

        activeState[CULL_FACE] = value

        if (value == 1) {
            gl.enable(GL.CULL_FACE)
        } else {
            gl.disable(GL.CULL_FACE)
        }
    }

    fun setBlendMode(value: BlendMode) {
        if (activeState.g(BLEND_FUNC) == value.bcode) {
            return
        }

        activeState[BLEND_FUNC] = value.bcode

        gl.blendFunc(value.glFirstMul, value.glSecondMul)
    }

    fun resetAttributes() {
        for (i in 0..maxAttribs - 1) {
            attribState.tempAttribState[i] = 0
            attribState.attribState[i] = 0
        }

        // im going to assume one is always active for performance reasons.
        for (i in 1..maxAttribs - 1) {
            gl.disableVertexAttribArray(i)
        }
    }

    fun resetToDefault() {
        nativeVaoExtension?.bindVertexArrayOES(null)

        resetAttributes()

        // set active state so we can force overrides of gl state
        for (i in 0..activeState.length - 1) {
            activeState[i] = 32
        }

        gl.pixelStorei(GL.UNPACK_FLIP_Y_WEBGL, 0)

        setState(defaultState)
    }

    companion object {
        val BLEND = 0
        val DEPTH_TEST = 1
        val FRONT_FACE = 2
        val CULL_FACE = 3
        val BLEND_FUNC = 4
    }
}
