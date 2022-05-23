import org.jetbrains.compose.*

plugins {
    kotlin("js")
    id("org.jetbrains.compose")
}

repositories {
    mavenCentral()
    jetbrainsCompose()
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
    implementation(projects.bootstrapCompose)
}
