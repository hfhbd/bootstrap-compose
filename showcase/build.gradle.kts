plugins {
    id("setup")
    id("org.jetbrains.compose")
}

kotlin {
    js {
        browser {
            binaries.executable()
        }
    }
}

dependencies {
    implementation(projects.bootstrapCompose)
    implementation(projects.bootstrapCompose.bootstrapComposeIcons)
}
