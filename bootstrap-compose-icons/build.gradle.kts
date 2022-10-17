import org.jetbrains.compose.*
import java.util.*

plugins {
    kotlin("js")
    id("org.jetbrains.compose")
    `maven-publish`
    signing
}

group = "app.softwork"

repositories {
    mavenCentral()
    jetbrainsCompose()
}

kotlin {
    js(IR) {
        browser {
            binaries.library()
            useCommonJs()
        }
    }

    explicitApi()
}

tasks {
    val generateSVG by registering(app.softwork.bootstrapcompose.icons.ConvertSvg::class) {
        dependsOn(rootProject.tasks.getByName("kotlinNpmInstall"))
        icons.set(rootProject.file("build/js/node_modules/bootstrap-icons/icons"))
        outputDir.set(File(project.buildDir, "generated/icons"))
        kotlin.sourceSets["main"].kotlin.srcDir(outputDir)
    }
    getByName("compileKotlinJs") {
        dependsOn(generateSVG)
    }
}

dependencies {
    api(compose.web.core)
    api(compose.web.svg)
    implementation(devNpm("bootstrap-icons", "1.9.1"))

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
                name.set("app.softwork Bootstrap Icons Library for JetBrains Compose Web")
                description.set("A wrapper for Bootstrap Icons to use with JetBrains Compose Web")
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
