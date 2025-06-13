plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

dependencies {
    implementation(libs.plugins.kotlin.js.toDep())
    implementation(libs.plugins.kotlin.plugin.compose.toDep())
    implementation(libs.plugins.licensee.toDep())
    implementation(libs.plugins.detekt.toDep())
    implementation(libs.plugins.mavencentral.toDep())
    implementation(libs.serialization.xml) {
        exclude("io.github.pdvrieze.xmlutil", "core")
    }
    implementation(libs.serialization.corejdk)
}

fun Provider<PluginDependency>.toDep(): Provider<String> = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}
