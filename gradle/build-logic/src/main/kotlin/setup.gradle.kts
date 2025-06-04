plugins {
    kotlin("multiplatform")
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
