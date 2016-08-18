var pixi = function (Kotlin) {
  'use strict';
  var _ = Kotlin.defineRootPackage(null, /** @lends _ */ {
    hello: Kotlin.definePackage(function () {
      this.width = 800;
      this.height = 600;
      this.renderer = _.pixi.renderers.WebGLRenderer_init_2yge5z$(_.hello.width, _.hello.height, _.hello.renderer$f);
      this.files = ['rabbitv3_ash.png', 'rabbitv3_batman.png', 'rabbitv3_bb8.png', 'rabbitv3_neo.png', 'rabbitv3_sonic.png', 'rabbitv3_spidey.png', 'rabbitv3_stormtrooper.png', 'rabbitv3_superman.png', 'rabbitv3_tron.png', 'rabbitv3_wolverine.png', 'rabbitv3.png', 'rabbitv3_frankenstein.png'];
      this.MAX_TEXTURES = Math.min(_.hello.files.length, _.hello.renderer.spriteRenderer.MAX_TEXTURES);
      this.images = Kotlin.arrayFromFun(_.hello.MAX_TEXTURES, _.hello.images$f);
      this.textures = [];
      this.adding = false;
      this.container = new _.pixi.display.Container();
      this.count = 0;
      this.gravity = 0.75;
      this.maxX = _.hello.width * 1.0;
      this.minX = 0.0;
      this.maxY = _.hello.height * 1.0;
      this.minY = 0.0;
      this.counter = document.createElement('div');
      this.stats = new Stats();
    }, /** @lends _.hello */ {
      renderer$f: function () {
        this.backgroundColor = _.pixi.Color_init_za3lpa$(1087931);
      },
      images$f: function (i) {
        var tmp$0;
        return Kotlin.isType(tmp$0 = document.createElement('img'), HTMLImageElement) ? tmp$0 : Kotlin.throwCCE();
      },
      f: function () {
        _.hello.init();
      },
      preload$f: function (closure$loaded) {
        return function (it) {
          closure$loaded.v++;
          if (closure$loaded.v === _.hello.images.length) {
            window.setTimeout(_.hello.f, 0);
          }
        };
      },
      preload: function () {
        var tmp$0;
        var loaded = {v: 0};
        tmp$0 = _.hello.images.length - 1;
        for (var i = 0; i <= tmp$0; i++) {
          _.hello.images[i].onload = _.hello.preload$f(loaded);
          _.hello.images[i].src = 'assets/bunny/' + _.hello.files[i];
        }
      },
      init$f: function (i) {
        return new _.pixi.textures.Texture(new _.pixi.textures.BaseTexture(_.hello.images[i]));
      },
      init$f_0: function (it) {
        _.hello.adding = true;
      },
      init$f_1: function (it) {
        _.hello.adding = false;
      },
      init$f_2: function (it) {
        _.hello.adding = true;
      },
      init$f_3: function (it) {
        _.hello.adding = false;
      },
      init: function () {
        var tmp$0;
        _.hello.textures = Kotlin.arrayFromFun(_.hello.MAX_TEXTURES, _.hello.init$f);
        $(_.hello.renderer.view).mousedown(_.hello.init$f_0).mouseup(_.hello.init$f_1);
        document.addEventListener('touchstart', _.hello.init$f_2, true);
        document.addEventListener('touchend', _.hello.init$f_3, true);
        tmp$0 = 99;
        for (var i = 0; i <= tmp$0; i++) {
          _.hello.addBunny();
        }
        _.hello.counter.innerHTML = _.hello.count.toString() + ' BUNNIES';
        _.hello.raf();
      },
      raf$f: function (it) {
        _.hello.raf();
      },
      raf: function () {
        _.hello.stats.begin();
        _.hello.update();
        _.hello.renderer.render_e5kpu6$(_.hello.container);
        _.hello.stats.end();
        window.requestAnimationFrame(_.hello.raf$f);
      },
      addBunny: function () {
        var bunny = new _.pixi.sprite.Sprite(_.hello.textures[Math.floor(Math.random() * _.hello.MAX_TEXTURES)]);
        bunny.js.speedX = Math.random() * 10;
        bunny.js.speedY = Math.random() * 10 - 5;
        if (Math.random() < 0.5) {
          bunny.position.x = _.hello.width;
        }
        bunny.position.y = Math.random() * _.hello.height / 2;
        _.hello.container.addChild_ps4ljl$(bunny);
        _.hello.count++;
      },
      update: function () {
        var tmp$0, tmp$1, tmp$2, tmp$3;
        if (_.hello.adding) {
          if (_.hello.count < 100000) {
            tmp$0 = 99;
            for (var i = 0; i <= tmp$0; i++) {
              _.hello.addBunny();
            }
          }
          _.hello.counter.innerHTML = _.hello.count.toString() + ' BUNNIES';
        }
        tmp$1 = _.hello.count - 1;
        for (var i_0 = 0; i_0 <= tmp$1; i_0++) {
          var bunny = _.hello.container.children.get_za3lpa$(i_0);
          var transform = bunny;
          transform.position.x = transform.position.x + (typeof (tmp$2 = bunny.js.speedX) === 'number' ? tmp$2 : Kotlin.throwCCE());
          transform.position.y = transform.position.y + (typeof (tmp$3 = bunny.js.speedY) === 'number' ? tmp$3 : Kotlin.throwCCE());
          bunny.js.speedY += _.hello.gravity;
          if (transform.position.x > _.hello.maxX) {
            bunny.js.speedX *= -1;
            transform.position.x = _.hello.maxX;
          }
           else if (transform.position.x < _.hello.minX) {
            bunny.js.speedX *= -1;
            transform.position.x = _.hello.minX;
          }
          if (transform.position.y > _.hello.maxY) {
            bunny.js.speedY *= -0.85;
            transform.position.y = _.hello.maxY;
            bunny.js.spin = (Math.random() - 0.5) * 0.2;
            if (Math.random() > 0.5) {
              bunny.js.speedY -= Math.random() * 6;
            }
          }
           else if (transform.position.y < _.hello.minY) {
            bunny.js.speedY = 0;
            transform.position.y = _.hello.minY;
          }
        }
      },
      main_kand9s$f: function () {
        var tmp$0;
        var body = (tmp$0 = document.body) != null ? tmp$0 : Kotlin.throwNPE();
        body.appendChild(_.hello.renderer.view);
        body.appendChild(_.hello.counter);
        body.appendChild(_.hello.stats.domElement);
        _.hello.stats.domElement.style.position = 'absolute';
        _.hello.stats.domElement.style.top = '0px';
        _.hello.counter.className = 'counter';
        _.hello.preload();
      },
      main_kand9s$: function (args) {
        $(_.hello.main_kand9s$f);
      }
    }),
    pixi: Kotlin.definePackage(function () {
      this.hexStr_nsqiau$ = '0123456789abcdef';
      this.VERSION = '0.0.1';
    }, /** @lends _.pixi */ {
      BlendMode: Kotlin.createEnumClass(function () {
        return [Kotlin.Enum];
      }, function $fun(bcode, canvasName, glFirstMul, glSecondMul) {
        if (glFirstMul === void 0)
          glFirstMul = WebGLRenderingContext.ONE;
        if (glSecondMul === void 0)
          glSecondMul = WebGLRenderingContext.ONE_MINUS_SRC_ALPHA;
        $fun.baseInitializer.call(this);
        this.bcode = bcode;
        this.canvasName = canvasName;
        this.glFirstMul = glFirstMul;
        this.glSecondMul = glSecondMul;
      }, function () {
        return {
          NORMAL: function () {
            return new _.pixi.BlendMode(0, 'source-over');
          },
          ADD: function () {
            return new _.pixi.BlendMode(1, 'lighter', WebGLRenderingContext.ONE, WebGLRenderingContext.DST_ALPHA);
          },
          MULTIPLY: function () {
            return new _.pixi.BlendMode(2, 'multiply', WebGLRenderingContext.DST_COLOR, WebGLRenderingContext.ONE_MINUS_SRC_ALPHA);
          },
          SCREEN: function () {
            return new _.pixi.BlendMode(3, 'screen', WebGLRenderingContext.ONE, WebGLRenderingContext.ONE_MINUS_SRC_COLOR);
          },
          OVERLAY: function () {
            return new _.pixi.BlendMode(4, 'overlay');
          },
          DARKEN: function () {
            return new _.pixi.BlendMode(5, 'darken');
          },
          LIGHTEN: function () {
            return new _.pixi.BlendMode(6, 'lighten');
          },
          COLOR_DODGE: function () {
            return new _.pixi.BlendMode(7, 'color-dodge');
          },
          COLOR_BURN: function () {
            return new _.pixi.BlendMode(8, 'color-burn');
          },
          HARD_LIGHT: function () {
            return new _.pixi.BlendMode(9, 'hard-light');
          },
          SOFT_LIGHT: function () {
            return new _.pixi.BlendMode(10, 'soft-light');
          },
          DIFFERENCE: function () {
            return new _.pixi.BlendMode(11, 'difference');
          },
          EXCLUSION: function () {
            return new _.pixi.BlendMode(12, 'exclusion');
          },
          HUE: function () {
            return new _.pixi.BlendMode(13, 'hue');
          },
          SATURATION: function () {
            return new _.pixi.BlendMode(14, 'saturate');
          },
          COLOR: function () {
            return new _.pixi.BlendMode(15, 'color');
          },
          LUMINOSITY: function () {
            return new _.pixi.BlendMode(16, 'luminosity');
          }
        };
      }, null, /** @lends _.pixi.BlendMode */ {
        Companion: Kotlin.createObject(null, function () {
          _.pixi.BlendMode.Companion.values_sxgrqa$ = _.pixi.BlendMode.values();
        }, /** @lends _.pixi.BlendMode.Companion.prototype */ {
          byCode_za3lpa$: function (code) {
            return _.pixi.BlendMode.Companion.values_sxgrqa$[code];
          }
        }),
        object_initializer$: function () {
          _.pixi.BlendMode.Companion;
        }
      }),
      Color: Kotlin.createClass(null, function () {
        this._rgba_izyxo$ = new Float32Array(Kotlin.kotlin.collections.toTypedArray_rjqrz0$([1.0, 1.0, 1.0, 1.0]));
        this._rgbInt_9yflym$ = -1;
        this._alpha_gm83iq$ = 1.0;
      }, /** @lends _.pixi.Color.prototype */ {
        rgbInt: {
          get: function () {
            return this._rgbInt_9yflym$;
          },
          set: function (value) {
            this._rgbInt_9yflym$ = value;
            this._rgba_izyxo$[0] = (value >> 16 & 255) / 255.0;
            this._rgba_izyxo$[1] = (value >> 8 & 255) / 255.0;
            this._rgba_izyxo$[2] = (value & 255) / 255.0;
          }
        },
        rgbaInt: {
          get: function () {
            return this._rgbInt_9yflym$ | Math.floor(this._alpha_gm83iq$ * 255) << 24;
          }
        },
        rgba: {
          get: function () {
            return this._rgba_izyxo$;
          },
          set: function (value) {
            this._rgbInt_9yflym$ = Math.floor(_.pixi.utils.get_25qnj7$(value, 0) * 255.0) << 16 | Math.floor(_.pixi.utils.get_25qnj7$(value, 1) * 255.0) << 8 | Math.floor(_.pixi.utils.get_25qnj7$(value, 2) * 255.0);
            this._alpha_gm83iq$ = _.pixi.utils.get_25qnj7$(value, 3);
          }
        },
        alpha: {
          get: function () {
            return this._alpha_gm83iq$;
          },
          set: function (value) {
            this._alpha_gm83iq$ = value;
            this._rgba_izyxo$[3] = value;
          }
        },
        r: {
          get: function () {
            return _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 0);
          }
        },
        g: {
          get: function () {
            return _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 1);
          }
        },
        b: {
          get: function () {
            return _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 1);
          }
        },
        a: {
          get: function () {
            return _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 1);
          }
        },
        setVec4_7b5o5w$: function (r, g, b, a) {
          this._rgba_izyxo$[0] = r;
          this._rgba_izyxo$[1] = g;
          this._rgba_izyxo$[2] = b;
          this._rgba_izyxo$[3] = a;
          this._rgbInt_9yflym$ = Math.floor(r * 255.0) << 16 | Math.floor(g * 255.0) << 8 | Math.floor(b * 255.0);
          this._alpha_gm83iq$ = a;
        },
        rgbHex: {
          get: function () {
            return '#' + _.pixi.hexStr_nsqiau$.charAt(this._rgbInt_9yflym$ << 20 & 15) + _.pixi.hexStr_nsqiau$.charAt(this._rgbInt_9yflym$ << 16 & 15) + _.pixi.hexStr_nsqiau$.charAt(this._rgbInt_9yflym$ << 12 & 15) + _.pixi.hexStr_nsqiau$.charAt(this._rgbInt_9yflym$ << 8 & 15) + _.pixi.hexStr_nsqiau$.charAt(this._rgbInt_9yflym$ << 4 & 15) + _.pixi.hexStr_nsqiau$.charAt(this._rgbInt_9yflym$ & 15);
          },
          set: function (value) {
            var tmp$0;
            this._rgbInt_9yflym$ = 0;
            tmp$0 = 6;
            for (var i = 1; i <= tmp$0; i++) {
              if (value.charAt(i) >= '0' && value.charAt(i) <= '9') {
                this._rgbInt_9yflym$ = this._rgbInt_9yflym$ | value.charAt(i).charCodeAt(0) - '0'.charCodeAt(0) << i * 4;
              }
               else if (value.charAt(i) >= 'A' && value.charAt(i) <= 'F') {
                this._rgbInt_9yflym$ = this._rgbInt_9yflym$ | value.charAt(i).charCodeAt(0) - 'A'.charCodeAt(0) << i * 4;
              }
               else if (value.charAt(i) >= 'a' && value.charAt(i) <= 'f') {
                this._rgbInt_9yflym$ = this._rgbInt_9yflym$ | value.charAt(i).charCodeAt(0) - 'a'.charCodeAt(0) << i * 4;
              }
            }
            this.rgbInt = this._rgbInt_9yflym$;
          }
        },
        copy_sgj01r$: function (clr) {
          this._rgba_izyxo$[0] = _.pixi.utils.get_25qnj7$(clr._rgba_izyxo$, 0);
          this._rgba_izyxo$[1] = _.pixi.utils.get_25qnj7$(clr._rgba_izyxo$, 1);
          this._rgba_izyxo$[2] = _.pixi.utils.get_25qnj7$(clr._rgba_izyxo$, 2);
          this._rgba_izyxo$[3] = _.pixi.utils.get_25qnj7$(clr._rgba_izyxo$, 3);
          this._alpha_gm83iq$ = clr._alpha_gm83iq$;
          this._rgbInt_9yflym$ = clr._rgbInt_9yflym$;
        },
        mul_sgj01r$: function (color) {
          if (color._rgbInt_9yflym$ === 16777215 && color._alpha_gm83iq$ === 1.0)
            return;
          this._rgba_izyxo$[0] = _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 0) * _.pixi.utils.get_25qnj7$(color._rgba_izyxo$, 0);
          this._rgba_izyxo$[1] = _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 1) * _.pixi.utils.get_25qnj7$(color._rgba_izyxo$, 1);
          this._rgba_izyxo$[2] = _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 2) * _.pixi.utils.get_25qnj7$(color._rgba_izyxo$, 2);
          this._rgba_izyxo$[3] = _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 3) * _.pixi.utils.get_25qnj7$(color._rgba_izyxo$, 3);
          this._rgbInt_9yflym$ = Math.floor(_.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 0) * 255.0) << 16 | Math.floor(_.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 1) * 255.0) << 8 | Math.floor(_.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 2) * 255.0);
          this._alpha_gm83iq$ = _.pixi.utils.get_25qnj7$(this._rgba_izyxo$, 3);
        }
      }),
      Color_init_za3lpa$: function (rgb, $this) {
        $this = $this || Object.create(_.pixi.Color.prototype);
        _.pixi.Color.call($this);
        $this.rgbInt = rgb;
        return $this;
      },
      Color_init_7b5o5w$: function (r, g, b, a, $this) {
        $this = $this || Object.create(_.pixi.Color.prototype);
        _.pixi.Color.call($this);
        $this.setVec4_7b5o5w$(r, g, b, a);
        return $this;
      },
      Color_init_sgj01r$: function (clr, $this) {
        $this = $this || Object.create(_.pixi.Color.prototype);
        _.pixi.Color.call($this);
        $this.copy_sgj01r$(clr);
        return $this;
      },
      Color_init_61zpoe$: function (css, $this) {
        $this = $this || Object.create(_.pixi.Color.prototype);
        _.pixi.Color.call($this);
        if (css.charAt(0) === '#') {
          if (css.length === 7) {
            $this.rgbHex = css;
          }
        }
        return $this;
      },
      RendererType: Kotlin.createEnumClass(function () {
        return [Kotlin.Enum];
      }, function $fun(systemName) {
        $fun.baseInitializer.call(this);
        this.systemName = systemName;
      }, function () {
        return {
          UNKNOWN: function () {
            return new _.pixi.RendererType('Unknown');
          },
          WEBGL: function () {
            return new _.pixi.RendererType('WebGL');
          },
          CANVAS: function () {
            return new _.pixi.RendererType('Canvas');
          }
        };
      }),
      ScaleMode: Kotlin.createEnumClass(function () {
        return [Kotlin.Enum];
      }, function $fun() {
        $fun.baseInitializer.call(this);
      }, function () {
        return {
          NEAREST: function () {
            return new _.pixi.ScaleMode();
          },
          LINEAR: function () {
            return new _.pixi.ScaleMode();
          }
        };
      }, null, /** @lends _.pixi.ScaleMode */ {
        Companion: Kotlin.createObject(null, function () {
          _.pixi.ScaleMode.Companion.DEFAULT = _.pixi.ScaleMode.NEAREST;
        }),
        object_initializer$: function () {
          _.pixi.ScaleMode.Companion;
        }
      }),
      Precision: Kotlin.createEnumClass(function () {
        return [Kotlin.Enum];
      }, function $fun(glsl) {
        $fun.baseInitializer.call(this);
        this.glsl = glsl;
      }, function () {
        return {
          LOW: function () {
            return new _.pixi.Precision('lowp');
          },
          MEDIUM: function () {
            return new _.pixi.Precision('mediump');
          },
          HIGH: function () {
            return new _.pixi.Precision('highp');
          }
        };
      }, null, /** @lends _.pixi.Precision */ {
        Companion: Kotlin.createObject(null, function () {
          _.pixi.Precision.Companion.DEFAULT = _.pixi.Precision.MEDIUM;
        }),
        object_initializer$: function () {
          _.pixi.Precision.Companion;
        }
      }),
      WrapMode: Kotlin.createEnumClass(function () {
        return [Kotlin.Enum];
      }, function $fun() {
        $fun.baseInitializer.call(this);
      }, function () {
        return {
          CLAMP: function () {
            return new _.pixi.WrapMode();
          },
          REPEAT: function () {
            return new _.pixi.WrapMode();
          },
          MIRRORED_REPEAT: function () {
            return new _.pixi.WrapMode();
          }
        };
      }, null, /** @lends _.pixi.WrapMode */ {
        Companion: Kotlin.createObject(null, function () {
          _.pixi.WrapMode.Companion.DEFAULT = _.pixi.WrapMode.CLAMP;
        }),
        object_initializer$: function () {
          _.pixi.WrapMode.Companion;
        }
      }),
      GcMode: Kotlin.createEnumClass(function () {
        return [Kotlin.Enum];
      }, function $fun() {
        $fun.baseInitializer.call(this);
      }, function () {
        return {
          MANUAL: function () {
            return new _.pixi.GcMode();
          },
          AUTO: function () {
            return new _.pixi.GcMode();
          }
        };
      }, null, /** @lends _.pixi.GcMode */ {
        Companion: Kotlin.createObject(null, function () {
          _.pixi.GcMode.Companion.DEFAULT = _.pixi.GcMode.AUTO;
        }),
        object_initializer$: function () {
          _.pixi.GcMode.Companion;
        }
      }),
      Destroyable: Kotlin.createTrait(null, /** @lends _.pixi.Destroyable.prototype */ {
        destroy: function () {
          this.isDestroyed = true;
        }
      }),
      display: Kotlin.definePackage(null, /** @lends _.pixi.display */ {
        Container: Kotlin.createClass(function () {
          return [_.pixi.display.DisplayObject];
        }, function $fun() {
          $fun.baseInitializer.call(this);
          this._children_4nmtgh$ = Kotlin.kotlin.collections.mutableListOf_9mqe4v$([]);
        }, /** @lends _.pixi.display.Container.prototype */ {
          children: {
            get: function () {
              return this._children_4nmtgh$;
            }
          },
          addChild_ps4ljl$: function (child) {
            var tmp$0;
            (tmp$0 = child.parent) != null ? tmp$0.removeChild_ps4ljl$(child) : null;
            child.parent = this;
            child.transform._parentID = -1;
            this.children.add_za3rmp$(child);
          },
          removeChild_ps4ljl$: function (child) {
            var index = this.children.indexOf_za3rmp$(child);
            if (index === -1) {
              return;
            }
            child.parent = null;
            child.transform._parentID = -1;
            this.children.removeAt_za3lpa$(index);
          },
          removeChildren_vux9f0$: function (beginIndex, endIndex) {
            var tmp$0;
            if (beginIndex === void 0)
              beginIndex = 0;
            if (endIndex === void 0)
              endIndex = this.children.size;
            tmp$0 = endIndex - 1;
            for (var i = beginIndex; i <= tmp$0; i++) {
              this.children.get_za3lpa$(i).parent = null;
              this.children.get_za3lpa$(i).transform._parentID = -1;
            }
            _.pixi.utils.removeRange_125sxr$(this.children, beginIndex, endIndex);
          },
          updateTransform: function () {
            var tmp$0;
            if (!this.visible) {
              return;
            }
            this.objectUpdateTransform();
            tmp$0 = this.children.size - 1;
            for (var i = 0; i <= tmp$0; i++) {
              this.children.get_za3lpa$(i).updateTransform();
            }
          },
          renderWebGL_75dbqe$: function (renderer) {
            var tmp$0;
            if (!this.visible || this.worldAlpha <= 0.0 || !this.renderable) {
              return;
            }
            this.objectRenderWebGL_75dbqe$(renderer);
            tmp$0 = this.children.size - 1;
            for (var i = 0; i <= tmp$0; i++) {
              this.children.get_za3lpa$(i).renderWebGL_75dbqe$(renderer);
            }
          }
        }),
        DisplayObject: Kotlin.createClass(null, function () {
          this.transform = new _.pixi.display.Transform();
          this.mulColor = new _.pixi.Color();
          this.worldMulColor = new _.pixi.Color();
          this.visible = true;
          this.renderable = true;
          this.parent = null;
          this.js = {};
        }, /** @lends _.pixi.display.DisplayObject.prototype */ {
          children: {
            get: function () {
              return null;
            }
          },
          position: {
            get: function () {
              return this.transform.position;
            },
            set: function (value) {
              this.transform.position.copy_i97z8y$(value);
            }
          },
          scale: {
            get: function () {
              return this.transform.scale;
            },
            set: function (value) {
              this.transform.scale.copy_i97z8y$(value);
            }
          },
          skew: {
            get: function () {
              return this.transform.skew;
            },
            set: function (value) {
              this.transform.skew.copy_i97z8y$(value);
            }
          },
          pivot: {
            get: function () {
              return this.transform.pivot;
            },
            set: function (value) {
              this.transform.pivot.copy_i97z8y$(value);
            }
          },
          x: {
            get: function () {
              return this.transform.position.x;
            },
            set: function (value) {
              this.transform.position.x = value;
            }
          },
          y: {
            get: function () {
              return this.transform.position.y;
            },
            set: function (value) {
              this.transform.position.y = value;
            }
          },
          localTransform: {
            get: function () {
              return this.transform.localTransform;
            }
          },
          worldTransform: {
            get: function () {
              return this.transform.worldTransform;
            }
          },
          alpha: {
            get: function () {
              return this.mulColor.alpha;
            },
            set: function (value) {
              this.mulColor.alpha = value;
            }
          },
          tint: {
            get: function () {
              return this.mulColor.rgbInt;
            },
            set: function (value) {
              this.mulColor.rgbInt = value;
            }
          },
          worldAlpha: {
            get: function () {
              return this.worldMulColor.alpha;
            }
          },
          worldTint: {
            get: function () {
              return this.worldMulColor.rgbInt;
            }
          },
          objectUpdateTransform: function () {
            var tmp$0;
            var _parent = this.parent;
            this.transform.updateTransform_ahtr0$((tmp$0 = _parent != null ? _parent.transform : null) != null ? tmp$0 : _.pixi.display.Transform.Companion.IDENTITY);
            if (_parent != null) {
              this.worldMulColor.copy_sgj01r$(this.mulColor);
              this.worldMulColor.mul_sgj01r$(_parent.worldMulColor);
            }
          },
          objectRenderWebGL_75dbqe$: function (renderer) {
          },
          updateTransform: function () {
            this.objectUpdateTransform();
          },
          renderWebGL_75dbqe$: function (renderer) {
            this.objectRenderWebGL_75dbqe$(renderer);
          }
        }),
        Transform: Kotlin.createClass(function () {
          return [_.pixi.math.Versionable];
        }, function () {
          this.worldTransform = new _.pixi.math.Matrix();
          this.localTransform = new _.pixi.math.Matrix();
          this._worldID = 0;
          this._parentID = -1;
          this._localID = 0;
          this._currentLocalID = 0;
          this.position = new _.pixi.math.ObservablePoint(this);
          this.scale = new _.pixi.math.ObservablePoint(this, 1.0, 1.0);
          this.pivot = new _.pixi.math.ObservablePoint(this);
          this.skew = new _.pixi.math.ObservablePoint(this);
          this._rotation_nof8ej$ = 0.0;
          this._sr_tgry0c$ = 0.0;
          this._cr_tgrxmk$ = 1.0;
          this._cy_tgrxmr$ = 1.0;
          this._sy_tgry0j$ = 0.0;
          this._nsx_9yeize$ = 0.0;
          this._cx_tgrxmq$ = 1.0;
        }, /** @lends _.pixi.display.Transform.prototype */ {
          invalidate: function () {
            this._localID++;
          },
          invalidateParent: function () {
            this._parentID = -1;
          },
          rotation_kjujlc$: {
            get: function () {
              return this._rotation_nof8ej$;
            },
            set: function (value) {
              this._rotation_nof8ej$ = value;
              this._sr_tgry0c$ = Math.sin(value);
              this._cr_tgrxmk$ = Math.cos(value);
            }
          },
          updateSkew: function () {
            this._cy_tgrxmr$ = Math.cos(this.skew.y);
            this._sy_tgry0j$ = Math.sin(this.skew.y);
            this._nsx_9yeize$ = Math.sin(this.skew.x);
            this._cx_tgrxmq$ = Math.cos(this.skew.x);
            this._localID++;
          },
          updateLocalTransform: function () {
            var lt = this.localTransform;
            if (this._localID !== this._currentLocalID) {
              var a = this._cr_tgrxmk$ * this.scale.x;
              var b = this._sr_tgry0c$ * this.scale.x;
              var c = -this._sr_tgry0c$ * this.scale.y;
              var d = this._cr_tgrxmk$ * this.scale.y;
              lt.a = this._cy_tgrxmr$ * a + this._sy_tgry0j$ * c;
              lt.b = this._cy_tgrxmr$ * b + this._sy_tgry0j$ * d;
              lt.c = this._nsx_9yeize$ * a + this._cx_tgrxmq$ * c;
              lt.d = this._nsx_9yeize$ * b + this._cx_tgrxmq$ * d;
              lt.tx = this.position.x - (this.pivot.x * lt.a + this.pivot.y * lt.c);
              lt.ty = this.position.y - (this.pivot.x * lt.b + this.pivot.y * lt.d);
              this._currentLocalID = this._localID;
              this._parentID = -1;
            }
          },
          updateTransform_ahtr0$: function (parentTransform) {
            var pt = parentTransform.worldTransform;
            var wt = this.worldTransform;
            var lt = this.localTransform;
            if (this._localID !== this._currentLocalID) {
              var a = this._cr_tgrxmk$ * this.scale.x;
              var b = this._sr_tgry0c$ * this.scale.x;
              var c = -this._sr_tgry0c$ * this.scale.y;
              var d = this._cr_tgrxmk$ * this.scale.y;
              lt.a = this._cy_tgrxmr$ * a + this._sy_tgry0j$ * c;
              lt.b = this._cy_tgrxmr$ * b + this._sy_tgry0j$ * d;
              lt.c = this._nsx_9yeize$ * a + this._cx_tgrxmq$ * c;
              lt.d = this._nsx_9yeize$ * b + this._cx_tgrxmq$ * d;
              lt.tx = this.position.x - (this.pivot.x * lt.a + this.pivot.y * lt.c);
              lt.ty = this.position.y - (this.pivot.x * lt.b + this.pivot.y * lt.d);
              this._currentLocalID = this._localID;
              this._parentID = -1;
            }
            if (this._parentID !== parentTransform._worldID) {
              wt.a = lt.a * pt.a + lt.b * pt.c;
              wt.b = lt.a * pt.b + lt.b * pt.d;
              wt.c = lt.c * pt.a + lt.d * pt.c;
              wt.d = lt.c * pt.b + lt.d * pt.d;
              wt.tx = lt.tx * pt.a + lt.ty * pt.c + pt.tx;
              wt.ty = lt.tx * pt.b + lt.ty * pt.d + pt.ty;
              this._parentID = parentTransform._worldID;
              this._worldID++;
            }
          }
        }, /** @lends _.pixi.display.Transform */ {
          Companion: Kotlin.createObject(null, function () {
            _.pixi.display.Transform.Companion.IDENTITY = new _.pixi.display.Transform();
          }),
          object_initializer$: function () {
            _.pixi.display.Transform.Companion;
          }
        })
      }),
      gl: Kotlin.definePackage(null, /** @lends _.pixi.gl */ {
        createContext_nr4ftb$: function (canvas, options) {
          var tmp$0, tmp$1;
          if (options === void 0)
            options = null;
          var gl = Kotlin.isType(tmp$1 = (tmp$0 = canvas.getContext('webgl', options)) != null ? tmp$0 : canvas.getContext('experimental-webgl', options), WebGLRenderingContext) ? tmp$1 : null;
          if (gl === null) {
            throw Kotlin.Throwable('This browser does not support webGL. Try using the canvas renderer');
          }
           else {
            return gl;
          }
        }
      }),
      math: Kotlin.definePackage(null, /** @lends _.pixi.math */ {
        BitTwiddle: Kotlin.createObject(null, null, /** @lends _.pixi.math.BitTwiddle.prototype */ {
          isPow2_za3lpa$: function (v) {
            return (v & v - 1) === 0 && v !== 0;
          },
          nextPow2_za3lpa$: function (t) {
            var v = t;
            if (v > 0) {
              v++;
            }
            v = v | v >> 1;
            v = v | v >> 2;
            v = v | v >> 4;
            v = v | v >> 8;
            v = v | v >> 16;
            return v + 1;
          },
          log2_za3lpa$: function (t) {
            var v = t;
            var r;
            var shift;
            r = v > 65535 ? 16 : 0;
            v = v >>> r;
            shift = v > 255 ? 8 : 0;
            v = v >>> shift;
            r = r | shift;
            shift = v > 15 ? 4 : 0;
            v = v >>> shift;
            r = r | shift;
            shift = v > 3 ? 2 : 0;
            v = v >>> shift;
            r = r | shift;
            return r | v >> 1;
          }
        }),
        Frame: Kotlin.createClass(null, function (x, y, width, height) {
          if (x === void 0)
            x = 0;
          if (y === void 0)
            y = 0;
          if (width === void 0)
            width = 0;
          if (height === void 0)
            height = 0;
          this.x = x;
          this.y = y;
          this.width = width;
          this.height = height;
        }, /** @lends _.pixi.math.Frame.prototype */ {
          copy_ieo66t$: function (frame) {
            this.x = frame.x;
            this.y = frame.y;
            this.width = frame.width;
            this.height = frame.height;
          }
        }, /** @lends _.pixi.math.Frame */ {
          Companion: Kotlin.createObject(null, function () {
            _.pixi.math.Frame.Companion.EMPTY = new _.pixi.math.Frame();
          }),
          object_initializer$: function () {
            _.pixi.math.Frame.Companion;
          }
        }),
        Matrix: Kotlin.createClass(null, function (a, b, c, d, tx, ty) {
          if (a === void 0)
            a = 1.0;
          if (b === void 0)
            b = 0.0;
          if (c === void 0)
            c = 0.0;
          if (d === void 0)
            d = 1.0;
          if (tx === void 0)
            tx = 0.0;
          if (ty === void 0)
            ty = 0.0;
          this.a = a;
          this.b = b;
          this.c = c;
          this.d = d;
          this.tx = tx;
          this.ty = ty;
          this.array = null;
        }, /** @lends _.pixi.math.Matrix.prototype */ {
          identity: function () {
            this.a = 1.0;
            this.b = 0.0;
            this.c = 0.0;
            this.d = 1.0;
            this.tx = 0.0;
            this.ty = 0.0;
          },
          toArray_wvtda4$: function (transpose, out) {
            var tmp$0;
            if (out === void 0)
              out = null;
            var array = (tmp$0 = out != null ? out : this.array) != null ? tmp$0 : new Float32Array(9);
            if (this.array == null) {
              this.array = array;
            }
            if (transpose) {
              array[0] = this.a;
              array[1] = this.b;
              array[2] = 0.0;
              array[3] = this.c;
              array[4] = this.d;
              array[5] = 0.0;
              array[6] = this.tx;
              array[7] = this.ty;
              array[8] = 1.0;
            }
             else {
              array[0] = this.a;
              array[1] = this.c;
              array[2] = this.tx;
              array[3] = this.b;
              array[4] = this.d;
              array[5] = this.ty;
              array[6] = 0.0;
              array[7] = 0.0;
              array[8] = 1.0;
            }
            return array;
          },
          append_oe79f$: function (matrix) {
            var a1 = this.a;
            var b1 = this.b;
            var c1 = this.c;
            var d1 = this.d;
            this.a = matrix.a * a1 + matrix.b * c1;
            this.b = matrix.a * b1 + matrix.b * d1;
            this.c = matrix.c * a1 + matrix.d * c1;
            this.d = matrix.c * b1 + matrix.d * d1;
            this.tx = matrix.tx * a1 + matrix.ty * c1 + this.tx;
            this.ty = matrix.tx * b1 + matrix.ty * d1 + this.ty;
            return this;
          },
          component1: function () {
            return this.a;
          },
          component2: function () {
            return this.b;
          },
          component3: function () {
            return this.c;
          },
          component4: function () {
            return this.d;
          },
          component5: function () {
            return this.tx;
          },
          component6: function () {
            return this.ty;
          },
          copy_w8lrqs$: function (a, b, c, d, tx, ty) {
            return new _.pixi.math.Matrix(a === void 0 ? this.a : a, b === void 0 ? this.b : b, c === void 0 ? this.c : c, d === void 0 ? this.d : d, tx === void 0 ? this.tx : tx, ty === void 0 ? this.ty : ty);
          },
          toString: function () {
            return 'Matrix(a=' + Kotlin.toString(this.a) + (', b=' + Kotlin.toString(this.b)) + (', c=' + Kotlin.toString(this.c)) + (', d=' + Kotlin.toString(this.d)) + (', tx=' + Kotlin.toString(this.tx)) + (', ty=' + Kotlin.toString(this.ty)) + ')';
          },
          hashCode: function () {
            var result = 0;
            result = result * 31 + Kotlin.hashCode(this.a) | 0;
            result = result * 31 + Kotlin.hashCode(this.b) | 0;
            result = result * 31 + Kotlin.hashCode(this.c) | 0;
            result = result * 31 + Kotlin.hashCode(this.d) | 0;
            result = result * 31 + Kotlin.hashCode(this.tx) | 0;
            result = result * 31 + Kotlin.hashCode(this.ty) | 0;
            return result;
          },
          equals_za3rmp$: function (other) {
            return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.a, other.a) && Kotlin.equals(this.b, other.b) && Kotlin.equals(this.c, other.c) && Kotlin.equals(this.d, other.d) && Kotlin.equals(this.tx, other.tx) && Kotlin.equals(this.ty, other.ty)))));
          }
        }),
        ObservablePoint: Kotlin.createClass(function () {
          return [_.pixi.math.Point];
        }, function $fun(cb, x, y) {
          if (x === void 0)
            x = 0.0;
          if (y === void 0)
            y = 0.0;
          $fun.baseInitializer.call(this, x, y);
          this.cb = cb;
        }, /** @lends _.pixi.math.ObservablePoint.prototype */ {
          x: {
            get: function () {
              return Kotlin.callGetter(this, _.pixi.math.Point, 'x');
            },
            set: function (value) {
              if (Kotlin.callGetter(this, _.pixi.math.Point, 'x') !== value) {
                Kotlin.callSetter(this, _.pixi.math.Point, 'x', value);
                this.cb.invalidate();
              }
            }
          },
          y: {
            get: function () {
              return Kotlin.callGetter(this, _.pixi.math.Point, 'y');
            },
            set: function (value) {
              if (Kotlin.callGetter(this, _.pixi.math.Point, 'y') !== value) {
                Kotlin.callSetter(this, _.pixi.math.Point, 'y', value);
                this.cb.invalidate();
              }
            }
          },
          setAll_mx4ult$: function (v) {
            if (Kotlin.callGetter(this, _.pixi.math.Point, 'x') !== v || Kotlin.callGetter(this, _.pixi.math.Point, 'y') !== v) {
              Kotlin.callSetter(this, _.pixi.math.Point, 'x', v);
              Kotlin.callSetter(this, _.pixi.math.Point, 'y', v);
              this.cb.invalidate();
            }
          },
          set_dleff0$: function (x, y) {
            if (Kotlin.callGetter(this, _.pixi.math.Point, 'x') !== x || Kotlin.callGetter(this, _.pixi.math.Point, 'y') !== y) {
              Kotlin.callSetter(this, _.pixi.math.Point, 'x', x);
              Kotlin.callSetter(this, _.pixi.math.Point, 'y', y);
              this.cb.invalidate();
            }
          },
          copy_i97z8y$: function (p) {
            if (Kotlin.callGetter(this, _.pixi.math.Point, 'x') !== p.x || Kotlin.callGetter(this, _.pixi.math.Point, 'y') !== p.y) {
              Kotlin.callSetter(this, _.pixi.math.Point, 'x', p.x);
              Kotlin.callSetter(this, _.pixi.math.Point, 'y', p.y);
              this.cb.invalidate();
            }
          }
        }),
        Point: Kotlin.createClass(null, function (x, y) {
          if (x === void 0)
            x = 0.0;
          if (y === void 0)
            y = 0.0;
          this.$x_gtnbs$ = x;
          this.$y_gtnbt$ = y;
        }, /** @lends _.pixi.math.Point.prototype */ {
          x: {
            get: function () {
              return this.$x_gtnbs$;
            },
            set: function (x) {
              this.$x_gtnbs$ = x;
            }
          },
          y: {
            get: function () {
              return this.$y_gtnbt$;
            },
            set: function (y) {
              this.$y_gtnbt$ = y;
            }
          },
          setAll_mx4ult$: function (v) {
            this.x = v;
            this.y = v;
          },
          set_dleff0$: function (x, y) {
            this.x = x;
            this.y = y;
          },
          copy_i97z8y$: function (p) {
            this.x = p.x;
            this.y = p.y;
          },
          equals_i97z8y$: function (p) {
            return this.x === p.x && this.y === p.y;
          },
          clone: function () {
            return new _.pixi.math.Point(this.x, this.y);
          }
        }),
        Rectangle: Kotlin.createClass(null, function (x, y, width, height) {
          if (x === void 0)
            x = 0.0;
          if (y === void 0)
            y = 0.0;
          if (width === void 0)
            width = 0.0;
          if (height === void 0)
            height = 0.0;
          this.x = x;
          this.y = y;
          this.width = width;
          this.height = height;
        }, /** @lends _.pixi.math.Rectangle.prototype */ {
          copy_fljg6l$: function (rect) {
            this.x = rect.x;
            this.y = rect.y;
            this.width = rect.width;
            this.height = rect.height;
          }
        }, /** @lends _.pixi.math.Rectangle */ {
          Companion: Kotlin.createObject(null, function () {
            _.pixi.math.Rectangle.Companion.EMPTY = new _.pixi.math.Rectangle();
          }),
          object_initializer$: function () {
            _.pixi.math.Rectangle.Companion;
          }
        }),
        Versionable: Kotlin.createTrait(null)
      }),
      renderers: Kotlin.definePackage(null, /** @lends _.pixi.renderers */ {
        ObjectRenderer: Kotlin.createClass(null, function (renderer) {
          this.renderer = renderer;
        }, /** @lends _.pixi.renderers.ObjectRenderer.prototype */ {
          start: function () {
          },
          stop: function () {
          },
          flush: function () {
          }
        }),
        RenderOptions: Kotlin.createClass(null, function (view, resolution, antialias, transparent, autoResize, forceFXAA, clearBeforeRender, roundPixels, backgroundColor, preserveDrawingBuffer) {
          if (view === void 0)
            view = null;
          if (resolution === void 0)
            resolution = 1.0;
          if (antialias === void 0)
            antialias = false;
          if (transparent === void 0)
            transparent = false;
          if (autoResize === void 0)
            autoResize = false;
          if (forceFXAA === void 0)
            forceFXAA = false;
          if (clearBeforeRender === void 0)
            clearBeforeRender = true;
          if (roundPixels === void 0)
            roundPixels = false;
          if (backgroundColor === void 0)
            backgroundColor = _.pixi.Color_init_7b5o5w$(0.0, 0.0, 0.0, 0.0);
          if (preserveDrawingBuffer === void 0)
            preserveDrawingBuffer = false;
          this.view = view;
          this.resolution = resolution;
          this.antialias = antialias;
          this.transparent = transparent;
          this.autoResize = autoResize;
          this.forceFXAA = forceFXAA;
          this.clearBeforeRender = clearBeforeRender;
          this.roundPixels = roundPixels;
          this.backgroundColor = backgroundColor;
          this.preserveDrawingBuffer = preserveDrawingBuffer;
        }, /** @lends _.pixi.renderers.RenderOptions.prototype */ {
          component1: function () {
            return this.view;
          },
          component2: function () {
            return this.resolution;
          },
          component3: function () {
            return this.antialias;
          },
          component4: function () {
            return this.transparent;
          },
          component5: function () {
            return this.autoResize;
          },
          component6: function () {
            return this.forceFXAA;
          },
          component7: function () {
            return this.clearBeforeRender;
          },
          component8: function () {
            return this.roundPixels;
          },
          component9: function () {
            return this.backgroundColor;
          },
          component10: function () {
            return this.preserveDrawingBuffer;
          },
          copy_o8gn6j$: function (view, resolution, antialias, transparent, autoResize, forceFXAA, clearBeforeRender, roundPixels, backgroundColor, preserveDrawingBuffer) {
            return new _.pixi.renderers.RenderOptions(view === void 0 ? this.view : view, resolution === void 0 ? this.resolution : resolution, antialias === void 0 ? this.antialias : antialias, transparent === void 0 ? this.transparent : transparent, autoResize === void 0 ? this.autoResize : autoResize, forceFXAA === void 0 ? this.forceFXAA : forceFXAA, clearBeforeRender === void 0 ? this.clearBeforeRender : clearBeforeRender, roundPixels === void 0 ? this.roundPixels : roundPixels, backgroundColor === void 0 ? this.backgroundColor : backgroundColor, preserveDrawingBuffer === void 0 ? this.preserveDrawingBuffer : preserveDrawingBuffer);
          },
          toString: function () {
            return 'RenderOptions(view=' + Kotlin.toString(this.view) + (', resolution=' + Kotlin.toString(this.resolution)) + (', antialias=' + Kotlin.toString(this.antialias)) + (', transparent=' + Kotlin.toString(this.transparent)) + (', autoResize=' + Kotlin.toString(this.autoResize)) + (', forceFXAA=' + Kotlin.toString(this.forceFXAA)) + (', clearBeforeRender=' + Kotlin.toString(this.clearBeforeRender)) + (', roundPixels=' + Kotlin.toString(this.roundPixels)) + (', backgroundColor=' + Kotlin.toString(this.backgroundColor)) + (', preserveDrawingBuffer=' + Kotlin.toString(this.preserveDrawingBuffer)) + ')';
          },
          hashCode: function () {
            var result = 0;
            result = result * 31 + Kotlin.hashCode(this.view) | 0;
            result = result * 31 + Kotlin.hashCode(this.resolution) | 0;
            result = result * 31 + Kotlin.hashCode(this.antialias) | 0;
            result = result * 31 + Kotlin.hashCode(this.transparent) | 0;
            result = result * 31 + Kotlin.hashCode(this.autoResize) | 0;
            result = result * 31 + Kotlin.hashCode(this.forceFXAA) | 0;
            result = result * 31 + Kotlin.hashCode(this.clearBeforeRender) | 0;
            result = result * 31 + Kotlin.hashCode(this.roundPixels) | 0;
            result = result * 31 + Kotlin.hashCode(this.backgroundColor) | 0;
            result = result * 31 + Kotlin.hashCode(this.preserveDrawingBuffer) | 0;
            return result;
          },
          equals_za3rmp$: function (other) {
            return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.view, other.view) && Kotlin.equals(this.resolution, other.resolution) && Kotlin.equals(this.antialias, other.antialias) && Kotlin.equals(this.transparent, other.transparent) && Kotlin.equals(this.autoResize, other.autoResize) && Kotlin.equals(this.forceFXAA, other.forceFXAA) && Kotlin.equals(this.clearBeforeRender, other.clearBeforeRender) && Kotlin.equals(this.roundPixels, other.roundPixels) && Kotlin.equals(this.backgroundColor, other.backgroundColor) && Kotlin.equals(this.preserveDrawingBuffer, other.preserveDrawingBuffer)))));
          }
        }),
        renderOptions_mpdkzt$: function (init) {
          var ro = new _.pixi.renderers.RenderOptions();
          init.call(ro);
          return ro;
        },
        RenderTarget: Kotlin.createClass(function () {
          return [_.pixi.Destroyable];
        }, function (gl, width, height, scaleMode, resolution, root) {
          if (scaleMode === void 0)
            scaleMode = _.pixi.ScaleMode.Companion.DEFAULT;
          if (resolution === void 0)
            resolution = 1.0;
          if (root === void 0)
            root = false;
          var tmp$0, tmp$1;
          this.gl = gl;
          this.width = width;
          this.height = height;
          this.scaleMode = scaleMode;
          this.resolution = resolution;
          this.root = root;
          this.frameBuffer = null;
          this.clearColor = _.pixi.Color_init_7b5o5w$(0.0, 0.0, 0.0, 0.0);
          this.size = new _.pixi.math.Frame(0, 0, 1, 1);
          this.transform = null;
          this.projectionMatrix = new _.pixi.math.Matrix();
          this.frame = null;
          this.defaultFrame = new _.pixi.math.Frame();
          this.destinationFrame = null;
          this.sourceFrame = null;
          if (!this.root) {
            var _frameBuffer = PIXI.glCore.GLFramebuffer.createRGBA(this.gl, 100, 100);
            this.frameBuffer = _frameBuffer;
            if (this.scaleMode === _.pixi.ScaleMode.NEAREST) {
              ((tmp$0 = _frameBuffer.texture) != null ? tmp$0 : Kotlin.throwNPE()).enableNearestScaling();
            }
             else {
              ((tmp$1 = _frameBuffer.texture) != null ? tmp$1 : Kotlin.throwNPE()).enableLinearScaling();
            }
          }
           else {
            var _frameBuffer_0 = new PIXI.glCore.GLFramebuffer(this.gl, 100, 100);
            _frameBuffer_0.framebuffer = null;
            this.frameBuffer = _frameBuffer_0;
          }
          this.setFrames_euxh06$();
          this.resize_vux9f0$(this.width, this.height);
          this.$isDestroyed_fw2pgq$ = false;
        }, /** @lends _.pixi.renderers.RenderTarget.prototype */ {
          texture: {
            get: function () {
              var tmp$0;
              return (tmp$0 = this.frameBuffer) != null ? tmp$0.texture : null;
            }
          },
          setFrames_euxh06$: function (destinationFrame, sourceFrame) {
            var tmp$0, tmp$1;
            if (destinationFrame === void 0)
              destinationFrame = null;
            if (sourceFrame === void 0)
              sourceFrame = null;
            this.destinationFrame = (tmp$0 = destinationFrame != null ? destinationFrame : this.destinationFrame) != null ? tmp$0 : this.defaultFrame;
            this.sourceFrame = (tmp$1 = sourceFrame != null ? sourceFrame : this.sourceFrame) != null ? tmp$1 : destinationFrame;
          },
          activate: function () {
            var tmp$0, tmp$1;
            (tmp$0 = this.frameBuffer) != null ? tmp$0.bind() : null;
            var destinationFrame = (tmp$1 = this.destinationFrame) != null ? tmp$1 : Kotlin.throwNPE();
            this.calculateProjection_euxh06$(destinationFrame, this.sourceFrame);
            var transform = this.transform;
            if (transform != null) {
              this.projectionMatrix.append_oe79f$(transform);
            }
            if (this.destinationFrame !== this.sourceFrame) {
              this.gl.enable(WebGLRenderingContext.SCISSOR_TEST);
              this.gl.scissor(Math.floor(destinationFrame.x), Math.floor(destinationFrame.y), Math.floor(destinationFrame.width * this.resolution), Math.floor(destinationFrame.height * this.resolution));
            }
             else {
              this.gl.disable(WebGLRenderingContext.SCISSOR_TEST);
            }
            this.gl.viewport(Math.floor(destinationFrame.x), Math.floor(destinationFrame.y), Math.floor(destinationFrame.width * this.resolution), Math.floor(destinationFrame.height * this.resolution));
          },
          resize_vux9f0$: function (width, height) {
            var tmp$0, tmp$1;
            if (this.size.width === width && this.size.height === height) {
              return;
            }
            this.size.width = width;
            this.size.height = height;
            this.defaultFrame.width = width;
            this.defaultFrame.height = height;
            (tmp$0 = this.frameBuffer) != null ? tmp$0.resize(Math.floor(width * this.resolution), Math.floor(height * this.resolution)) : null;
            this.calculateProjection_euxh06$((tmp$1 = this.frame) != null ? tmp$1 : this.size);
          },
          calculateProjection_euxh06$: function (destinationFrame, sourceFrame) {
            if (sourceFrame === void 0)
              sourceFrame = null;
            var pm = this.projectionMatrix;
            var _sourceFrame = sourceFrame != null ? sourceFrame : destinationFrame;
            pm.identity();
            if (!this.root) {
              pm.a = 1.0 / destinationFrame.width * 2;
              pm.d = 1.0 / destinationFrame.height * 2;
              pm.tx = -1 - _sourceFrame.x * pm.a;
              pm.ty = -1 - _sourceFrame.y * pm.d;
            }
             else {
              pm.a = 1.0 / destinationFrame.width * 2;
              pm.d = -1.0 / destinationFrame.height * 2;
              pm.tx = -1 - _sourceFrame.x * pm.a;
              pm.ty = 1 - _sourceFrame.y * pm.d;
            }
          },
          clear_sgj01r$: function (clearColor) {
            var tmp$0;
            if (clearColor === void 0)
              clearColor = null;
            var rgba = (clearColor != null ? clearColor : this.clearColor).rgba;
            (tmp$0 = this.frameBuffer) != null ? tmp$0.clear(_.pixi.utils.get_25qnj7$(rgba, 0), _.pixi.utils.get_25qnj7$(rgba, 1), _.pixi.utils.get_25qnj7$(rgba, 2), _.pixi.utils.get_25qnj7$(rgba, 3)) : null;
          },
          isDestroyed: {
            get: function () {
              return this.$isDestroyed_fw2pgq$;
            },
            set: function (isDestroyed) {
              this.$isDestroyed_fw2pgq$ = isDestroyed;
            }
          },
          destroy: function () {
            var tmp$0;
            (tmp$0 = this.frameBuffer) != null ? tmp$0.destroy() : null;
            this.frameBuffer = null;
            _.pixi.Destroyable.prototype.destroy.call(this);
          }
        }),
        checkPrecision_61zpoe$: function (src) {
          if (!Kotlin.equals(src.substring(0, 9), 'precision')) {
            return 'precision ' + _.pixi.Precision.Companion.DEFAULT.glsl + ' float;\n' + src;
          }
          return src;
        },
        Shader: Kotlin.createClass(function () {
          return [_.pixi.Destroyable];
        }, function (renderer, vertexSrc, fragmentSrc) {
          this.renderer = renderer;
          this.shader_ymn70s$ = new PIXI.glCore.GLShader(this.renderer.gl, _.pixi.renderers.checkPrecision_61zpoe$(vertexSrc), _.pixi.renderers.checkPrecision_61zpoe$(fragmentSrc));
          this.$isDestroyed_q6u8zs$ = false;
        }, /** @lends _.pixi.renderers.Shader.prototype */ {
          gl: {
            get: function () {
              return this.renderer.gl;
            }
          },
          program: {
            get: function () {
              return this.shader_ymn70s$.program;
            }
          },
          attributes: {
            get: function () {
              return this.shader_ymn70s$.attributes;
            }
          },
          uniforms: {
            get: function () {
              return this.shader_ymn70s$.uniforms;
            }
          },
          isDestroyed: {
            get: function () {
              return this.$isDestroyed_q6u8zs$;
            },
            set: function (isDestroyed) {
              this.$isDestroyed_q6u8zs$ = isDestroyed;
            }
          },
          bind: function () {
            this.shader_ymn70s$.bind();
          },
          destroy: function () {
            this.shader_ymn70s$.destroy();
            _.pixi.Destroyable.prototype.destroy.call(this);
          }
        }),
        Shader_init_2820im$: function (renderer, vertexSrc, fragmentSrc, $this) {
          $this = $this || Object.create(_.pixi.renderers.Shader.prototype);
          _.pixi.renderers.Shader.call($this, renderer, Kotlin.kotlin.collections.joinToString_qtax42$(vertexSrc, '\n'), Kotlin.kotlin.collections.joinToString_qtax42$(fragmentSrc, '\n'));
          return $this;
        },
        SystemRenderer: Kotlin.createClass(function () {
          return [_.pixi.Destroyable];
        }, function (width, height, options) {
          if (width === void 0)
            width = 800;
          if (height === void 0)
            height = 600;
          if (options === void 0)
            options = new _.pixi.renderers.RenderOptions();
          var tmp$0, tmp$1;
          this.width = width;
          this.height = height;
          this.options = options;
          this.view = (tmp$1 = this.options.view) != null ? tmp$1 : Kotlin.isType(tmp$0 = document.createElement('canvas'), HTMLCanvasElement) ? tmp$0 : Kotlin.throwCCE();
          this.resolution = this.options.resolution;
          this.autoResize = this.options.autoResize;
          this.clearBeforeRender = this.options.clearBeforeRender;
          this.roundPixels = this.options.roundPixels;
          this._backgroundColor_obqm34$ = new _.pixi.Color();
          this.backgroundColor = this.options.backgroundColor;
          this.$isDestroyed_1tkkln$ = false;
        }, /** @lends _.pixi.renderers.SystemRenderer.prototype */ {
          backgroundColor: {
            get: function () {
              return this._backgroundColor_obqm34$;
            },
            set: function (value) {
              this._backgroundColor_obqm34$.copy_sgj01r$(value);
            }
          },
          resize_vux9f0$: function (width, height) {
            this.width = Math.ceil(width * this.resolution);
            this.height = Math.ceil(height * this.resolution);
            this.view.width = this.width;
            this.view.height = this.height;
            if (this.autoResize) {
              this.view.style.width = (this.width / this.resolution).toString() + 'px';
              this.view.style.height = (this.height / this.resolution).toString() + 'px';
            }
          },
          isDestroyed: {
            get: function () {
              return this.$isDestroyed_1tkkln$;
            },
            set: function (isDestroyed) {
              this.$isDestroyed_1tkkln$ = isDestroyed;
            }
          },
          destroy: function () {
            _.pixi.Destroyable.prototype.destroy.call(this);
            this.destroy_6taknv$(false);
          },
          destroy_6taknv$: function (removeView) {
            var tmp$0;
            if (removeView === void 0)
              removeView = false;
            if (removeView && this.view.parentNode !== null) {
              (Kotlin.isType(tmp$0 = this.view.parentNode, Node) ? tmp$0 : Kotlin.throwCCE()).removeChild(this.view);
            }
          }
        }),
        TextureGarbageCollector: Kotlin.createClass(null, function (renderer) {
          this.gl = renderer.gl;
          this._managedTextures = Kotlin.kotlin.collections.emptyList();
          this.count = 0;
          this.checkCount = 0;
          this.maxIdle = 60 * 60;
          this.checkCountMax = 60 * 10;
          this.mode = _.pixi.GcMode.Companion.DEFAULT;
        }, /** @lends _.pixi.renderers.TextureGarbageCollector.prototype */ {
          update: function () {
          }
        }),
        TextureManager: Kotlin.createClass(function () {
          return [_.pixi.Destroyable];
        }, function (renderer) {
          this.$isDestroyed_5fbu4r$ = false;
          this.gl = renderer.gl;
          this._managedTextures = Kotlin.kotlin.collections.emptyList();
        }, /** @lends _.pixi.renderers.TextureManager.prototype */ {
          isDestroyed: {
            get: function () {
              return this.$isDestroyed_5fbu4r$;
            },
            set: function (isDestroyed) {
              this.$isDestroyed_5fbu4r$ = isDestroyed;
            }
          },
          updateTexture_cvvv61$: function (texture) {
            return this.updateTexture_wgolu0$(texture.baseTexture);
          },
          updateTexture_wgolu0$: function (texture) {
            var source = texture.source;
            if (source == null)
              return null;
            var glTexture = texture._glTexture;
            if (glTexture == null) {
              glTexture = new PIXI.glCore.GLTexture(this.gl);
              glTexture.premultiplyAlpha = true;
              glTexture.upload(source);
              texture._glTexture = glTexture;
              if (texture.isPowerOfTwo) {
                if (texture.mipmap) {
                  glTexture.enableMipmap();
                }
                if (texture.wrapMode === _.pixi.WrapMode.CLAMP) {
                  glTexture.enableWrapClamp();
                }
                 else if (texture.wrapMode === _.pixi.WrapMode.REPEAT) {
                  glTexture.enableWrapRepeat();
                }
                 else {
                  glTexture.enableWrapMirrorRepeat();
                }
              }
               else {
                glTexture.enableWrapClamp();
              }
              if (texture.scaleMode === _.pixi.ScaleMode.NEAREST) {
                glTexture.enableNearestScaling();
              }
               else {
                glTexture.enableLinearScaling();
              }
            }
             else {
              glTexture.upload(source);
            }
            return glTexture;
          }
        }),
        WebGLRenderer: Kotlin.createClass(function () {
          return [_.pixi.renderers.SystemRenderer];
        }, function $fun(width, height, options) {
          if (width === void 0)
            width = 800;
          if (height === void 0)
            height = 600;
          if (options === void 0)
            options = new _.pixi.renderers.RenderOptions();
          var tmp$0;
          $fun.baseInitializer.call(this, width, height, options);
          this.$type_2txetq$ = _.pixi.RendererType.WEBGL;
          this.gl = Kotlin.isType(tmp$0 = this.view.getContext('webgl'), WebGLRenderingContext) ? tmp$0 : Kotlin.throwCCE();
          this.rootRenderTarget = new _.pixi.renderers.RenderTarget(this.gl, width, height, _.pixi.ScaleMode.Companion.DEFAULT, this.resolution, true);
          this.renderingToScreen = false;
          this._lastObjectRendered = null;
          _.pixi.utils.sayHello_61zpoe$(this.type.systemName);
          this.rootRenderTarget.clearColor = this.backgroundColor;
          this.bindRenderTarget_lo3uzm$(this.rootRenderTarget, true);
          this.resize_vux9f0$(width, height);
          this.emptyRenderer = new _.pixi.renderers.ObjectRenderer(this);
          this.spriteRenderer = new _.pixi.sprite.webgl.SpriteRenderer(this);
          this.currentRenderer = this.spriteRenderer;
          this.textureManager = new _.pixi.renderers.TextureManager(this);
          this.textureGC = new _.pixi.renderers.TextureGarbageCollector(this);
          this._activeRenderTarget = this.rootRenderTarget;
          this._activeShader = null;
          this._activeTextureLocation = 0;
          this._activeTexture = null;
          this.state = new _.pixi.renderers.WebGLState(this.gl);
          this._initContext();
        }, /** @lends _.pixi.renderers.WebGLRenderer.prototype */ {
          type: {
            get: function () {
              return this.$type_2txetq$;
            }
          },
          render_e5kpu6$: function (displayObject, renderTexture, clear, transform, skipUpdateTransform) {
            if (renderTexture === void 0)
              renderTexture = null;
            if (clear === void 0)
              clear = null;
            if (transform === void 0)
              transform = null;
            if (skipUpdateTransform === void 0)
              skipUpdateTransform = false;
            this.renderingToScreen = renderTexture == null;
            this.spriteRenderer.onPrerender();
            if (this.renderingToScreen) {
              this._lastObjectRendered = displayObject;
            }
            if (!skipUpdateTransform) {
              displayObject.updateTransform();
            }
            this.bindRenderTexture_5nb7rp$(renderTexture, transform);
            this.currentRenderer.start();
            if (clear != null ? clear : this.clearBeforeRender) {
              this._activeRenderTarget.clear_sgj01r$();
            }
            displayObject.renderWebGL_75dbqe$(this);
            this.currentRenderer.flush();
            this.textureGC.update();
          },
          setObjectRenderer_ajirfg$: function (objectRenderer) {
            if (Kotlin.equals(this.currentRenderer, objectRenderer)) {
              return;
            }
            this.currentRenderer.stop();
            this.currentRenderer = objectRenderer;
            this.currentRenderer.start();
          },
          _initContext: function () {
            this.state.resetToDefault();
            this.spriteRenderer.onContextChange();
          },
          flush: function () {
            this.setObjectRenderer_ajirfg$(this.emptyRenderer);
          },
          bindRenderTexture_5nb7rp$: function (renderTexture, transform) {
            if (transform === void 0)
              transform = null;
            if (renderTexture != null) {
              throw new Kotlin.kotlin.NotImplementedError();
            }
            this.bindRenderTarget_lo3uzm$(this.rootRenderTarget);
          },
          bindRenderTarget_lo3uzm$: function (renderTarget, force) {
            var tmp$0, tmp$1;
            if (force === void 0)
              force = false;
            if (!force && Kotlin.equals(this._activeRenderTarget, renderTarget)) {
              return;
            }
            this._activeRenderTarget = renderTarget;
            renderTarget.activate();
            (tmp$1 = (tmp$0 = this._activeShader) != null ? tmp$0.uniforms : null) != null ? (tmp$1.projectionMatrix = renderTarget.projectionMatrix.toArray_wvtda4$(true)) : null;
          },
          bindShader_opcsrd$: function (shader) {
            if (Kotlin.equals(this._activeShader, shader)) {
              return;
            }
            this._activeShader = shader;
            shader.bind();
            shader.uniforms.projectionMatrix = this._activeRenderTarget.projectionMatrix.toArray_wvtda4$(true);
          },
          bindTexture_mf5ti3$: function (texture, location) {
            this.bindTexture_nqtr3l$(texture.baseTexture, location);
          },
          bindTexture_nqtr3l$: function (texture, location, force) {
            if (location === void 0)
              location = 0;
            if (force === void 0)
              force = false;
            if (force || this._activeTextureLocation !== location) {
              this._activeTextureLocation = location;
              this.gl.activeTexture(WebGLRenderingContext.TEXTURE0 + location);
            }
            this._activeTexture = texture;
            var _glTexture = texture._glTexture;
            if (_glTexture == null) {
              this.textureManager.updateTexture_wgolu0$(texture);
            }
             else {
              texture.touched = this.textureGC.count;
              _glTexture.bind();
            }
          },
          resize_vux9f0$: function (width, height) {
            var tmp$0, tmp$1;
            _.pixi.renderers.SystemRenderer.prototype.resize_vux9f0$.call(this, width, height);
            this.rootRenderTarget.resize_vux9f0$(width, height);
            if (Kotlin.equals(this._activeRenderTarget, this.rootRenderTarget)) {
              this.rootRenderTarget.activate();
              (tmp$1 = (tmp$0 = this._activeShader) != null ? tmp$0.uniforms : null) != null ? (tmp$1.projectionMatrix = this.rootRenderTarget.projectionMatrix.toArray_wvtda4$(true)) : null;
            }
          },
          setBlendMode_7sz4cw$: function (blendMode) {
            this.state.setBlendMode_7sz4cw$(blendMode);
          },
          clear_sgj01r$: function (clearColor) {
            this._activeRenderTarget.clear_sgj01r$(clearColor);
          },
          setTransform_oe79f$: function (matrix) {
            this._activeRenderTarget.transform = matrix;
          },
          createVao: function () {
            return new PIXI.glCore.VertexArrayObject(this.gl, this.state.attribState);
          },
          reset: function () {
            this.setObjectRenderer_ajirfg$(this.emptyRenderer);
            this._activeShader = null;
            this._activeRenderTarget = this.rootRenderTarget;
            this._activeTextureLocation = 9999;
            this._activeTexture = null;
            this.rootRenderTarget.activate();
            this.state.resetToDefault();
          },
          destroyPlugins: function () {
            this.spriteRenderer.destroy();
          },
          destroy_6taknv$: function (removeView) {
            if (removeView === void 0)
              removeView = false;
            _.pixi.renderers.SystemRenderer.prototype.destroy.call(this);
            this.textureManager.destroy();
            this.destroyPlugins();
            this.gl.useProgram(null);
          }
        }),
        WebGLRenderer_init_2yge5z$: function (width, height, init, $this) {
          if (width === void 0)
            width = 800;
          if (height === void 0)
            height = 600;
          $this = $this || Object.create(_.pixi.renderers.WebGLRenderer.prototype);
          _.pixi.renderers.WebGLRenderer.call($this, width, height, _.pixi.renderers.renderOptions_mpdkzt$(init));
          return $this;
        },
        WebGLState: Kotlin.createClass(null, function (gl) {
          var tmp$0, tmp$1, tmp$2, tmp$3;
          this.gl = gl;
          this.activeState = new Uint8Array(16);
          this.defaultState = new Uint8Array(16);
          this.defaultState[0] = 1;
          this.maxAttribs = typeof (tmp$0 = this.gl.getParameter(WebGLRenderingContext.MAX_VERTEX_ATTRIBS)) === 'number' ? tmp$0 : Kotlin.throwCCE();
          this.attribState = {};
          this.attribState.attribState = [];
          this.attribState.tempAttribState = [];
          tmp$1 = this.maxAttribs - 1;
          for (var i = 0; i <= tmp$1; i++) {
            this.attribState.attribState.push(32);
            this.attribState.tempAttribState.push(32);
          }
          this.attribState;
          this.nativeVaoExtension = (tmp$3 = (tmp$2 = this.gl.getExtension('OES_vertex_array_object')) != null ? tmp$2 : this.gl.getExtension('MOZ_OES_vertex_array_object')) != null ? tmp$3 : this.gl.getExtension('WEBKIT_OES_vertex_array_object');
        }, /** @lends _.pixi.renderers.WebGLState.prototype */ {
          setState_za3rmp$: function (state) {
            this.setBlend_za3lpa$(state[_.pixi.renderers.WebGLState.Companion.BLEND]);
            this.setDepthTest_za3lpa$(state[_.pixi.renderers.WebGLState.Companion.DEPTH_TEST]);
            this.setFrontFace_za3lpa$(state[_.pixi.renderers.WebGLState.Companion.FRONT_FACE]);
            this.setCullFace_za3lpa$(state[_.pixi.renderers.WebGLState.Companion.CULL_FACE]);
            this.setBlendMode_7sz4cw$(_.pixi.BlendMode.Companion.byCode_za3lpa$(state[_.pixi.renderers.WebGLState.Companion.BLEND_FUNC]));
          },
          setBlend_za3lpa$: function (value) {
            if (value === void 0)
              value = 0;
            if (_.pixi.utils.get_vbk1hc$(this.activeState, _.pixi.renderers.WebGLState.Companion.BLEND) === value) {
              return;
            }
            this.activeState[_.pixi.renderers.WebGLState.Companion.BLEND] = value;
            if (value === 1) {
              this.gl.enable(WebGLRenderingContext.BLEND);
            }
             else {
              this.gl.disable(WebGLRenderingContext.BLEND);
            }
          },
          setDepthTest_za3lpa$: function (value) {
            if (value === void 0)
              value = 0;
            if (_.pixi.utils.get_vbk1hc$(this.activeState, _.pixi.renderers.WebGLState.Companion.DEPTH_TEST) === value) {
              return;
            }
            this.activeState[_.pixi.renderers.WebGLState.Companion.DEPTH_TEST] = value;
            if (value === 1) {
              this.gl.enable(WebGLRenderingContext.DEPTH_TEST);
            }
             else {
              this.gl.disable(WebGLRenderingContext.DEPTH_TEST);
            }
          },
          setFrontFace_za3lpa$: function (value) {
            if (value === void 0)
              value = 0;
            if (_.pixi.utils.get_vbk1hc$(this.activeState, _.pixi.renderers.WebGLState.Companion.FRONT_FACE) === value) {
              return;
            }
            this.activeState[_.pixi.renderers.WebGLState.Companion.FRONT_FACE] = value;
            if (value === 1) {
              this.gl.enable(WebGLRenderingContext.CW);
            }
             else {
              this.gl.disable(WebGLRenderingContext.CW);
            }
          },
          setCullFace_za3lpa$: function (value) {
            if (value === void 0)
              value = 0;
            if (_.pixi.utils.get_vbk1hc$(this.activeState, _.pixi.renderers.WebGLState.Companion.CULL_FACE) === value) {
              return;
            }
            this.activeState[_.pixi.renderers.WebGLState.Companion.CULL_FACE] = value;
            if (value === 1) {
              this.gl.enable(WebGLRenderingContext.CULL_FACE);
            }
             else {
              this.gl.disable(WebGLRenderingContext.CULL_FACE);
            }
          },
          setBlendMode_7sz4cw$: function (value) {
            if (_.pixi.utils.get_vbk1hc$(this.activeState, _.pixi.renderers.WebGLState.Companion.BLEND_FUNC) === value.bcode) {
              return;
            }
            this.activeState[_.pixi.renderers.WebGLState.Companion.BLEND_FUNC] = value.bcode;
            this.gl.blendFunc(value.glFirstMul, value.glSecondMul);
          },
          resetAttributes: function () {
            var tmp$0, tmp$1;
            tmp$0 = this.maxAttribs - 1;
            for (var i = 0; i <= tmp$0; i++) {
              this.attribState.tempAttribState[i] = 0;
              this.attribState.attribState[i] = 0;
            }
            tmp$1 = this.maxAttribs - 1;
            for (var i_0 = 1; i_0 <= tmp$1; i_0++) {
              this.gl.disableVertexAttribArray(i_0);
            }
          },
          resetToDefault: function () {
            var tmp$0, tmp$1;
            (tmp$0 = this.nativeVaoExtension) != null ? tmp$0.bindVertexArrayOES(null) : null;
            this.resetAttributes();
            tmp$1 = this.activeState.length - 1;
            for (var i = 0; i <= tmp$1; i++) {
              this.activeState[i] = 32;
            }
            this.gl.pixelStorei(WebGLRenderingContext.UNPACK_FLIP_Y_WEBGL, 0);
            this.setState_za3rmp$(this.defaultState);
          }
        }, /** @lends _.pixi.renderers.WebGLState */ {
          Companion: Kotlin.createObject(null, function () {
            _.pixi.renderers.WebGLState.Companion.BLEND = 0;
            _.pixi.renderers.WebGLState.Companion.DEPTH_TEST = 1;
            _.pixi.renderers.WebGLState.Companion.FRONT_FACE = 2;
            _.pixi.renderers.WebGLState.Companion.CULL_FACE = 3;
            _.pixi.renderers.WebGLState.Companion.BLEND_FUNC = 4;
          }),
          object_initializer$: function () {
            _.pixi.renderers.WebGLState.Companion;
          }
        })
      }),
      sprite: Kotlin.definePackage(null, /** @lends _.pixi.sprite */ {
        Sprite: Kotlin.createClass(function () {
          return [_.pixi.math.Versionable, _.pixi.display.Container];
        }, function $fun(texture) {
          if (texture === void 0)
            texture = _.pixi.textures.Texture.Companion.EMPTY;
          $fun.baseInitializer.call(this);
          this._texture_rkxmcg$ = texture;
          this.blendMode = _.pixi.BlendMode.NORMAL;
          this.vertexData = new Float32Array(8);
          this._textureID = -1;
          this._transformID = -1;
          this.anchor = new _.pixi.math.ObservablePoint(this);
        }, /** @lends _.pixi.sprite.Sprite.prototype */ {
          texture: {
            get: function () {
              return this._texture_rkxmcg$;
            },
            set: function (value) {
              this._texture_rkxmcg$ = value;
              this.onTextureUpdate();
            }
          },
          onTextureUpdate: function () {
            this._textureID = -1;
          },
          invalidate: function () {
            this._transformID = -1;
          },
          calculateVertices: function () {
            if (this._transformID === this.transform._worldID && this._textureID === this._texture_rkxmcg$._updateID) {
              return;
            }
            this._transformID = this.transform._worldID;
            this._textureID === this._texture_rkxmcg$._updateID;
            var texture = this._texture_rkxmcg$;
            var orig = texture.orig;
            var trim = texture.trim;
            var wt = this.transform.worldTransform;
            var w0;
            var w1;
            var h0;
            var h1;
            if (trim != null) {
              w1 = trim.x - this.anchor.x * orig.width;
              w0 = w1 + trim.width;
              h1 = trim.y - this.anchor.y * orig.height;
              h0 = h1 + trim.height;
            }
             else {
              w0 = orig.width * (1 - this.anchor.x);
              w1 = orig.width * -this.anchor.x;
              h0 = orig.height * (1 - this.anchor.y);
              h1 = orig.height * -this.anchor.y;
            }
            this.vertexData[0] = wt.a * w1 + wt.c * h1 + wt.tx;
            this.vertexData[1] = wt.b * w1 + wt.d * h1 + wt.ty;
            this.vertexData[2] = wt.a * w0 + wt.c * h1 + wt.tx;
            this.vertexData[3] = wt.b * w0 + wt.d * h1 + wt.ty;
            this.vertexData[4] = wt.a * w0 + wt.c * h0 + wt.tx;
            this.vertexData[5] = wt.b * w0 + wt.d * h0 + wt.ty;
            this.vertexData[6] = wt.a * w1 + wt.c * h0 + wt.tx;
            this.vertexData[7] = wt.b * w1 + wt.d * h0 + wt.ty;
          },
          objectRenderWebGL_75dbqe$: function (renderer) {
            this.calculateVertices();
            renderer.setObjectRenderer_ajirfg$(renderer.spriteRenderer);
            renderer.spriteRenderer.render_xzqyvq$(this);
          }
        }),
        webgl: Kotlin.definePackage(function () {
          this.vertexSrc = 'attribute vec2 aVertexPosition;\nattribute vec2 aTextureCoord;\nattribute vec4 aColor;\nattribute float aTextureId;\n\nuniform mat3 projectionMatrix;\n\nvarying vec2 vTextureCoord;\nvarying vec4 vColor;\nvarying float vTextureId;\n\nvoid main(void){\n   gl_Position = vec4((projectionMatrix * vec3(aVertexPosition, 1.0)).xy, 0.0, 1.0);\n   vTextureCoord = aTextureCoord;\n   vTextureId = aTextureId;\n   vColor = vec4(aColor.rgb * aColor.a, aColor.a);\n}';
          this.fragTemplate = 'varying vec2 vTextureCoord;\nvarying vec4 vColor;\nvarying float vTextureId;\nuniform sampler2D uSamplers[%count%];\n\nvoid main(void){\nvec4 color;\nfloat textureId = floor(vTextureId+0.5);\n%forloop%\ngl_FragColor = color * vColor;\n}';
          this.regexCount = Kotlin.kotlin.text.Regex_61zpoe$('%count%');
          this.regexForLoop = Kotlin.kotlin.text.Regex_61zpoe$('%forloop%');
        }, /** @lends _.pixi.sprite.webgl */ {
          BatchBuffer: Kotlin.createClass(null, function (size) {
            this.vertices = new ArrayBuffer(size);
            this.float32View = new Float32Array(this.vertices);
            this.uint32View = new Uint32Array(this.vertices);
          }),
          BatchGroup: Kotlin.createClass(null, function () {
            this.textures = Kotlin.kotlin.collections.mutableListOf_9mqe4v$([]);
            this.textureCount = 0;
            this.size = 0;
            this.start = 0;
            this.blend = _.pixi.BlendMode.NORMAL;
          }),
          createIndicesForQuads_za3lpa$: function (size) {
            var totalIndices = size * 6;
            var indices = new Uint16Array(totalIndices);
            var i = 0;
            var j = 0;
            while (i < totalIndices) {
              indices[i + 0] = j + 0;
              indices[i + 1] = j + 1;
              indices[i + 2] = j + 2;
              indices[i + 3] = j + 0;
              indices[i + 4] = j + 2;
              indices[i + 5] = j + 3;
              i += 6;
              j += 4;
            }
            return indices;
          },
          generateMultiTextureShader_6ioa9g$: function (renderer, maxTextures) {
            var tmp$0;
            var fragmentSrc = _.pixi.sprite.webgl.fragTemplate;
            fragmentSrc = _.pixi.sprite.webgl.regexCount.replace_x2uqeu$(fragmentSrc, maxTextures.toString());
            fragmentSrc = _.pixi.sprite.webgl.regexForLoop.replace_x2uqeu$(fragmentSrc, _.pixi.sprite.webgl.generateSampleSrc_za3lpa$(maxTextures));
            var shader = new _.pixi.renderers.Shader(renderer, _.pixi.sprite.webgl.vertexSrc, fragmentSrc);
            var sampleValues = new Int32Array(maxTextures);
            tmp$0 = maxTextures - 1;
            for (var i = 0; i <= tmp$0; i++) {
              sampleValues[i] = i;
            }
            shader.bind();
            shader.uniforms.uSamplers = sampleValues;
            return shader;
          },
          generateSampleSrc_za3lpa$: function (maxTextures) {
            var tmp$0;
            if (maxTextures === 1) {
              return 'color = texture2D(uSamplers[0], vTextureCoord);';
            }
            var src = new Kotlin.StringBuilder();
            src.append('\n');
            src.append('\n');
            tmp$0 = maxTextures - 1;
            for (var i = 0; i <= tmp$0; i++) {
              if (i > 0) {
                src.append('\nelse ');
              }
              if (i < maxTextures - 1) {
                src.append('if(textureId == ' + i + '.0)');
              }
              src.append('\n{');
              src.append('\n' + '\t' + 'color = texture2D(uSamplers[' + i + '], vTextureCoord);');
              src.append('\n}');
            }
            src.append('\n');
            src.append('\n');
            return src.toString();
          },
          SpriteRenderer: Kotlin.createClass(function () {
            return [_.pixi.Destroyable, _.pixi.renderers.ObjectRenderer];
          }, function $fun(renderer) {
            $fun.baseInitializer.call(this, renderer);
            this.$isDestroyed_b1z7ul$ = false;
            this.vertSize = 5;
            this.vertByteSize = this.vertSize * 4;
            this.size = 4096;
            this.buffers = Kotlin.kotlin.collections.mutableListOf_9mqe4v$([]);
            var i = 1;
            while (i <= _.pixi.math.BitTwiddle.nextPow2_za3lpa$(this.size)) {
              var numVertsTemp = i * 4 * this.vertByteSize;
              this.buffers.add_za3rmp$(new _.pixi.sprite.webgl.BatchBuffer(numVertsTemp));
              i *= 2;
            }
            this.indices = _.pixi.sprite.webgl.createIndicesForQuads_za3lpa$(this.size);
            this.shaders = [];
            this.MAX_TEXTURES = 0;
            this.tick = 0;
            this.groups = Kotlin.arrayFromFun(this.size, _.pixi.sprite.webgl.SpriteRenderer.groups$f);
            this.vertexBuffers = Kotlin.kotlin.collections.mutableListOf_9mqe4v$([]);
            this.vaoMax = 2;
            this.vaos = Kotlin.kotlin.collections.mutableListOf_9mqe4v$([]);
            this.vao = null;
            this.sprites = Kotlin.kotlin.collections.mutableListOf_9mqe4v$([]);
            this.indexBuffer = null;
            this.vertexCount = 0;
          }, /** @lends _.pixi.sprite.webgl.SpriteRenderer.prototype */ {
            isDestroyed: {
              get: function () {
                return this.$isDestroyed_b1z7ul$;
              },
              set: function (isDestroyed) {
                this.$isDestroyed_b1z7ul$ = isDestroyed;
              }
            },
            render_xzqyvq$: function (obj) {
              if (this.sprites.size >= this.size) {
                this.flush();
              }
              if (!obj.texture.valid) {
                return;
              }
              this.sprites.add_za3rmp$(obj);
            },
            onContextChange: function () {
              var tmp$0, tmp$1;
              var gl = this.renderer.gl;
              this.MAX_TEXTURES = Math.min(typeof (tmp$0 = gl.getParameter(WebGLRenderingContext.MAX_TEXTURE_IMAGE_UNITS)) === 'number' ? tmp$0 : Kotlin.throwCCE(), _.pixi.sprite.webgl.SpriteRenderer.Companion.MAX_TEXTURES);
              this.MAX_TEXTURES = _.pixi.utils.checkMaxIfStatementsInShader_5oq0xv$(this.MAX_TEXTURES, gl);
              this.shaders = Kotlin.arrayFromFun(this.MAX_TEXTURES, _.pixi.sprite.webgl.SpriteRenderer.onContextChange$f);
              this.shaders[0] = _.pixi.sprite.webgl.generateMultiTextureShader_6ioa9g$(this.renderer, 1);
              this.shaders[1] = _.pixi.sprite.webgl.generateMultiTextureShader_6ioa9g$(this.renderer, 2);
              this.indexBuffer = PIXI.glCore.GLBuffer.createIndexBuffer(gl, this.indices, WebGLRenderingContext.STATIC_DRAW);
              this.vertexBuffers.clear();
              this.vaos.clear();
              tmp$1 = this.vaoMax;
              for (var i = 1; i <= tmp$1; i++)
                this.addOneMoreVao();
              this.vao = this.vaos.get_za3lpa$(0);
            },
            addOneMoreVao: function () {
              var tmp$0, tmp$1, tmp$2, tmp$3, tmp$4, tmp$5;
              var gl = this.renderer.gl;
              var shader = (tmp$0 = this.shaders[1]) != null ? tmp$0 : Kotlin.throwNPE();
              var vb = PIXI.glCore.GLBuffer.createVertexBuffer(gl, null, WebGLRenderingContext.STREAM_DRAW);
              this.vertexBuffers.add_za3rmp$(vb);
              this.vaos.add_za3rmp$(this.renderer.createVao().addIndex((tmp$1 = this.indexBuffer) != null ? tmp$1 : Kotlin.throwNPE()).addAttribute(vb, (tmp$2 = shader.attributes['aVertexPosition']) != null ? tmp$2 : Kotlin.throwNPE(), WebGLRenderingContext.FLOAT, false, this.vertByteSize, 0).addAttribute(vb, (tmp$3 = shader.attributes['aTextureCoord']) != null ? tmp$3 : Kotlin.throwNPE(), WebGLRenderingContext.UNSIGNED_SHORT, true, this.vertByteSize, 2 * 4).addAttribute(vb, (tmp$4 = shader.attributes['aColor']) != null ? tmp$4 : Kotlin.throwNPE(), WebGLRenderingContext.UNSIGNED_BYTE, true, this.vertByteSize, 3 * 4).addAttribute(vb, (tmp$5 = shader.attributes['aTextureId']) != null ? tmp$5 : Kotlin.throwNPE(), WebGLRenderingContext.FLOAT, false, this.vertByteSize, 4 * 4));
            },
            onPrerender: function () {
              this.vertexCount = 0;
            },
            flush: function () {
              var tmp$0, tmp$1, tmp$2, tmp$3, tmp$4, tmp$5, tmp$6, tmp$7, tmp$8, tmp$9;
              var currentIndex = this.sprites.size;
              if (currentIndex === 0) {
                return;
              }
              var gl = this.renderer.gl;
              var np2 = _.pixi.math.BitTwiddle.nextPow2_za3lpa$(currentIndex);
              var log2 = _.pixi.math.BitTwiddle.log2_za3lpa$(np2);
              var buffer = this.buffers.get_za3lpa$(log2);
              var float32View = buffer.float32View;
              var uint32View = buffer.uint32View;
              var index = 0;
              var groupCount = 1;
              var textureCount = 1;
              var currentGroup = this.groups[0];
              var blendMode = this.sprites.get_za3lpa$(0).blendMode;
              var currentTexture = null;
              currentGroup.textureCount = 0;
              currentGroup.textures.clear();
              currentGroup.start = 0;
              currentGroup.blend = _.pixi.BlendMode.NORMAL;
              this.tick++;
              tmp$0 = currentIndex - 1;
              for (var i = 0; i <= tmp$0; i++) {
                var sprite = this.sprites.get_za3lpa$(i);
                var nextTexture = sprite.texture.baseTexture;
                if (blendMode !== sprite.blendMode) {
                  blendMode = sprite.blendMode;
                  currentTexture = null;
                  textureCount = this.MAX_TEXTURES;
                  this.tick++;
                }
                if (!Kotlin.equals(currentTexture, nextTexture)) {
                  currentTexture = nextTexture;
                  if (nextTexture._enabled !== this.tick) {
                    if (textureCount === this.MAX_TEXTURES) {
                      tmp$1 = this, tmp$2 = tmp$1.tick, tmp$3 = tmp$2, tmp$1.tick = tmp$2 + 1, tmp$3;
                      textureCount = 0;
                      currentGroup.size = i - currentGroup.start;
                      currentGroup = this.groups[groupCount++];
                      currentGroup.textureCount = 0;
                      currentGroup.blend = blendMode;
                      currentGroup.start = i;
                      currentGroup.textures.clear();
                    }
                    nextTexture._enabled = this.tick;
                    nextTexture._id = textureCount;
                    currentGroup.textures.add_za3rmp$(nextTexture);
                    tmp$4 = currentGroup, tmp$5 = tmp$4.textureCount, tmp$6 = tmp$5, tmp$4.textureCount = tmp$5 + 1, tmp$6;
                    textureCount++;
                  }
                }
                var vertexData = sprite.vertexData;
                var tint = sprite.worldMulColor.rgbaInt;
                var uvs = sprite.texture._uvs.uvsUint32;
                var textureId = nextTexture._id * 1.0;
                if (this.renderer.roundPixels) {
                  var resolution = this.renderer.resolution;
                  float32View[index] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 0) * resolution) / resolution;
                  float32View[index + 1] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 1) * resolution) / resolution;
                  float32View[index + 5] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 2) * resolution) / resolution;
                  float32View[index + 6] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 3) * resolution) / resolution;
                  float32View[index + 10] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 4) * resolution) / resolution;
                  float32View[index + 11] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 5) * resolution) / resolution;
                  float32View[index + 15] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 6) * resolution) / resolution;
                  float32View[index + 16] = Math.floor(_.pixi.utils.get_25qnj7$(vertexData, 7) * resolution) / resolution;
                }
                 else {
                  float32View[index] = _.pixi.utils.get_25qnj7$(vertexData, 0);
                  float32View[index + 1] = _.pixi.utils.get_25qnj7$(vertexData, 1);
                  float32View[index + 5] = _.pixi.utils.get_25qnj7$(vertexData, 2);
                  float32View[index + 6] = _.pixi.utils.get_25qnj7$(vertexData, 3);
                  float32View[index + 10] = _.pixi.utils.get_25qnj7$(vertexData, 4);
                  float32View[index + 11] = _.pixi.utils.get_25qnj7$(vertexData, 5);
                  float32View[index + 15] = _.pixi.utils.get_25qnj7$(vertexData, 6);
                  float32View[index + 16] = _.pixi.utils.get_25qnj7$(vertexData, 7);
                }
                uint32View[index + 2] = _.pixi.utils.get_9qikkl$(uvs, 0);
                uint32View[index + 7] = _.pixi.utils.get_9qikkl$(uvs, 1);
                uint32View[index + 12] = _.pixi.utils.get_9qikkl$(uvs, 2);
                uint32View[index + 17] = _.pixi.utils.get_9qikkl$(uvs, 3);
                uint32View[index + 3] = tint;
                uint32View[index + 8] = tint;
                uint32View[index + 13] = tint;
                uint32View[index + 18] = tint;
                float32View[index + 4] = textureId;
                float32View[index + 9] = textureId;
                float32View[index + 14] = textureId;
                float32View[index + 19] = textureId;
                index += 20;
              }
              currentGroup.size = currentIndex - currentGroup.start;
              this.vertexCount++;
              if (this.vaoMax <= this.vertexCount) {
                this.addOneMoreVao();
                this.vaoMax++;
              }
              this.vertexBuffers.get_za3lpa$(this.vertexCount).upload(buffer.vertices, 0);
              this.vao = this.vaos.get_za3lpa$(this.vertexCount).bind();
              tmp$7 = groupCount - 1;
              for (var i_0 = 0; i_0 <= tmp$7; i_0++) {
                var group = this.groups[i_0];
                var groupTextureCount = group.textureCount;
                var shader = (tmp$8 = this.shaders[groupTextureCount - 1]) != null ? tmp$8 : _.pixi.sprite.webgl.generateMultiTextureShader_6ioa9g$(this.renderer, groupTextureCount);
                this.renderer.bindShader_opcsrd$(shader);
                tmp$9 = groupTextureCount - 1;
                for (var j = 0; j <= tmp$9; j++) {
                  this.renderer.bindTexture_nqtr3l$(group.textures.get_za3lpa$(j), j);
                }
                this.renderer.state.setBlendMode_7sz4cw$(group.blend);
                gl.drawElements(WebGLRenderingContext.TRIANGLES, group.size * 6, WebGLRenderingContext.UNSIGNED_SHORT, group.start * 6 * 2);
              }
              this.sprites.clear();
            },
            start: function () {
              this.tick %= 1000;
            },
            stop: function () {
              var tmp$0;
              this.flush();
              (tmp$0 = this.vao) != null ? tmp$0.unbind() : null;
            },
            destroy: function () {
              var tmp$0, tmp$1, tmp$2, tmp$3;
              _.pixi.Destroyable.prototype.destroy.call(this);
              tmp$0 = this.vaoMax - 1;
              for (var i = 0; i <= tmp$0; i++) {
                this.vertexBuffers.get_za3lpa$(i).destroy();
                this.vaos.get_za3lpa$(i).destroy();
              }
              (tmp$1 = this.indexBuffer) != null ? tmp$1.destroy() : null;
              tmp$2 = this.shaders.length - 1;
              for (var i_0 = 0; i_0 <= tmp$2; i_0++) {
                (tmp$3 = this.shaders[i_0]) != null ? tmp$3.destroy() : null;
              }
            }
          }, /** @lends _.pixi.sprite.webgl.SpriteRenderer */ {
            onContextChange$f: function (i) {
              return null;
            },
            Companion: Kotlin.createObject(null, function () {
              _.pixi.sprite.webgl.SpriteRenderer.Companion.BATCH_SIZE = 4096;
              _.pixi.sprite.webgl.SpriteRenderer.Companion.MAX_TEXTURES = 32;
            }),
            object_initializer$: function () {
              _.pixi.sprite.webgl.SpriteRenderer.Companion;
            },
            groups$f: function (i) {
              return new _.pixi.sprite.webgl.BatchGroup();
            }
          })
        })
      }),
      textures: Kotlin.definePackage(null, /** @lends _.pixi.textures */ {
        BaseRenderTexture: Kotlin.createClass(function () {
          return [_.pixi.textures.BaseTexture];
        }, function $fun() {
          $fun.baseInitializer.call(this);
        }),
        BaseTexture: Kotlin.createClass(null, function (source, scaleMode, resolution) {
          if (source === void 0)
            source = null;
          if (scaleMode === void 0)
            scaleMode = _.pixi.ScaleMode.Companion.DEFAULT;
          if (resolution === void 0)
            resolution = 1;
          this.source = source;
          this.scaleMode = scaleMode;
          this.resolution = resolution;
          this.touched = 0;
          this.width = 100;
          this.height = 100;
          this.realWidth = 100;
          this.realHeight = 100;
          this.premultipliedAlpha = true;
          this.isPowerOfTwo = false;
          this.mipmap = false;
          this.wrapMode = _.pixi.WrapMode.Companion.DEFAULT;
          this._glTexture = null;
          this._enabled = 0;
          this._id = 0;
          var source_0 = this.source;
          if (source_0 != null) {
            this.update();
          }
        }, /** @lends _.pixi.textures.BaseTexture.prototype */ {
          update: function () {
            var tmp$0;
            var source = (tmp$0 = this.source) != null ? tmp$0 : Kotlin.throwNPE();
            if (Kotlin.isType(source, HTMLImageElement)) {
              this.realWidth = source.naturalWidth;
              this.realHeight = source.naturalHeight;
            }
             else if (Kotlin.isType(source, HTMLVideoElement)) {
              this.realWidth = source.videoWidth;
              this.realHeight = source.videoHeight;
            }
             else if (Kotlin.isType(source, HTMLCanvasElement)) {
              this.realWidth = source.width;
              this.realHeight = source.height;
            }
            this.width = this.realWidth / this.resolution | 0;
            this.height = this.realHeight / this.resolution | 0;
            this.isPowerOfTwo = (_.pixi.math.BitTwiddle.isPow2_za3lpa$(this.realWidth) && _.pixi.math.BitTwiddle.isPow2_za3lpa$(this.realHeight));
          }
        }),
        RenderTexture: Kotlin.createClass(function () {
          return [_.pixi.textures.Texture];
        }, function $fun(baseRenderTexture, frame) {
          if (frame === void 0)
            frame = null;
          $fun.baseInitializer.call(this, baseRenderTexture, frame);
          this.baseRenderTexture = baseRenderTexture;
        }),
        Texture: Kotlin.createClass(null, function (baseTexture, frame, orig, trim, rotate) {
          if (frame === void 0)
            frame = null;
          if (orig === void 0)
            orig = null;
          if (trim === void 0)
            trim = null;
          if (rotate === void 0)
            rotate = 0;
          this.baseTexture = baseTexture;
          this.frame = frame != null ? frame : new _.pixi.math.Frame(0, 0, this.baseTexture.width, this.baseTexture.height);
          this.orig = orig != null ? orig : new _.pixi.math.Frame(0, 0, this.frame.width, this.frame.height);
          this.trim = trim;
          this.valid = true;
          this._updateID = 0;
          this._uvs = new _.pixi.textures.TextureUVs();
          this._rotate_ydup63$ = rotate;
          this._updateUVs();
        }, /** @lends _.pixi.textures.Texture.prototype */ {
          rotate: {
            get: function () {
              return this._rotate_ydup63$;
            },
            set: function (value) {
              this._rotate_ydup63$ = value;
              this._updateUVs();
            }
          },
          _updateUVs: function () {
            this._uvs.set_lb4txl$(this.frame, this.baseTexture, this.rotate);
            this._updateID++;
          }
        }, /** @lends _.pixi.textures.Texture */ {
          Companion: Kotlin.createObject(null, function () {
            _.pixi.textures.Texture.Companion.EMPTY = new _.pixi.textures.Texture(new _.pixi.textures.BaseTexture());
            _.pixi.textures.Texture.Companion.EMPTY.valid = false;
          }),
          object_initializer$: function () {
            _.pixi.textures.Texture.Companion;
          }
        }),
        TextureUVs: Kotlin.createClass(null, function () {
          this.x0 = 0.0;
          this.y0 = 0.0;
          this.x1 = 1.0;
          this.y1 = 0.0;
          this.x2 = 1.0;
          this.y2 = 1.0;
          this.x3 = 0.0;
          this.y3 = 1.0;
          this.uvsUint32 = new Uint32Array(4);
        }, /** @lends _.pixi.textures.TextureUVs.prototype */ {
          set_lb4txl$: function (frame, baseFrame, rotate) {
            var tw = baseFrame.width * 1.0;
            var th = baseFrame.height * 1.0;
            this.x0 = frame.x / tw;
            this.y0 = frame.y / th;
            this.x1 = (frame.x + frame.width) / tw;
            this.y1 = frame.y / th;
            this.x2 = (frame.x + frame.width) / tw;
            this.y2 = (frame.y + frame.height) / th;
            this.x3 = frame.x / tw;
            this.y3 = (frame.y + frame.height) / th;
            this.uvsUint32[0] = (Math.floor(this.y0 * 65535) & 65535) << 16 | Math.floor(this.x0 * 65535) & 65535;
            this.uvsUint32[1] = (Math.floor(this.y1 * 65535) & 65535) << 16 | Math.floor(this.x1 * 65535) & 65535;
            this.uvsUint32[2] = (Math.floor(this.y2 * 65535) & 65535) << 16 | Math.floor(this.x2 * 65535) & 65535;
            this.uvsUint32[3] = (Math.floor(this.y3 * 65535) & 65535) << 16 | Math.floor(this.x3 * 65535) & 65535;
          }
        })
      }),
      utils: Kotlin.definePackage(function () {
        this.fragTemplate = '\nprecision mediump float;\nvoid main(void){\nfloat test = 0.1;\n%forloop%\ngl_FragColor = vec4(0.0);\n}\n';
        this.regexCount = Kotlin.kotlin.text.Regex_61zpoe$('%count%');
        this.regexForLoop = Kotlin.kotlin.text.Regex_61zpoe$('%forloop%');
        this._saidHello_mtyt57$ = false;
      }, /** @lends _.pixi.utils */ {
        checkMaxIfStatementsInShader_5oq0xv$: function (_maxIfs, _gl) {
          var tmp$0;
          var tempGL = null;
          var maxIfs = _maxIfs;
          if (_gl == null) {
            var tinyCanvas = Kotlin.isType(tmp$0 = document.createElement('canvas'), HTMLCanvasElement) ? tmp$0 : Kotlin.throwCCE();
            tinyCanvas.width = 1;
            tinyCanvas.height = 1;
            tempGL = _.pixi.gl.createContext_nr4ftb$(tinyCanvas);
          }
          var gl = _gl != null ? _gl : tempGL != null ? tempGL : Kotlin.throwNPE();
          var shader = gl.createShader(WebGLRenderingContext.FRAGMENT_SHADER);
          while (true) {
            var fragmentSrc = _.pixi.utils.regexForLoop.replace_x2uqeu$(_.pixi.utils.fragTemplate, _.pixi.utils.generateIfTestSrc_za3lpa$(maxIfs));
            gl.shaderSource(shader, fragmentSrc);
            gl.compileShader(shader);
            if (gl.getShaderParameter(shader, WebGLRenderingContext.COMPILE_STATUS) == null) {
              maxIfs /= 2;
            }
             else {
              break;
            }
          }
          if (tempGL != null) {
            if (gl.getExtension('WEBGL_lose_context')) {
              gl.getExtension('WEBGL_lose_context').loseContext();
            }
          }
          return maxIfs;
        },
        generateIfTestSrc_za3lpa$: function (maxIfs) {
          var tmp$0;
          var src = new Kotlin.StringBuilder();
          tmp$0 = maxIfs - 1;
          for (var i = 0; i <= tmp$0; i++) {
            if (i > 0) {
              src.append('\nelse ');
            }
            if (i < maxIfs - 1) {
              src.append('if(test == ' + i + '.0){}');
            }
          }
          return src.toString();
        },
        removeRange_125sxr$: function ($receiver, beginIndex, endIndex) {
          var tmp$0;
          if (beginIndex === void 0)
            beginIndex = 0;
          if (endIndex === void 0)
            endIndex = $receiver.size;
          var range = endIndex - beginIndex;
          if (range < 0)
            return;
          tmp$0 = $receiver.size - range - 1;
          for (var i = beginIndex; i <= tmp$0; i++) {
            $receiver.set_vux3hl$(i, $receiver.get_za3lpa$(i + range));
          }
        },
        get_ynspw0$: function ($receiver, index) {
          var tmp$0;
          return (tmp$0 = $receiver[index]) != null ? tmp$0 : Kotlin.throwNPE();
        },
        get_gt95ti$: function ($receiver, index) {
          var tmp$0;
          return (tmp$0 = $receiver[index]) != null ? tmp$0 : Kotlin.throwNPE();
        },
        get_vbk1hc$: function ($receiver, index) {
          var tmp$0;
          return (tmp$0 = $receiver[index]) != null ? tmp$0 : Kotlin.throwNPE();
        },
        get_9qikkl$: function ($receiver, index) {
          var tmp$0;
          return (tmp$0 = $receiver[index]) != null ? tmp$0 : Kotlin.throwNPE();
        },
        get_9tjlp1$: function ($receiver, index) {
          var tmp$0;
          return (tmp$0 = $receiver[index]) != null ? tmp$0 : Kotlin.throwNPE();
        },
        get_25qnj7$: function ($receiver, index) {
          var tmp$0;
          return (tmp$0 = $receiver[index]) != null ? tmp$0 : Kotlin.throwNPE();
        },
        sayHello_61zpoe$: function (type) {
          if (_.pixi.utils._saidHello_mtyt57$) {
            return;
          }
          var args = [];
          args.push('\n' + ' %c %c %c Pixi.js ' + _.pixi.VERSION + ' - \u2730 ' + type + ' \u2730  %c  %c   http://www.pixijs.com/  %c %c \u2665%c\u2665%c\u2665 ' + '\n' + '\n');
          args.push('background: #ff66a5; padding:5px 0;');
          args.push('background: #ff66a5; padding:5px 0;');
          args.push('color: #ff66a5; background: #030307; padding:5px 0;');
          args.push('background: #ff66a5; padding:5px 0;');
          args.push('background: #ffc3dc; padding:5px 0;');
          args.push('background: #ff66a5; padding:5px 0;');
          args.push('color: #ff2424; background: #fff; padding:5px 0;');
          args.push('color: #ff2424; background: #fff; padding:5px 0;');
          args.push('color: #ff2424; background: #fff; padding:5px 0;');
          console.log.apply(console, args);
          _.pixi.utils._saidHello_mtyt57$ = true;
        }
      })
    })
  });
  Kotlin.defineModule('pixi', _);
  _.hello.main_kand9s$([]);
  return _;
}(kotlin);

//@ sourceMappingURL=pixi.js.map
