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
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(projects.bootstrapCompose)
    implementation(projects.bootstrapCompose.bootstrapComposeIcons)
}
