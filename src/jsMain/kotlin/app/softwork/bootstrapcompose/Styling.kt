@file:Suppress("unused")

package app.softwork.bootstrapcompose

public class Styling {
    public val Margins: SpacingSpecs = SpacingSpecs("m")
    public val Padding: SpacingSpecs = SpacingSpecs("p")
    public val Borders: BorderSpec = BorderSpec()
    public val Background: Background = Background()
    public val Text: Text = Text()

    internal fun generateClassStrings(): Array<String> {
        return Margins.generateClassStrings() +
                Padding.generateClassStrings() +
                Borders.generateClassStrings() +
                Background.generateClassStrings() +
                Text.generateClassStrings()
    }
}

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
        Vertical("x"),
        Horizontal("y");

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

    public class SideSpec(internal val sides: List<Sides>) {
        public var size: SpacingSize = SpacingSize.Small
        public var breakpoint: Breakpoint? = null
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

    internal fun generateClassStrings(): Array<String> {
        return _specs.flatMap { spec ->
            spec.sides.map { side ->
                "$property$side-" + (spec.breakpoint?.let { "$it-" } ?: "") + "${spec.size}"
            }
        }.toTypedArray()
    }

    public val All: Sides = Sides.All
    public val Top: Sides = Sides.Top
    public val Bottom: Sides = Sides.Bottom
    public val Start: Sides = Sides.Start
    public val End: Sides = Sides.End
    public val Vertical: Sides = Sides.Vertical
    public val Horizontal: Sides = Sides.Horizontal
}


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
        Large("rounded-3")
    }

    public val All: Sides = Sides.All
    public val Top: Sides = Sides.Top
    public val End: Sides = Sides.End
    public val Bottom: Sides = Sides.Bottom
    public val Start: Sides = Sides.Start

    public operator fun Sides.plus(side: Sides): List<Sides> {
        return listOf(this, side)
    }

    public class SideSpec(internal val sides: List<Sides>) {
        public var width: BorderWidth? = null
        public var color: Color? = null
        internal var radiusSpec: RadiusSpec? = null

        public val Small: RadiusSize = RadiusSize.Small
        public val Medium: RadiusSize = RadiusSize.Medium
        public val Large: RadiusSize = RadiusSize.Large

        public fun radius(type: BorderRadius, size: RadiusSize) {
            radiusSpec = RadiusSpec(type, size)
        }

    }

    private var sideSpecs: SideSpec? = null

    public operator fun Sides.invoke(spec: SideSpec.() -> Unit) {
        sideSpecs = SideSpec(listOf(this)).apply(spec)
    }

    public operator fun List<Sides>.invoke(spec: SideSpec.() -> Unit) {
        sideSpecs = SideSpec(this).apply(spec)
    }

    public data class RadiusSpec(
        public var type: BorderRadius = BorderRadius.All,
        public var size: RadiusSize = RadiusSize.Medium
    )


    internal fun generateClassStrings(): Array<String> {
        val classList: MutableList<String> = mutableListOf()

        sideSpecs?.let { sideSpecs ->
            classList += sideSpecs.sides.map { it.toString() }

            sideSpecs.color?.let { color ->
                classList += "border-$color"
            }

            sideSpecs.width?.let { width ->
                classList += width.toString()
            }

            sideSpecs.radiusSpec?.let { (type, size) ->
                classList += type.toString()
                classList += size.toString()
            }
        }

        return classList.toTypedArray()
    }

}



public class Background {
    public var color: Color? = null
    public var gradient: Boolean = false

    public operator fun invoke(f: Background.() -> Unit) {
        this.f()
    }

    internal fun generateClassStrings(): Array<String> {
        val classes: MutableList<String> = mutableListOf()

        color?.let { color ->
            classes += color.background()
            if (gradient) {
                classes += "bg-gradient"
            }
        }

        return classes.toTypedArray()
    }
}

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
        NoWrap("text-nowrap");

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

    public class AlignSpec(internal val alignment: Alignment, public var breakpoint: Breakpoint? = null)

    public fun align(alignment: Alignment, breakpoint: Breakpoint? = null) {
        alignments += AlignSpec(alignment, breakpoint)
    }

    internal fun generateClassStrings(): Array<String> {
        val classes: MutableList<String> = mutableListOf()

        color?.let { color ->
            classes += color.text()
        }

        alignments.forEach { spec ->
            classes += spec.breakpoint?.let { bp ->
                "text-$bp-${spec.alignment}"
            } ?: "text-${spec.alignment}"
        }

        wrap?.let {
            classes += it.toString()
        }

        if (wordBreak) {
            classes += "text-break"
        }

        transform?.let {
            classes += it.toString()
        }

        size?.let {
            classes += "fs-${it.coerceIn(1, 6)}"
        }

        weight?.let {
            classes += it.toString()
        }

        style?.let {
            classes += it.toString()
        }

        lineHeight?.let {
            classes += it.toString()
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

        return classes.toTypedArray()
    }
}