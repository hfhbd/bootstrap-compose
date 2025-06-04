plugins {
    id("setup")
    kotlin("plugin.compose")
}

kotlin {
    js {
        browser {
            binaries.executable()
        }
    }
    sourceSets {
        jsMain {
            dependencies {
                implementation(libs.compose.runtime)
                implementation(libs.coroutines.core)
                implementation(projects.bootstrapCompose)
                implementation(projects.bootstrapCompose.bootstrapComposeIcons)
            }
        }
    }
}

plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec>().downloadBaseUrl = null
}
