import org.jetbrains.compose.*
import java.util.*
import io.gitlab.arturbosch.detekt.*

plugins {
    kotlin("js") version "1.7.10"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev745"
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("io.gitlab.arturbosch.detekt") version "1.20.0"
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
            useCommonJs()
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }

    explicitApi()
}

dependencies {
    api("app.softwork:kotlinx-uuid-core:0.0.15")
    api(compose.web.core)
    api(npm("bootstrap", "5.1.3"))
    api(npm("@popperjs/core", "2.11.5"))

    implementation(devNpm("sass-loader", "^13.0.0"))

    testImplementation(compose.web.testUtils)
    testImplementation(kotlin("test"))
}

val emptyJar by tasks.registering(Jar::class) {
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            artifact(emptyJar) {
                classifier = "javadoc"
            }
            pom {
                name.set("app.softwork Bootstrap Library for JetBrains Compose Web")
                description.set("A wrapper for Bootstrap to use with JetBrains Compose Web")
                url.set("https://github.com/hfhbd/bootstrap-compose")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("hfhbd")
                        name.set("Philip Wedemann")
                        email.set("mybztg+mavencentral@icloud.com")
                    }
                }
                scm {
                    connection.set("scm:git://github.com/hfhbd/bootstrap-compose.git")
                    developerConnection.set("scm:git://github.com/hfhbd/bootstrap-compose.git")
                    url.set("https://github.com/hfhbd/bootstrap-compose")
                }
            }
        }
    }
}

(System.getProperty("signing.privateKey") ?: System.getenv("SIGNING_PRIVATE_KEY"))?.let {
    String(Base64.getDecoder().decode(it)).trim()
}?.let { key ->
    println("found key, config signing")
    signing {
        val signingPassword = System.getProperty("signing.password") ?: System.getenv("SIGNING_PASSWORD")
        useInMemoryPgpKeys(key, signingPassword)
        sign(publishing.publications)
    }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getProperty("sonartype.apiKey") ?: System.getenv("SONARTYPE_APIKEY"))
            password.set(System.getProperty("sonartype.apiToken") ?: System.getenv("SONARTYPE_APITOKEN"))
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

detekt {
    source = files(rootProject.rootDir)
    parallel = true
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")
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
