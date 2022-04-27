import org.jetbrains.compose.*

plugins {
    kotlin("multiplatform")
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

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(projects.bootstrapCompose)
            }
        }
    }
}
