import io.gitlab.arturbosch.detekt.*

plugins {
    id("sign")
    kotlin("plugin.compose")
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
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

nexusPublishing {
    this.repositories {
        sonatype {
            username.set(providers.gradleProperty("SONARTYPE_APIKEY"))
            password.set(providers.gradleProperty("SONARTYPE_APITOKEN"))
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

detekt {
    source.from(files(rootProject.rootDir))
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${detekt.toolVersion}")
}

tasks {
    fun SourceTask.config() {
        include("**/*.kt")
        exclude("**/*.kts")
        exclude("**/resources/**")
        exclude("**/generated/**")
        exclude("**/build/**")
    }
    withType<DetektCreateBaselineTask>().configureEach {
        config()
    }
    withType<Detekt>().configureEach {
        config()

        reports {
            sarif.required.set(true)
        }
    }
}
