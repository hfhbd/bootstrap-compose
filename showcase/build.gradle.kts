plugins {
    id("setup")
}

kotlin {
    js {
        browser {
            binaries.executable()
            useEsModules()
            compilerOptions {
                target.set("es2015")
            }
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
