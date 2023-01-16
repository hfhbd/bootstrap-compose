package app.softwork.bootstrapcompose.icons

import com.ibm.icu.text.*
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
import java.util.*

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

        val packageFile = File(outputDir, "app/softwork/bootstrapcompose/icons")
        packageFile.mkdirs()

        val size = icons.asFileTree.count { file ->
            val name = file.nameWithoutExtension
            File(packageFile, "$name.kt")
                .writeText(convertSvgToComposeSvg(file.readText(), name))
            true
        }
        println("converted $size icons")
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
private val numberSpace = Regex(" (\\S)")
private val leadingNumber = Regex("(\\d+)([A-Za-z-]+)+")
val rule = RuleBasedNumberFormat(Locale("en", "US"), RuleBasedNumberFormat.SPELLOUT)

private fun String.toPascalCase2(): String = leadingNumber.replace(this) {
    println("${it.groupValues} $this")
    val value = it.groups[1]!!.value
    val number = value.toInt()
    rule.format(number).replace(numberSpace) {
        it.groups[1]!!.value.capitalized()
    }.capitalized() + (it.groups[2]?.value?.capitalized() ?: "")
}.capitalized().replace(regex) {
    it.groups[1]!!.value.capitalized()
}.also { println("$this -> $it") }

private fun String.toPascalCase(): String {
    return when {
        this == "123" -> "OneTwoThree"
        startsWith("0-") -> "Zero${drop(1)}"
        startsWith("1-") -> "One${drop(1)}"
        startsWith("2-") -> "Two${drop(1)}"
        startsWith("3-") -> "Three${drop(1)}"
        startsWith("4-") -> "Four${drop(1)}"
        startsWith("5-") -> "Five${drop(1)}"
        startsWith("6-") -> "Six${drop(1)}"
        startsWith("7-") -> "Seven${drop(1)}"
        startsWith("8-") -> "Eight${drop(1)}"
        startsWith("9-") -> "Nine${drop(1)}"
        else -> this.capitalized()
    }.replace(regex) {
        it.groups[1]!!.value.capitalized()
    }.also { println("$this -> $it") }
}

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
${
        content.joinToString(separator = "\n") {
            "        ${it.toCompose()}"
        }
    }
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
