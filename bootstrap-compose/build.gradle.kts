plugins {
    id("publishedLibrary")
}

kotlin {
    sourceSets {
        jsMain {
            dependencies {
                api(libs.compose.runtime)
                api(libs.compose.html.core)
                api(npm("bootstrap", "5.2.3"))
                api(npm("@popperjs/core", "2.11.6"))
            }
        }
        jsTest {
            dependencies {
                implementation(libs.compose.runtime)
                implementation(libs.compose.html.testUtils)
                implementation(libs.coroutines.test)
            }
        }
    }
}
