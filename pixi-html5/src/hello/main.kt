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
import kotlin.dom.addClass
import kotlin.dom.onClick
import kotlin.dom.removeClass

val width = 800
val height = 600
val MAX_BUNNIES = 100000
val INIT_BUNNIES = 100

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
        images[i].onload = {
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

fun init() {
    textures = Array<Texture>(MAX_TEXTURES, { i -> Texture(BaseTexture(images[i])) })

    jq(renderer.view).mousedown {
        adding = true
    }.mouseup {
        adding = false
    }

    document.addEventListener("touchstart", { adding = true }, true)
    document.addEventListener("touchend", { adding = false }, true)

    for (i in 1..INIT_BUNNIES) {
        addBunny()
    }

    counter.innerHTML = "$count BUNNIES";

    updateBtn.onClick {
        pause = !pause
        if (pause) {
            updateBtn.removeClass("green")
            updateBtn.addClass("red")
            updateBtn.innerHTML = "UP FEW"
        } else {
            updateBtn.removeClass("red")
            updateBtn.addClass("green")
            updateBtn.innerHTML = "UP ALL"
        }
    }

    raf()
}

fun raf() {
    stats.begin()
    update()
    renderer.render(container)
    stats.end()
    window.requestAnimationFrame { raf() }
}

//class Bunny(tex: Texture) : Sprite(tex) {
//    var speedX = 0f
//    var speedY = 0f
//    var spin = 0f
//}

//pre-create bunnies?
var bunnies = js("([])")

fun addBunny() {
    if (count >= MAX_BUNNIES) {
        return
    }
    var bunny : dynamic = Sprite(textures[Math.floor(Math.random() * MAX_TEXTURES)])

    bunny.speedX = Math.random() * 10;
    bunny.speedY = (Math.random() * 10) - 5;
    if (Math.random() < 0.5) {
        bunny.position.x = width.toFloat()
    }

    bunny.position.y = (Math.random() * height / 2).toFloat();

    container.addChild(bunny)

    bunnies.push(bunny)

    count++
}

var pause = false;

fun update() {
    var maxX = width * 1f;
    var minX = 0f;
    var maxY = height * 1f;
    var minY = 0f;
    var gravity = 0.75f

    if (adding) {
        if (count < MAX_BUNNIES) {
            for (i in 0..199) {
                addBunny()
            }
        }
        counter.innerHTML = "$count BUNNIES";
    }

    var start = if (pause) count*19/20 else 0

    for (i in start..count - 1) {
        var bunny = bunnies[i]
        //bunny.rotation += bunny.spin as Float
        var transform = bunny.transform
        var position = transform.position
        position.x = position.x + bunny.speedX
        position.y = position.y + bunny.speedY
        bunny.speedY = bunny.speedY + gravity

        if (position.x > maxX) {
            bunny.speedX *= -1;
            position.x = maxX
        } else if (position.x < minX) {
            bunny.speedX *= -1;
            position.x = minX
        }

        if (position.y > maxY) {
            bunny.speedY *= -0.85f;
            position.y = maxY
            bunny.spin = ((Math.random() - 0.5) * 0.2).toFloat()
            if (Math.random() > 0.5) {
                bunny.speedY -= (Math.random() * 6).toFloat();
            }
        } else if (position.y < minY) {
            bunny.speedY = 0f;
            position.y = minY
        }
    }
}

var counter = document.createElement("div")

var stats = js("""new Stats()""")

var updateBtn = document.createElement("div")

fun main(args: Array<String>) {
    jq {
        val body = document.body!!
        body.appendChild(renderer.view)
        body.appendChild(counter);
        body.appendChild(updateBtn);
        body.appendChild(stats.domElement);
        stats.domElement.style.position = "absolute";
        stats.domElement.style.top = "0px";

        counter.className = "counter"

        updateBtn.className = "update-btn"
        updateBtn.addClass("green")
        updateBtn.innerHTML = "UP ALL"

        preload()
    }
}


