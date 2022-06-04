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
            useCommonJs()
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}

dependencies {
    implementation(projects.bootstrapCompose)

    implementation(devNpm("sass-loader", "^13.0.0"))
    implementation(devNpm("sass", "^1.52.1"))
}
