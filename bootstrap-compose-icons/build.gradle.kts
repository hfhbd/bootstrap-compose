import org.jetbrains.compose.*

plugins {
    kotlin("js")
    id("org.jetbrains.compose")
    publishJs
}

repositories {
    mavenCentral()
    jetbrainsCompose()
}

kotlin {
    js(IR) {
        browser()
    }

    explicitApi()
}

val bootstrapIconsVersion = "1.9.1"

val generateSVG by tasks.registering(app.softwork.bootstrapcompose.icons.ConvertSvg::class) {
    dependsOn(rootProject.tasks.named("kotlinNpmInstall"))
    icons.set(rootProject.file("build/js/node_modules/bootstrap-icons/icons"))
    outputDir.set(File(project.buildDir, "generated/icons"))
    iconTableName.set(project.file("generatedIcons.md"))
    version.set(bootstrapIconsVersion)
}

tasks.named("compileKotlinJs") {
    dependsOn(generateSVG)
}

kotlin.sourceSets["main"].kotlin.srcDir(generateSVG.map { it.packageFolder })

dependencies {
    api(compose.web.core)
    api(compose.web.svg)
    implementation(devNpm("bootstrap-icons", bootstrapIconsVersion))

    testImplementation(compose.web.testUtils)
    testImplementation(kotlin("test"))
}
