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
                scssSupport {
                    enabled = true
                }
            }
        }
    }
}

dependencies {
    implementation(projects.bootstrapCompose)
    implementation(projects.bootstrapCompose.bootstrapComposeIcons)
}
