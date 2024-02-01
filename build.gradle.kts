import io.gitlab.arturbosch.detekt.*

plugins {
    id("org.jetbrains.compose") version "1.5.11"
    id("sign")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.5"
    id("app.cash.licensee") version "1.9.0"
}

dependencies {
    api("app.softwork:kotlinx-uuid-core:0.0.22")
    api(compose.html.core)
    api(npm("bootstrap", "5.2.3"))
    api(npm("@popperjs/core", "2.11.5"))

    testImplementation(compose.html.testUtils)
    testImplementation(kotlin("test"))
}

licensee {
    allow("Apache-2.0")
}

nexusPublishing {
    this.repositories {
        sonatype {
            username.set(System.getProperty("sonartype.apiKey") ?: System.getenv("SONARTYPE_APIKEY"))
            password.set(System.getProperty("sonartype.apiToken") ?: System.getenv("SONARTYPE_APITOKEN"))
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
