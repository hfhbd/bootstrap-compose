plugins {
    kotlin("js")
}

kotlin {
    js {
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
