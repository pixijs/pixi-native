package hello

import jquery.*
import org.w3c.dom.HTMLImageElement
import pixi.Color
import pixi.display.Container
import kotlin.browser.document
import kotlin.browser.window

import pixi.renderers.WebGLRenderer
import pixi.sprite.Sprite
import pixi.textures.BaseTexture
import pixi.textures.Texture

val width = 800
val height = 600

val renderer = WebGLRenderer(width, height, { backgroundColor = Color(0x1099bb) })

val files = arrayOf("rabbitv3_ash.png",
        "rabbitv3_batman.png",
        "rabbitv3_bb8.png",
        "rabbitv3_neo.png",
        "rabbitv3_sonic.png",
        "rabbitv3_spidey.png",
        "rabbitv3_stormtrooper.png",
        "rabbitv3_superman.png",
        "rabbitv3_tron.png",
        "rabbitv3_wolverine.png",
        "rabbitv3.png",
        "rabbitv3_frankenstein.png");
val MAX_TEXTURES = Math.min(files.size, renderer.spriteRenderer.MAX_TEXTURES)
val images = Array<HTMLImageElement>(MAX_TEXTURES, { i -> document.createElement("img") as HTMLImageElement })


fun preload() {
    var loaded = 0
    for (i in 0..images.size - 1) {
        images[i].onload =  {
            loaded++
            if (loaded == images.size) {
                window.setTimeout({ init() }, 0)
            }
        }
        images[i].src = "assets/bunny/${files[i]}";
    }
}

var textures = arrayOf<Texture>()
var adding = false
var container = Container()
var count = 0

var gravity = 0.75

var maxX = width * 1f;
var minX = 0f;
var maxY = height * 1f;
var minY = 0f;

fun init() {
    textures = Array<Texture>(MAX_TEXTURES, { i -> Texture(BaseTexture(images[i])) })

    jq(renderer.view).mousedown {
        adding = true
    }.mouseup {
        adding = false
    }

    document.addEventListener("touchstart", { adding = true }, true)
    document.addEventListener("touchend", { adding = false }, true)

    for (i in 0..99) {
        addBunny()
    }

    counter.innerHTML = "$count BUNNIES";

    raf()
}

fun raf() {
    stats.begin()
    update()
    renderer.render(container)
    stats.end()
    window.requestAnimationFrame { raf() }
}

fun addBunny() {
    var bunny = Sprite(textures[Math.floor(Math.random() * MAX_TEXTURES)])


    bunny.js.speedX = Math.random() * 10;
    bunny.js.speedY = (Math.random() * 10) - 5;
    if (Math.random()<0.5) {
        bunny.position.x = width.toFloat()
    }

    bunny.position.y = (Math.random() * height/2).toFloat();

    container.addChild(bunny)

    count++
}

fun update() {
    if (adding) {
        if (count < 100000) {
            for (i in 0..99) {
                addBunny()
            }
        }
        counter.innerHTML = "$count BUNNIES";
    }

    for (i in 0..count - 1) {
        var bunny = container.children[i];
        //bunny.rotation += bunny.js.spin as Float
        var transform = bunny;
        transform.position.x += bunny.js.speedX as Float;
        transform.position.y += bunny.js.speedY as Float;
        bunny.js.speedY += gravity;

        if (transform.position.x > maxX) {
            bunny.js.speedX *= -1;
            transform.position.x = maxX;
        } else if (transform.position.x < minX) {
            bunny.js.speedX *= -1;
            transform.position.x = minX;
        }

        if (transform.position.y > maxY) {
            bunny.js.speedY *= -0.85;
            transform.position.y = maxY;
            bunny.js.spin = (Math.random() - 0.5) * 0.2
            if (Math.random() > 0.5) {
                bunny.js.speedY -= Math.random() * 6;
            }
        } else if (transform.position.y < minY) {
            bunny.js.speedY = 0;
            transform.position.y = minY;
        }
    }
}

var counter = document.createElement("div")

var stats = js("""new Stats()""")

fun main(args: Array<String>) {
    jq {
        val body = document.body!!
        body.appendChild(renderer.view)
        body.appendChild( counter);
        body.appendChild( stats.domElement );
        stats.domElement.style.position = "absolute";
        stats.domElement.style.top = "0px";

        counter.className = "counter";
        preload()
    }
}


