package app.softwork.bootstrapcompose

import org.jetbrains.compose.web.attributes.*
import org.w3c.dom.*

@DslMarker
public annotation class StylingMarker

public fun <T : Element> AttrsScope<T>.Styling(styling: Styling.() -> Unit) {
    Styling().apply {
        styling()
    }.apply(this)
}

/**
 * Primary entrypoint for the Styling DSL. Component authors may create a subclass to add additional styling
 * capabilities to their component, including the dynamic generation of classes by using Compose-web style sheet
 * builders.
 */
@StylingMarker
public open class Styling {
    private val generators: MutableList<() -> List<String>> = mutableListOf()
    public val Margins: SpacingSpecs = SpacingSpecs("m")
    public val Padding: SpacingSpecs = SpacingSpecs("p")
    public val Borders: BorderSpec = BorderSpec()
    public val Background: Background = Background()
    public val Text: Text = Text()
    public val Layout: Layout = Layout()

    /**
     * This function will generate a list of css class names that should be applied to a component in order
     * to implement the styling features specified by the dsl. Subclasses should invoke this method and add
     * any additional class names to the returned list.
     */
    public open fun<T : Element> apply(target: AttrsScope<T>) {
        for (gen in generators) {
            target.classes(gen())
        }

        target.classes(Margins.generateClassStrings())
        target.classes(Padding.generateClassStrings())
        target.classes(Borders.generateClassStrings())
        target.classes(Background.generateClassStrings())
        target.classes(Text.generateClassStrings())
        target.classes(Layout.generateClassStrings())
    }

    /**
     * Register a styling generator.
     */
    public fun registerGenerator(block: () -> List<String>) {
        generators += block
    }
}

@StylingMarker
public class SpacingSpecs(private val property: String) {
    public operator fun invoke(f: SpacingSpecs.() -> Unit) {
        this.f()
    }

    public enum class Sides(private val value: String) {
        All(""),
        Top("t"),
        Bottom("b"),
        Start("s"),
        End("e"),
        Vertical("y"),
        Horizontal("x");

        override fun toString(): String {
            return value
        }
    }

    public enum class SpacingSize(private val value: String) {
        None("0"),
        ExtraSmall("1"),
        Small("2"),
        Medium("3"),
        Large("4"),
        ExtraLarge("5"),
        Auto("auto");

        override fun toString(): String {
            return value
        }
    }

    public class SideSpec(private val sides: List<Sides>) {
        public var size: SpacingSize = SpacingSize.Small
        public var breakpoint: Breakpoint? = null

        internal fun generateClassStrings(property: String): List<String> {
            return sides.map { side ->
                "$property$side-" + (breakpoint?.let { "$it-" } ?: "") + "$size"
            }
        }
    }

    private val _specs: MutableList<SideSpec> = mutableListOf()

    public operator fun Sides.plus(side: Sides): List<Sides> {
        return listOf(this, side)
    }

    public operator fun Sides.invoke(spec: SideSpec.() -> Unit) {
        _specs += SideSpec(listOf(this)).apply(spec)
    }

    public operator fun List<Sides>.invoke(spec: SideSpec.() -> Unit) {
        _specs += SideSpec(this).apply(spec)
    }

    internal fun generateClassStrings(): List<String> {
        return _specs.flatMap { spec ->
            spec.generateClassStrings(property)
        }
    }

    public val All: Sides = Sides.All
    public val Top: Sides = Sides.Top
    public val Bottom: Sides = Sides.Bottom
    public val Start: Sides = Sides.Start
    public val End: Sides = Sides.End
    public val Vertical: Sides = Sides.Vertical
    public val Horizontal: Sides = Sides.Horizontal
}

@StylingMarker
public class BorderSpec {
    public operator fun invoke(f: BorderSpec.() -> Unit) {
        this.f()
    }

    public enum class Sides(private val value: String) {
        All("border"),
        Top("border-top"),
        End("border-end"),
        Bottom("border-bottom"),
        Start("border-start");

        override fun toString(): String {
            return value
        }
    }

    public enum class BorderWidth(private val value: String) {
        Thinner("border-1"),
        Thin("border-2"),
        Medium("border-3"),
        Thick("border-4"),
        Thicker("border-5");

        override fun toString(): String {
            return value
        }
    }

    public val All: Sides = Sides.All
    public val Top: Sides = Sides.Top
    public val End: Sides = Sides.End
    public val Bottom: Sides = Sides.Bottom
    public val Start: Sides = Sides.Start

