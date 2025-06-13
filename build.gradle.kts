plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    source.from(fileTree(layout.settingsDirectory) {
        include("**/*.kt")
        exclude("**/*.kts")
        exclude("**/resources/**")
        exclude("**/generated/**")
        exclude("**/build/**")
    })
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
    reports {
        sarif.required.set(true)
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${detekt.toolVersion}")
    }
}

plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec>().downloadBaseUrl = null
}
