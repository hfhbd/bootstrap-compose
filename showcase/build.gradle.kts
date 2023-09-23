plugins {
    id("setup")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
        }
    }
}

dependencies {
    implementation(projects.bootstrapCompose)
    implementation(projects.bootstrapCompose.bootstrapComposeIcons)
}
