# bootstrap-compose
Highly experimental predefined Bootstrap functions to use in [Compose Web](https://github.com/Jetbrains/compose-jb)

## Install

This package is uploaded to MavenCentral.

````kotlin
repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
            
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}

dependencies {
    implementation("app.softwork:bootstrap-compose:LATEST")
}
````
