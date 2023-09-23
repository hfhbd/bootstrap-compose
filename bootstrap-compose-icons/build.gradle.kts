plugins {
    id("org.jetbrains.compose")
    id("sign")
}


val generateSVG by tasks.registering(app.softwork.bootstrapcompose.icons.ConvertSvg::class) {
    dependsOn(rootProject.tasks.named("kotlinNpmInstall"))
    icons.set(rootProject.file("build/js/node_modules/bootstrap-icons/icons"))
    outputDir.set(project.layout.buildDirectory.dir("generated/icons"))
}
kotlin.sourceSets["main"].kotlin.srcDir(generateSVG)

dependencies {
    api(compose.html.core)
    api(compose.html.svg)
    implementation(devNpm("bootstrap-icons", "1.9.1"))

    testImplementation(compose.html.testUtils)
    testImplementation(kotlin("test"))
}
