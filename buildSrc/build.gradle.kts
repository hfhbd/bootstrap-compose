plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.84.2")

    testImplementation(kotlin("test-junit"))
}
