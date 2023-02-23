import java.util.*

plugins {
    `maven-publish`
    signing
}

val emptyJar by tasks.registering(Jar::class)

publishing {
    publications {
        register<MavenPublication>("maven") {
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
