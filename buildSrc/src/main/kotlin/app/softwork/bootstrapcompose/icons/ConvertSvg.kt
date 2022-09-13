package app.softwork.bootstrapcompose.icons

import kotlinx.serialization.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.*
import nl.adaptivity.xmlutil.serialization.*
import org.gradle.api.*
import org.gradle.api.file.*
import org.gradle.api.tasks.*
import org.gradle.configurationcache.extensions.*
import org.gradle.work.*
import java.io.*

@CacheableTask
abstract class ConvertSvg : DefaultTask() {
    @get:Incremental
    @get:PathSensitive(PathSensitivity.NAME_ONLY)
    @get:InputDirectory
    abstract val icons: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun doConverting() {
        val outputDir = outputDir.get().asFile
        icons.asFileTree.forEachIndexed { index, file ->
            val name = file.nameWithoutExtension
            println("$index $name")
            File(outputDir, "$name.kt")
                .writeText(convertSvgToComposeSvg(file.readText(), name))
        }
    }
}

private val xml = XML(
    serializersModule = SerializersModule {
        polymorphic(Content::class) {
            subclass(Path::class)
            subclass(Circle::class)
            subclass(Rect::class)
        }
    }
)

private fun convertSvgToComposeSvg(input: String, fileName: String): String {
    val xml = xml.decodeFromString(SVG.serializer(), input)

    return xml.compose(fileName.toPascalCase())
}

private val regex = Regex("-(\\S)")
private fun String.toPascalCase(): String = capitalized().replace(regex) {
    it.groups[1]!!.value.capitalized()
}
    .replace("1", "One")
    .replace("2", "Two")
    .replace("3", "Three")
    .replace("4", "Four")
    .replace("5", "Five")
    .replace("6", "Six")
    .replace("7", "Seven")
    .replace("8", "Eight")
    .replace("9", "Nine")

@XmlSerialName("svg", "http://www.w3.org/2000/svg", "")
@Serializable
private data class SVG(
    val width: String,
    val height: String,
    val fill: String,
    @SerialName("class") val classes: String,
    val viewBox: String,
    @XmlElement(true)
    @XmlPolyChildren([".Path", ".Circle", ".Rect"])
    val content: List<@Polymorphic Content>
) {

    val composeClasses get() = classes.split(" ").joinToString(", ") { "\"$it\"" }
    fun compose(fileName: String): String = """package app.softwork.bootstrapcompose.icons

import androidx.compose.runtime.*
import org.jetbrains.compose.web.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.svg.*
import org.w3c.dom.svg.*

@ExperimentalComposeWebSvgApi
@Composable
@NonRestartableComposable
public fun $fileName(attrs: AttrBuilderContext<SVGElement>? = null) {
    Svg(
        viewBox = "$viewBox",
        attrs = {
            width(${width.toInt()})
            height(${height.toInt()})
            xmlns("http://www.w3.org/2000/svg")
            fill("$fill")
            classes($composeClasses)
            attrs?.invoke(this)
        }
    ) {
${content.joinToString(separator = "\n") {
        "        ${it.toCompose()}"
    }}
    }
}
"""
}

private interface Content {
    fun toCompose(): String
}

@XmlSerialName("path", "http://www.w3.org/2000/svg", "")
@Serializable
private data class Path(val d: String, @SerialName("fill-rule") val fillRule: String? = null) : Content {
    override fun toCompose() = buildString {
        append("""Path("$d"""")
        if (fillRule != null) {
            append(", attrs = {")
            append("""fillRule("$fillRule")""")
            append("}")
        }
        append(")")
    }
}

@XmlSerialName("circle", "http://www.w3.org/2000/svg", "")
@Serializable
private data class Circle(
    val cx: String,
    val cy: String,
    val r: String
) : Content {
    override fun toCompose() = """Circle($cx, $cy, $r)"""
}

@XmlSerialName("rect", "http://www.w3.org/2000/svg", "")
@Serializable
private data class Rect(
    val width: String,
    val height: String,
    val x: String?,
    val y: String?,
    val rx: String,
    val transform: String? = null
) : Content {
    override fun toCompose() = buildString {
        if (x == null || y == null) {
            append("""Rect($width, $height, $rx, $rx, "$transform")""")
        } else {
            append("""Rect($x, $y, $width, $height, attrs = {""")
            append("""rx($rx)""")
            append("})")
        }
    }
}
