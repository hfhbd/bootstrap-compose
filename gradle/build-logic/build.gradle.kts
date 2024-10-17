plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.plugins.kotlin.js.toDep())
    implementation(libs.plugins.kotlin.plugin.compose.toDep())
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.90.2") {
        exclude("io.github.pdvrieze.xmlutil", "core")
    }
    implementation("io.github.pdvrieze.xmlutil:core-jdk:0.90.1")

    testImplementation(kotlin("test"))
}

fun Provider<PluginDependency>.toDep(): Provider<String> = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}
