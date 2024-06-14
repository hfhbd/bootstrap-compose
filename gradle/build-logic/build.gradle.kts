plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

dependencies {
    implementation(libs.plugins.kotlin.js.toDep())
    implementation(libs.plugins.kotlin.plugin.compose.toDep())
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.90.0") {
        exclude("io.github.pdvrieze.xmlutil", "core")
    }
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.86.3")

    testImplementation(kotlin("test"))
}

fun Provider<PluginDependency>.toDep(): Provider<String> = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}
