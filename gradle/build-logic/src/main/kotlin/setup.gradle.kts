plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("app.cash.licensee")
}

kotlin {
    js {
        useEsModules()
        browser {
            commonWebpackConfig {
                scssSupport {
                    enabled.set(true)
                }
            }
        }
        compilerOptions {
            target.set("es2015")
        }
    }
    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec>().downloadBaseUrl = null
}

licensee {
    allow("Apache-2.0")
}
