plugins {
    id("setup")
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
                implementation(project(":bootstrap-compose"))
                implementation(project(":bootstrap-compose-icons"))
            }
        }
    }
}
