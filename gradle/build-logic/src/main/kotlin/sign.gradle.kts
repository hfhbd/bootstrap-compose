import org.gradle.api.publish.maven.*
import org.gradle.kotlin.dsl.*
import java.util.*

plugins {
    id("setup")
    id("maven-publish")
    id("signing")
}

kotlin {
    js {
        browser {
            binaries.library()
        }
    }

    explicitApi()
}

val emptyJar by tasks.registering(Jar::class)

publishing {
    publications {
        register<MavenPublication>("maven") {
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

signing {
    val signingKey = providers.gradleProperty("SIGNING_PRIVATE_KEY")
    if (signingKey.isPresent) {
        useInMemoryPgpKeys(String(Base64.getDecoder().decode(signingKey.get())).trim(), providers.gradleProperty("SIGNING_PASSWORD").get())
        sign(publishing.publications)
    }
}
