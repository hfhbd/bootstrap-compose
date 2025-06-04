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