    public operator fun Sides.plus(side: Sides): List<Sides> {
        return listOf(this, side)
    }

    public class SideSpec(private val sides: List<Sides>) {
        public var width: BorderWidth? = null
        public var color: Color? = null

        internal fun generateClassString(): List<String> {
            val classList: MutableList<String> = mutableListOf()
            sides.map {
                classList += it.toString()
            }

            return classList
        }
    }

    public enum class BorderRadius(private val value: String) {
        All("rounded"),
        Top("rounded-top"),
        End("rounded-end"),
        Bottom("rounded-bottom"),
        Start("rounded-start"),
        Circle("rounded-circle"),
        Pill("rounded-pill");

        override fun toString(): String {
            return value
        }
    }

    public enum class RadiusSize(private val value: String) {
        Small("rounded-1"),
        Medium("rounded-2"),
        Large("rounded-3");

        override fun toString(): String {
            return value
        }
    }

    private var sideSpecs: SideSpec? = null

    public operator fun Sides.invoke(spec: SideSpec.() -> Unit) {
        sideSpecs = SideSpec(listOf(this)).apply(spec)
    }

    public operator fun List<Sides>.invoke(spec: SideSpec.() -> Unit) {
        sideSpecs = SideSpec(this).apply(spec)
    }

    private var radiusType: BorderRadius? = null
    private var radiusSize: RadiusSize? = null

    public fun radius(type: BorderRadius, size: RadiusSize? = null) {
        radiusType = type
        radiusSize = size
    }

    internal fun generateClassStrings(): List<String> {
        val classList: MutableList<String> = mutableListOf()

        sideSpecs?.let { sideSpecs ->
            classList += sideSpecs.generateClassString()

            sideSpecs.color?.let { color ->
                classList += "border-$color"
            }

            sideSpecs.width?.let { width ->
                classList += width.toString()
            }
        }

        classList += listOfNotNull(radiusType, radiusSize).map {
            it.toString()
        }

        return classList
    }
}

@StylingMarker
public class Background {
    public var color: Color? = null
    public var gradient: Boolean = false

    public operator fun invoke(f: Background.() -> Unit) {
        this.f()
    }

    internal fun generateClassStrings(): List<String> {
        val classes: MutableList<String> = mutableListOf()

        color?.let { color ->
            classes += color.background()
            if (gradient) {
                classes += "bg-gradient"
            }
        }

        return classes
    }
}

@StylingMarker
public class Text {
    public var color: Color? = null
    private val alignments: MutableList<AlignSpec> = mutableListOf()
    public var wrap: Wraps? = null
    public var wordBreak: Boolean = false
    public var transform: Transform? = null
    public var size: Int? = null
    public var weight: Weight? = null
    public var style: Style? = null
    public var lineHeight: LineHeight? = null
    public var monospace: Boolean = false
    public var reset: Boolean = false
    public var muted: Boolean = false
    public var decoration: Decoration? = null

    public operator fun invoke(f: Text.() -> Unit) {
        this.f()
    }

    public enum class Alignment(private val value: String) {
        Start("start"),
        Center("center"),
        End("end");

        override fun toString(): String {
            return value
        }
    }

    public enum class Wraps(private val value: String) {
        Wrap("text-wrap"),
        NoWrap("text-nowrap"),
        Truncate("text-truncate");

        override fun toString(): String {
            return value
        }
    }

    public enum class Transform(private val value: String) {
        Lowercase("text-lowercase"),
        Uppercase("text-uppercase"),
        Capitalized("text-capitalize");

        override fun toString(): String {
            return value
        }
    }

    public enum class Weight(private val value: String) {
        Bold("fw-bold"),
        Bolder("fw-bolder"),
        Normal("fw-normal"),
        Light("fw-light"),
        Lighter("fw-lighter");

        override fun toString(): String {
            return value
        }
    }

    public enum class Style(private val value: String) {
        Italic("fst-italic"),
        Normal("fst-normal");

        override fun toString(): String {
            return value
        }
    }

    public enum class LineHeight(private val value: String) {
        Smallest("lh-1"),
        Small("lh-sm"),
        Base("lh-base"),
        Large("lh-large");

        override fun toString(): String {
            return value
        }
    }

    public enum class Decoration(private val value: String) {
        Underline("text-decoration-underline"),
        LineThrough("text-decoration-line-through"),
        None("text-decoration-none");

        override fun toString(): String {
            return value
        }
    }

