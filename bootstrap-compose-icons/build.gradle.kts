plugins {
    id("sign")
    kotlin("plugin.compose")
}

val generateSVG by tasks.registering(app.softwork.bootstrapcompose.icons.ConvertSvg::class) {
    dependsOn(rootProject.tasks.named("kotlinNpmInstall"))
    icons.set(rootProject.file("build/js/node_modules/bootstrap-icons/icons"))
    outputDir.set(project.layout.buildDirectory.dir("generated/icons"))
}

kotlin.sourceSets.main {
    kotlin.srcDir(generateSVG)
}

dependencies {
    compileOnly(libs.compose.runtime)
    api(libs.compose.html.core)
    api(libs.compose.html.svg)
    implementation(devNpm("bootstrap-icons", "1.11.1"))

    testImplementation(kotlin("test"))
    testImplementation(libs.compose.runtime)
    testImplementation(libs.compose.html.testUtils)
}
