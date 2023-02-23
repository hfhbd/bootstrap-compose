plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.84.3") {
        exclude("io.github.pdvrieze.xmlutil", "core")
    }
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.84.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    testImplementation(kotlin("test-junit"))
}

gradlePlugin {
    plugins {
        register("typedIds") {
            id = "typedIds"
            implementationClass = "TypedIds"
        }
    }
}