    public data class AlignSpec(private val alignment: Alignment, private var breakpoint: Breakpoint? = null) {
        internal fun className(): String {
            return breakpoint?.let {
                "text-$it-$alignment"
            } ?: "text-$alignment"
        }
    }

    public fun align(alignment: Alignment, breakpoint: Breakpoint? = null) {
        alignments += AlignSpec(alignment, breakpoint)
    }

    internal fun generateClassStrings(): List<String> {
        val classes: MutableList<String> = mutableListOf()

        color?.let { color ->
            classes += color.text()
        }

        alignments.forEach { spec ->
            classes += spec.className()
        }

        if (wordBreak) {
            classes += "text-break"
        }

        size?.let {
            classes += "fs-${it.coerceIn(1, 6)}"
        }

        if (monospace) {
            classes += "font-monospace"
        }

        if (reset) {
            classes += "text-reset"
        }

        if (muted) {
            classes += "text-muted"
        }

        decoration?.let {
            classes += it.toString()
        }

        classes += listOfNotNull(wrap, transform, weight, style, lineHeight).map {
            it.toString()
        }

        return classes
    }
}

@StylingMarker
public class Layout {
    private val displaySpecs: MutableList<DisplaySpec> = mutableListOf()
    private val floatSpecs: MutableList<FloatSpec> = mutableListOf()
    public var overflow: Overflow? = null
    public var width: Width? = null
    public var height: Height? = null
    public var verticalAlignment: VerticalAlignment? = null
    public var horizontalAlignment: HorizontalAlignment? = null
    public var visible: Boolean? = null

    public operator fun invoke(f: Layout.() -> Unit) {
        this.f()
    }

    public enum class Display(private val value: String) {
        None("none"),
        Inline("inline"),
        InlineBlock("inline-block"),
        Block("block"),
        Grid("grid"),
        Flex("flex"),
        InlineFlex("inline-flex");

        override fun toString(): String {
            return value
        }
    }

    public enum class Float(private val value: String) {
        Start("start"),
        End("end"),
        None("none");

        override fun toString(): String {
            return value
        }
    }

    public enum class Overflow(private val value: String) {
        Auto("overflow-auto"),
        Hidden("overflow-hidden"),
        Visible("overflow-visible"),
        Scroll("overflow-scroll");

        override fun toString(): String {
            return value
        }
    }

    public enum class Width(private val value: String) {
        Quarter("w-25"),
        Half("w-50"),
        ThreeQuarters("w-75"),
        Full("w-100"),
        Auto("w-auto"),
        View("vw-100");

        override fun toString(): String {
            return value
        }
    }

    public enum class Height(private val value: String) {
        Quarter("h-25"),
        Half("h-50"),
        ThreeQuarters("h-75"),
        Full("h-100"),
        Auto("h-auto"),
        View("vh-100");

        override fun toString(): String {
            return value
        }
    }

    public enum class HorizontalAlignment(private val classInfix: String) {
        Start("start"),
        Center("center"),
        End("end"),
        Around("around"),
        Between("between"),
        Evenly("evenly");

        override fun toString(): String = "justify-content-$classInfix"
    }

    public enum class VerticalAlignment(private val value: String) {
        Baseline("align-baseline"),
        Top("align-top"),
        Middle("align-middle"),
        Bottom("align-bottom"),
        TextTop("align-text-top"),
        TextBottom("align-text-bottom");

        override fun toString(): String = value
    }

    private data class DisplaySpec(val display: Display, val breakpoint: Breakpoint?)
    private data class FloatSpec(val float: Float, val breakpoint: Breakpoint?)

    public fun display(display: Display, breakpoint: Breakpoint? = null) {
        displaySpecs += DisplaySpec(display, breakpoint)
    }

    public fun float(float: Float, breakpoint: Breakpoint? = null) {
        floatSpecs += FloatSpec(float, breakpoint)
    }

    internal fun generateClassStrings(): List<String> {
        val classes: MutableList<String> = mutableListOf()

        displaySpecs.forEach { displaySpec ->
            classes += "d-" + (displaySpec.breakpoint?.let { "$it-" } ?: "") + "${displaySpec.display}"
        }

        floatSpecs.forEach { floatSpec ->
            classes += "float-" + (floatSpec.breakpoint?.let { "$it-" } ?: "") + "${floatSpec.float}"
        }

        visible?.let { visible ->
            classes += if (visible) {
                "visible"
            } else {
                "invisible"
            }
        }

        classes += listOfNotNull(overflow, width, height, verticalAlignment, horizontalAlignment).map {
            it.toString()
        }

        return classes
    }
}
