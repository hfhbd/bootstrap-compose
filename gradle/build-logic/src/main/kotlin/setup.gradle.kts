plugins {
    kotlin("js")
}

kotlin {
    js(IR) {
        browser {
            useCommonJs()
            commonWebpackConfig {
                scssSupport {
                    enabled.set(true)
                }
            }
        }
    }
}
