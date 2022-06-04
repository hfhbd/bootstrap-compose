pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "bootstrap-compose"

include(":showcase")
include(":icons")
project(":icons").name = "bootstrap-compose-icons"
