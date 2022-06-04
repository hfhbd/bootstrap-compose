# bootstrap-compose

Highly experimental predefined Bootstrap functions to use in [Compose Web](https://github.com/Jetbrains/compose-jb)

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
                cssSupport.enabled = true
            }
        }
    }
}

dependencies {
    implementation("app.softwork:bootstrap-compose:LATEST")
    implementation("app.softwork:bootstrap-compose-icons:LATEST") // for icons support
    implementation(devNpm("sass-loader", "^13.0.0"))
}
```

And this `sccs.js` in your `webpack.config.d`:

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

Include your `scss` file in your `ressources` folder:

````scss
// Variable overrides first
$primary: #900;

// Then import Bootstrap
@import "bootstrap/scss/bootstrap";

````

and load it in your main method.

````kotlin
fun main() {
    require("./custom.scss")
}
````
