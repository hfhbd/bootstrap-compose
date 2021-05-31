# bootstrap-compose
Highly experimental predefined Bootstrap functions to use in [Compose Web](https://github.com/Jetbrains/compose-jb)

## Install

This package is uploaded
to [GitHub Packages](https://docs.github.com/en/packages/guides/configuring-gradle-for-use-with-github-packages).

````kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/hfhbd/*")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("app.softwork:bootstrap-compose:0.0.1")
}
````

## Usage
You still need to implement the Bootstrap js/css files manually.
