plugins {
    id("sign")
    kotlin("plugin.compose")
    id("io.github.hfhbd.mavencentral") version "0.0.15"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("app.cash.licensee") version "1.12.0"
}

dependencies {
    compileOnly(libs.compose.runtime)
    api(libs.compose.html.core)
    api(npm("bootstrap", "5.2.3"))
    api(npm("@popperjs/core", "2.11.6"))

    testImplementation(kotlin("test"))
    testImplementation(libs.compose.runtime)
    testImplementation(libs.compose.html.testUtils)
    testImplementation(libs.coroutines.test)
}

licensee {
    allow("Apache-2.0")
}

detekt {
    source.from(fileTree(rootProject.rootDir) {
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
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${detekt.toolVersion}")
}
