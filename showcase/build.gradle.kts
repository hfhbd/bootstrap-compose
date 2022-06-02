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
        }
    }
}

dependencies {
    implementation(projects.bootstrapCompose)

    implementation(devNpm("style-loader", "^3.3.1"))
    implementation(devNpm("css-loader", "^6.7.1"))
    implementation(devNpm("sass-loader", "^13.0.0"))
    implementation(devNpm("sass", "^1.52.1"))
}
