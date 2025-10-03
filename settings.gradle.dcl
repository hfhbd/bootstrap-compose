pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("MyRepos")
    id("org.gradle.toolchains.foojay-resolver-convention").version("1.0.0")
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

rootProject.name = "bootstrap-compose"

include(":bootstrap-compose")
include(":bootstrap-compose-icons")

include(":showcase")
