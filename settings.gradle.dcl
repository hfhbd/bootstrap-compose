pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("MyRepos")
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

rootProject.name = "bootstrap-compose"

include(":bootstrap-compose")
include(":bootstrap-compose-icons")

include(":showcase")
