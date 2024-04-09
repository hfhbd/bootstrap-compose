# bootstrap-compose

Predefined Bootstrap functions to use in [Compose HTML](https://github.com/Jetbrains/compose-jb)

## Install

This package is uploaded to MavenCentral.

```kotlin
repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
            useCommonJs()
            commonWebpackConfig {
                // Kotlin >= 1.7.20
                scssSupport {
                    enabled = true
                }
                
                // Kotlin <= 1.7.10
                cssSupport.enabled = true
            }
        }
    }
}

dependencies {
    implementation("app.softwork:bootstrap-compose:LATEST")
    implementation("app.softwork:bootstrap-compose-icons:LATEST") // for icons support
    
    implementation(devNpm("sass-loader", "^13.0.0")) // only needed with Kotlin <= 1.7.10
    implementation(devNpm("sass", "^1.52.1")) // only needed with Kotlin <= 1.7.10
}
```

And this `sccs.js` in your `webpack.config.d` (if not present, create this directory at the root of your project) if you use Kotlin <= 1.7.10:

```js
config.module.rules.push({
    test: /\.(scss|sass)$/,
    use: [
        /**
         *  fallback to style-loader in development
         *  "style-loader" creates style nodes from JS strings
         */
        "style-loader",   // translates CSS into CommonJS
        "css-loader",   // translates CSS into CommonJS
        "sass-loader"   // compiles Sass to CSS, using Node Sass by default
    ]
});
```

## SCSS

Include your `scss` file (e.g. `custom.scss`) in your `resources` folder:

````scss
// Variable overrides first
$primary: #900;

// Then import Bootstrap
@import "bootstrap/scss/bootstrap";

````

and load it in your main method.

````kotlin
import app.softwork.bootstrapcompose.require

fun main() {
    require("./custom.scss")
}
````
