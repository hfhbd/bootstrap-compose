plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version "1.5.31" // same kotlin version of kotlin-dsl
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.84.2")

    testImplementation(kotlin("test-junit"))
}
