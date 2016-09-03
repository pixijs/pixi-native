package pixi.renderers

import pixi.Color
import pixi.Destroyable
import pixi.ScaleMode
import pixi.gl.GLFrameBuffer
import pixi.gl.GLTexture
import pixi.math.Frame
import pixi.math.Matrix
import org.khronos.webgl.WebGLRenderingContext as GL;

class RenderTarget(val gl: GL, var width: Int, var height: Int,
                   val scaleMode: ScaleMode = ScaleMode.DEFAULT, val resolution: Float = 1f, val root: Boolean = false) : Destroyable {

    var frameBuffer: GLFrameBuffer? = null;

    val texture: GLTexture?
        get() = frameBuffer?.texture

    var clearColor = Color(0f, 0f, 0f, 0f);

    val size = Frame(0, 0, 1, 1);

    var transform: Matrix? = null;

    val projectionMatrix = Matrix();

    var frame: Frame? = null;

    //TODO: refactor this mess
    var defaultFrame = Frame();
    var destinationFrame: Frame? = null;
    var sourceFrame: Frame? = null;

    fun setFrames(destinationFrame: Frame? = null, sourceFrame: Frame? = null) {
        this.destinationFrame = destinationFrame ?: this.destinationFrame ?: this.defaultFrame;
        this.sourceFrame = sourceFrame ?: this.sourceFrame ?: destinationFrame;
    }

    init {
        if (!root) {
            val _frameBuffer = GLFrameBuffer.createRGBA(gl, 100, 100)
            frameBuffer = _frameBuffer;

            if (scaleMode == ScaleMode.NEAREST) {
                _frameBuffer.texture!!.enableNearestScaling();
            } else {
                _frameBuffer.texture!!.enableLinearScaling();
            }
        } else {
            val _frameBuffer = GLFrameBuffer(gl, 100, 100);
            _frameBuffer.framebuffer = null;
            frameBuffer = _frameBuffer;
        }

        this.setFrames();
        resize(width, height);
    }


    fun activate() {
        // make surethe texture is unbound!
        this.frameBuffer?.bind();

        val destinationFrame = destinationFrame!!;

        this.calculateProjection(destinationFrame, sourceFrame);

        //TODO: this is bad. remove this thing, change sourceFrame instead
        val transform = this.transform;
        if (transform != null) {
            this.projectionMatrix.append(transform);
        }

        //TODO add a check as them may be the same!
        if (this.destinationFrame !== this.sourceFrame) {
            gl.enable(GL.SCISSOR_TEST);
            gl.scissor(Math.floor(destinationFrame.x),
                    Math.floor(destinationFrame.y),
                    Math.floor(destinationFrame.width * this.resolution),
                    Math.floor(destinationFrame.height * this.resolution));
        } else {
            gl.disable(GL.SCISSOR_TEST);
        }

        gl.viewport(Math.floor(destinationFrame.x),
                Math.floor(destinationFrame.y),
                Math.floor(destinationFrame.width * this.resolution),
                Math.floor(destinationFrame.height * this.resolution));
    };

    fun resize(width: Int, height: Int) {
        if (size.width == width && size.height == height) {
            return;
        }
        size.width = width;
        size.height = height;

        defaultFrame.width = width;
        defaultFrame.height = height;

        frameBuffer?.resize(Math.floor(width * resolution), Math.floor(height * resolution));
        calculateProjection(frame ?: size);
    }

    fun calculateProjection(destinationFrame: Frame, sourceFrame: Frame? = null) {
        var pm = this.projectionMatrix;

        val _sourceFrame = sourceFrame ?: destinationFrame;

        pm.identity();

        // TODO: make dest scale source
        if (!this.root) {
            pm.a = 1.0 / destinationFrame.width * 2;
            pm.d = 1.0 / destinationFrame.height * 2;

            pm.tx = -1 - _sourceFrame.x * pm.a;
            pm.ty = -1 - _sourceFrame.y * pm.d;
        } else {
            pm.a = 1.0 / destinationFrame.width * 2;
            pm.d = -1.0 / destinationFrame.height * 2;

            pm.tx = -1 - _sourceFrame.x * pm.a;
            pm.ty = 1 - _sourceFrame.y * pm.d;
        }
    }

    fun clear(clearColor: Color? = null) {
        val rgba = (clearColor ?: this.clearColor).rgba
        frameBuffer?.clear(rgba[0], rgba[1], rgba[2], rgba[3])
    }

    override var isDestroyed = false;

    override fun destroy() {
        frameBuffer?.destroy();
        frameBuffer = null;
        super.destroy();
    }
}
