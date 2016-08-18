package pixi.utils

import org.khronos.webgl.Float32Array
import kotlin.browser.window
import pixi.*;

private var _saidHello = false;

fun sayHello(type: String) {
    if (_saidHello) {
        return;
    }

    var args = js("[]");
    args.push("\n %c %c %c Pixi.js $VERSION - ✰ $type ✰  %c  %c   http://www.pixijs.com/  %c %c ♥%c♥%c♥ \n\n")
    args.push("background: #ff66a5; padding:5px 0;");
    args.push("background: #ff66a5; padding:5px 0;");
    args.push("color: #ff66a5; background: #030307; padding:5px 0;");
    args.push("background: #ff66a5; padding:5px 0;");
    args.push("background: #ffc3dc; padding:5px 0;");
    args.push("background: #ff66a5; padding:5px 0;");
    args.push("color: #ff2424; background: #fff; padding:5px 0;");
    args.push("color: #ff2424; background: #fff; padding:5px 0;");
    args.push("color: #ff2424; background: #fff; padding:5px 0;");
    console.asDynamic().log.apply(console, args);

    _saidHello = true;
}
