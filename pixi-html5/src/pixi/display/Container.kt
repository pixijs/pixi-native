package pixi.display

import pixi.utils.removeRange
import pixi.renderers.WebGLRenderer

open class Container : DisplayObject() {

    private var _children = mutableListOf<DisplayObject>();

    override val children: MutableList<DisplayObject>
        get() = _children;


    fun addChild(child: DisplayObject) {
        // if the child has a parent then lets remove it as Pixi objects can only exist in one place
        child.parent?.removeChild(child);
        child.parent = this;

        // ensure a transform will be recalculated..
        child.transform._parentID = -1

        children.add(child);

//        this.onChildrenChange(this.children.length - 1);
//        child.emit('added', this);
    }

    fun removeChild(child: DisplayObject) {
        var index = this.children.indexOf(child);

        if (index === -1)
        {
            return;
        }

        child.parent = null
        child.transform._parentID = -1
        children.removeAt(index)
        // TODO - lets either do all callbacks or all events.. not both!
//        this.onChildrenChange(index);
//        child.emit('removed', this);
    }

    fun removeChildren(beginIndex: Int = 0, endIndex : Int = children.size) {
        for (i in beginIndex..endIndex-1) {
            children[i].parent = null
            children[i].transform._parentID = -1
        }
        children.removeRange(beginIndex, endIndex)
    }


    override fun updateTransform() {
        if (!this.visible) {
            return;
        }
        objectUpdateTransform();
        for (i in 0..children.size - 1) {
            children[i].updateTransform();
        }
    }

    override fun renderWebGL(renderer: WebGLRenderer) {

        if (!this.visible || this.worldMulColor._alpha <= 0f || !this.renderable) {
            return
        }
//        if (!this.visible || this.worldAlpha <= 0f || !this.renderable) {
//            return;
//        }

        objectRenderWebGL(renderer);
        for (i in 0..children.size - 1) {
            children[i].renderWebGL(renderer);
        }
    }
}
