# bootstrap-compose

Predefined Bootstrap functions to use in [Compose HTML](https://github.com/Jetbrains/compose-jb)

## Install

This package is uploaded to MavenCentral.

```kotlin
repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            binaries.executable()
            useESModules()
            commonWebpackConfig {
                scssSupport {
                    enabled = true
                }
            }
        }
        compilerOptions {
            target = "es2015"
        }
    }
}

dependencies {
    implementation("app.softwork:bootstrap-compose:LATEST")
    implementation("app.softwork:bootstrap-compose-icons:LATEST") // for icons support
}
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
@JsModule("./custom.scss")
@JsNonModule
private external val customSCSS: dynamic

fun main() {
    customSCSS
}
````
