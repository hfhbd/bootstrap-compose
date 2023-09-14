plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.86.2")

    testImplementation(kotlin("test-junit"))
}
