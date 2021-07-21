plugins {
    kotlin("multiplatform") version "1.5.21"
    id("org.jetbrains.compose") version "0.5.0-build262"
    `maven-publish`
}

group = "app.softwork"

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser {
            binaries.library()
        }
    }

    explicitApi()

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jsMain by getting {
            dependencies {
                api("app.softwork:kotlinx-uuid-core:0.0.9")
                api(compose.web.core)
            }
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/hfhbd/bootstrap-compose")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
