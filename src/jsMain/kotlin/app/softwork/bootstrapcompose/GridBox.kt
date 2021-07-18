@file:Suppress("unused")

package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.selectors.CSSSelector
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.jetbrains.compose.web.dom.Style
import org.w3c.dom.HTMLDivElement
import kotlin.reflect.KProperty

@Composable
public fun GridBox(
    styling: GridStyle.() -> Unit,
    content: (@Composable GridContentBuilder.() -> Unit)? = null
) {
    val style = GridStyle().apply(styling)
    val classes = style.generate()

    Div(attrs = {
        classes("d-grid")
        classes(*classes)
    }) {
        val scope = remember { GridContentBuilder(this) }
        content?.invoke(scope)
    }
}

public class GridContentBuilder(scope: ElementScope<HTMLDivElement>) : ElementScope<HTMLDivElement> by scope {
    public fun Styling.GridItem(spec: GridItemLayout.() -> Unit) {
        val s = GridItemLayout().apply(spec)

        this.registerGenerator {
            s.generate()
        }
    }
}

public class GridStyle : Styling() {
    public val GridLayout: GridLayout = GridLayout()

    @Composable
    override fun generate(): Array<String> {
        return super.generate() + GridLayout.generate()
    }
}

public class GridLayout {
    private var columns: MutableList<GridTemplateTrack> = mutableListOf()
    private var rows: MutableList<GridTemplateTrack> = mutableListOf()
    private var areas: MutableList<GridArea> = mutableListOf()
    public var gap: CSSLengthOrPercentageValue? = null
    public var justifyContent: Placement? = null
    public var alignContent: Placement? = null

    public operator fun invoke(f: GridLayout.() -> Unit) {
        this.f()
    }

    public enum class Placement(private val value: String) {
        Start("start"),
        End("end"),
        Center("center"),
        Stretch("stretch"),
        SpaceAround("space-around"),
        SpaceBetween("space-between"),
        SpaceEvenly("space-evenly");

        public override fun toString(): String {
            return value
        }
    }

    public fun columns(breakpoint: Breakpoint? = null, spec: GridTemplateTrack.() -> Unit) {
        columns += GridTemplateTrack(GridTemplateTrack.ColumnOrRow.Column, breakpoint).apply(spec)
    }

    public fun rows(breakpoint: Breakpoint? = null, spec: GridTemplateTrack.() -> Unit) {
        rows += GridTemplateTrack(GridTemplateTrack.ColumnOrRow.Row, breakpoint).apply(spec)
    }

    public fun areas(breakpoint: Breakpoint? = null, spec: GridArea.() -> Unit) {
        areas += GridArea(breakpoint).apply(spec)
    }

    @Composable
    internal fun generate(): List<String> {
        val classes: MutableList<String> = mutableListOf()

        Style {
            val classname = remember { "_${UUID()}" }

            CSSSelector.CSSClass(classname) style {
                gap?.let {
                    property("gap", gap.toString())
                }
                justifyContent?.let {
                    property("justify-content", it.toString())
                }
                alignContent?.let {
                    property("align-content", it.toString())
                }
            }

            columns.forEach {
                it.apply { classes += generateStyle() }
            }

            rows.forEach {
                it.apply { classes += generateStyle() }
            }

            areas.forEach {
                it.apply { classes += generateStyle() }
            }
            classes += classname
        }

        return classes
    }
}

public class GridTemplateTrack internal constructor(
    private val type: ColumnOrRow,
    private val breakpoint: Breakpoint?
) {
    private val items: MutableList<GridTemplateItem> = mutableListOf()
    public var gap: CSSLengthOrPercentageValue? = null
    public var alignment: Alignment? = null
    private var auto = false

    internal enum class ColumnOrRow(val gap: String, val align: String) {
        Column("column-gap", "align-items"),
        Row("row-gap", "justify-items"),
    }

    public enum class Alignment(private val value: String) {
        Start("start"),
        End("end"),
        Center("center"),
        Stretch("stretch");

        public override fun toString(): String {
            return value
        }
    }

    public fun none() {
        if (items.isNotEmpty()) {
            throw IllegalStateException("Cannot add 'none' to already specified grid-template-columns")
        }
        items.add(GridTemplateNone)
    }

    /**
     * This function is used to implement grid-template-columns/rows when the
     * track list follows the <track-list> syntax.
     * See https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-columns
     */
    public fun trackList(block: TrackList.() -> Unit) {
        if (items.isNotEmpty()) {
            throw IllegalStateException("Cannot add more tracks")
        }
        items += TrackList().apply(block).items
    }

    /**
     * This function is used to implement grid-template-columns/rows when the
     * track list follows the <auto-track-list> syntax.
     * See https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-columns
     */
    public fun autoTrackList(block: AutoTrackList.() -> Unit) {
        if (items.isNotEmpty()) {
            throw IllegalStateException("Cannot add more tracks")
        }
        items += AutoTrackList().apply(block).items
    }

    /**
     * This function is used to implement grid-auto-rows and grid-auto-columns properties.
     * See https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-columns
     */
    public fun auto(block: AutoList.() -> Unit) {
        if (items.isNotEmpty()) {
            throw IllegalStateException("Cannot add more tracks")
        }
        items += AutoList().apply(block).items
        auto = true
    }

    @Composable
    internal fun StyleSheetBuilder.generateStyle(): String {
        val classname = remember { "_${UUID()}" }

        withBreakpoint(breakpoint) {
            CSSSelector.CSSClass(classname) style {
                val propValue = items.joinToString(separator = " ")
                val propName = when {
                    auto && type == ColumnOrRow.Column -> "grid-auto-columns"
                    auto && type == ColumnOrRow.Row -> "grid-auto-rows"
                    !auto && type == ColumnOrRow.Column -> "grid-template-columns"
                    !auto && type == ColumnOrRow.Row -> "grid-template-rows"
                    else -> "" //not possible
                }

                property(propName, propValue)

                gap?.let {
                    property(type.gap, it)
                }

                alignment?.let {
                    property(type.align, it.toString())
                }
            }
        }

        return classname
    }
}

public open class TrackListUnits {
    public val Number.ch: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.ch)
    public val Number.em: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.em)
    public val Number.ex: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.ex)
    public val Number.rem: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.rem)
    public val Number.vh: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.vh)
    public val Number.vw: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.vw)
    public val Number.vmin: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.vmin)
    public val Number.vmax: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.vmax)
    public val Number.px: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.px)
    public val Number.cm: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.cm)
    public val Number.mm: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.mm)
    public val Number.pc: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.pc)
    public val Number.pt: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.pt)
    public val Number.percent: LengthPercentage by LengthPercentageUnitPropertyDelegate(CSSUnit.percent)

    public val Number.fr: Flex
        get(): Flex {
            return Flex(CSSUnitValueTyped(this.toFloat(), CSSUnit.fr))
        }

    private class LengthPercentageUnitPropertyDelegate<T : CSSUnitLengthOrPercentage>(val unit: T) {
        operator fun getValue(thisRef: Number, property: KProperty<*>): LengthPercentage {
            return LengthPercentage(CSSUnitValueTyped(thisRef.toFloat(), unit))
        }
    }
}


public class TrackList internal constructor() : TrackListUnits() {
    internal val items: MutableList<TrackListItem> = mutableListOf()

    public fun track(names: List<String>, size: TrackSizeItem) {
        if (names.isNotEmpty()) {
            items += LineNames(*names.toTypedArray())
        }
        items += size
    }

    public fun track(size: TrackSizeItem) {
        items += size
    }

    public fun track(names: List<String>, repeat: TrackRepeat) {
        if (names.isNotEmpty()) {
            items += LineNames(*names.toTypedArray())
        }
        items += repeat
    }

    public fun track(repeat: TrackRepeat) {
        items += repeat
    }

    public class TrackRepeat(private val count: Int, private vararg val items: TrackRepeatItem) : TrackListItem {
        override fun toString(): String {
            return "repeat($count, ${items.joinToString(separator = " ")})"
        }
    }

    public fun lineNames(vararg names: String) {
        items += LineNames(*names)
    }
}

public class AutoList internal constructor() : TrackListUnits() {
    internal val items: MutableList<TrackSizeItem> = mutableListOf()

    public fun track(size: TrackSizeItem) {
        items += size
    }
}

public data class MinMax(private val min: InflexibleBreadthItem, private val max: TrackBreadthItem) :
    TrackSizeItem {

    override fun toString(): String {
        return "minmax($min,$max)"
    }
}

public class AutoTrackList internal constructor() : TrackListUnits() {
    internal val items: MutableList<AutoTrackListItem> = mutableListOf()
    private var autoRepeat = false

    public fun track(names: List<String>, size: FixedSizeItem) {
        if (names.isNotEmpty()) {
            items += LineNames(*names.toTypedArray())
        }
        items += size
    }

    public fun track(size: FixedSizeItem) {
        items += size
    }

    public fun track(names: List<String>, repeat: FixedRepeat) {
        if (names.isNotEmpty()) {
            items += LineNames(*names.toTypedArray())
        }
        items += repeat
    }

    public fun track(repeat: FixedRepeat) {
        items += repeat
    }

    public fun lineNames(vararg names: String) {
        items += LineNames(*names)
    }

    public fun autoRepeat(type: RepeatType, vararg repeatItems: FixedRepeatItem) {
        if (type == RepeatType.AutoFill || type == RepeatType.AutoFit) {
            if (autoRepeat) {
                //auto-repeat can only be specified once per grid-template-columns
                throw IllegalStateException("auto-repeat already specified")
            }
            autoRepeat = true
        }

        items += AutoRepeat(type, *repeatItems)
    }

    public class MinMax private constructor(private val min: Any, private val max: Any) : FixedSizeItem {
        public constructor(minimum: FixedBreadthItem, maximum: TrackBreadthItem) : this(min = minimum, max = maximum)
        public constructor(minimum: InflexibleBreadthItem, maximum: FixedBreadthItem) : this(
            min = minimum,
            max = maximum
        )

        override fun toString(): String {
            return "minmax($min,$max)"
        }
    }

    public class AutoRepeat(private val type: RepeatType, private vararg val items: FixedRepeatItem) :
        AutoTrackListItem {
        override fun toString(): String {
            return "repeat($type, ${items.joinToString(separator = " ")})"
        }
    }

    public enum class RepeatType(private val value: String) {
        AutoFill("auto-fill"),
        AutoFit("auto-fit");

        override fun toString(): String {
            return value
        }
    }

    public class FixedRepeat(private val count: Int, private vararg val items: FixedRepeatItem) : AutoTrackListItem {
        override fun toString(): String {
            return "repeat($count, ${items.joinToString(separator = " ")})"
        }
    }
}

/**
 * Marker interface for items that can be included in a Grid-Template-Columns or
 * Grid-Template-Rows propery.
 */
public interface GridTemplateItem

/**
 * Marker interface for items that can be included in a <track-list>.
 * see https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-columns
 */
public interface TrackListItem : GridTemplateItem

/**
 * Marker interface for items that can be included in an <auto-track-list>.
 * see https://developer.mozilla.org/en-US/docs/Web/CSS/grid-template-columns
 */
public interface AutoTrackListItem : GridTemplateItem


// Marker interfaces for items that can be used as a <track-size>
public interface TrackSizeItem : TrackListItem, TrackRepeatItem
public interface TrackBreadthItem : TrackSizeItem
public interface TrackRepeatItem

// Marker interfaces for items that can be used as a <fixed-size>
public interface FixedSizeItem : AutoTrackListItem, FixedRepeatItem
public interface FixedBreadthItem : FixedSizeItem
public interface FixedRepeatItem

public interface InflexibleBreadthItem

private object GridTemplateNone : GridTemplateItem {
    override fun toString(): String {
        return "none"
    }
}

public class LineNames(private vararg val names: String) : TrackListItem,
    AutoTrackListItem, TrackRepeatItem, FixedRepeatItem {
    override fun toString(): String {
        return names.joinToString(prefix = "[", postfix = "]", separator = " ")
    }
}

public data class FitContent(val value: LengthPercentage) : TrackSizeItem {
    override fun toString(): String {
        return "fit-content($value)"
    }
}

public class LengthPercentage(private val v: CSSLengthOrPercentageValue) : InflexibleBreadthItem,
    TrackBreadthItem, FixedBreadthItem {
    override fun toString(): String {
        return v.toString()
    }
}

public object MinContent : InflexibleBreadthItem, TrackBreadthItem {
    override fun toString(): String {
        return "min-content"
    }
}

public object MaxContent : InflexibleBreadthItem, TrackBreadthItem {
    override fun toString(): String {
        return "max-content"
    }
}

public object Auto : InflexibleBreadthItem, TrackBreadthItem {
    override fun toString(): String {
        return "auto"
    }
}

public class Flex(private val v: CSSSizeValue<CSSUnit.fr>) : TrackBreadthItem {
    override fun toString(): String {
        return v.toString()
    }
}

public class GridArea internal constructor(private val breakpoint: Breakpoint?) {
    private val rows: MutableList<List<String>> = mutableListOf()

    public fun row(vararg cells: String) {
        rows += listOf(*cells)
    }

    @Composable
    internal fun StyleSheetBuilder.generateStyle(): String {
        val classname = remember { "_${UUID()}" }

        withBreakpoint(breakpoint) {
            CSSSelector.CSSClass(classname) style {
                property("grid-template-areas", rows.joinToString(separator = " ") {
                    it.joinToString(separator = " ", prefix = "\"", postfix = "\"")
                })
            }
        }

        return classname
    }
}


public class GridItemLayout {
    private val areaSpecs: MutableList<GridItemArea> = mutableListOf()
    private val placements: MutableList<PlacementSpec> = mutableListOf()

    public fun area(breakpoint: Breakpoint? = null, name: String) {
        areaSpecs += GridItemArea(breakpoint, name)
    }

    public fun area(name: String) {
        areaSpecs += GridItemArea(null, name)
    }

    public fun area(breakpoint: Breakpoint, name: String) {
        areaSpecs += GridItemArea(breakpoint, name)
    }

    public fun area(spec: GridItemArea.() -> Unit) {
        areaSpecs += GridItemArea().apply(spec)
    }

    public fun area(breakpoint: Breakpoint, spec: GridItemArea.() -> Unit) {
        areaSpecs += GridItemArea(breakpoint).apply(spec)
    }

    public fun placement(spec: PlacementSpec.() -> Unit) {
        placements += PlacementSpec().apply(spec)
    }

    public fun placement(breakpoint: Breakpoint, spec: PlacementSpec.() -> Unit) {
        placements += PlacementSpec(breakpoint).apply(spec)
    }

    @Composable
    internal fun generate(): List<String> {
        val classes: MutableList<String> = mutableListOf()

        Style {
            areaSpecs.forEach {
                it.apply { classes += generateStyle() }
            }
            placements.forEach {
                it.apply { classes += generateStyle() }
            }
        }

        return classes
    }
}

public class GridItemArea(private val breakpoint: Breakpoint? = null, private val name: String? = null) {
    private var column: GridItemAreaSpec? = null
    private var row: GridItemAreaSpec? = null

    public fun row(f: GridItemAreaSpec.() -> Unit) {
        row = GridItemAreaSpec().apply(f)
    }

    public fun row(start: Int, end: Int? = null) {
        row = GridItemAreaSpec().apply {
            start(start)
            end?.let { end(it) }
        }
    }

    public fun row(start: Int, end: GridLine? = null) {
        row = GridItemAreaSpec().apply {
            start(start)
            end(end)
        }
    }

    public fun row(start: GridLine, end: Int? = null) {
        row = GridItemAreaSpec().apply {
            start(start)
            end?.let {
                end(end)
            }
        }
    }

    public fun row(start: GridLine, end: GridLine?) {
        row = GridItemAreaSpec().apply {
            start(start)
            end(end)
        }
    }

    public fun column(f: GridItemAreaSpec.() -> Unit) {
        column = GridItemAreaSpec().apply(f)
    }

    public fun column(start: Int, end: Int?) {
        column = GridItemAreaSpec().apply {
            start(start)
            end?.let {
                end(it)
            }
        }
    }

    public fun column(start: Int, end: GridLine?) {
        column = GridItemAreaSpec().apply {
            start(start)
            end(end)
        }
    }

    public fun column(start: GridLine, end: Int?) {
        column = GridItemAreaSpec().apply {
            start(start)
            end?.let { end(it) }
        }
    }

    public fun column(start: GridLine, end: GridLine) {
        column = GridItemAreaSpec().apply {
            start(start)
            end(end)
        }
    }

    @Composable
    internal fun StyleSheetBuilder.generateStyle(): String {
        val classname = remember { "_${UUID()}" }

        withBreakpoint(breakpoint) {
            CSSSelector.CSSClass(classname) style {
                if (name != null) {
                    property("grid-area", name)
                } else {
                    column?.let {
                        property("grid-column", it.toString())
                    }
                    row?.let {
                        property("grid-row", it.toString())
                    }
                }
            }
        }

        return classname
    }
}

public class GridItemAreaSpec {
    private var startPlacement: GridLine = GridLine.Auto
    private var endPlacement: GridLine? = null

    public fun start(ident: String) {
        startPlacement = GridLine.CustomIdent(ident)
    }

    public fun start(line: Int, ident: String? = null) {
        startPlacement = GridLine.Line(line, ident)
    }

    public fun end(ident: String) {
        endPlacement = GridLine.CustomIdent(ident)
    }

    public fun end(line: Int, ident: String? = null) {
        endPlacement = GridLine.Line(line, ident)
    }

    internal fun start(start: GridLine) {
        startPlacement = start
    }

    internal fun end(end: GridLine?) {
        end?.let {
            endPlacement = it
        }
    }

    public infix fun Int.to(end: Int) {
        startPlacement = GridLine.Line(this)
        endPlacement = GridLine.Line(end)
    }

    public infix fun Int.to(end: GridLine) {
        startPlacement = GridLine.Line(this)
        endPlacement = end
    }

    public infix fun GridLine.to(end: GridLine) {
        startPlacement = this
        endPlacement = end
    }

    public infix fun GridLine.to(end: Int) {
        startPlacement = this
        endPlacement = GridLine.Line(end)
    }

    public override fun toString(): String {
        return startPlacement.toString() + (endPlacement?.let { " / $endPlacement" } ?: "")
    }
}

public sealed class GridLine {
    public object Auto : GridLine() {
        override fun toString(): String {
            return "auto"
        }
    }

    internal data class CustomIdent(private val ident: String) : GridLine() {
        override fun toString(): String {
            return ident
        }
    }

    internal data class Line(private val number: Int, private val ident: String? = null) : GridLine() {
        override fun toString(): String {
            return number.toString() + (ident ?: "")
        }
    }

    public class Span private constructor(private val number: Int?, private val ident: String?) :
        GridLine() {

        public constructor(n: Int) : this(n, null)
        public constructor(id: String) : this(null, id)

        override fun toString(): String {
            return "span " + (number?.toString() ?: ident)
        }
    }
}

public class PlacementSpec(private val breakpoint: Breakpoint? = null) {
    public var block: PlacementType = PlacementType.Auto
    public var inline: PlacementType? = null

    public enum class PlacementType(private val value: String) {
        Auto("auto"),
        Start("start"),
        End("end"),
        Center("center"),
        Stretch("stretch");

        public override fun toString(): String {
            return value
        }
    }

    @Composable
    internal fun StyleSheetBuilder.generateStyle(): String {
        val classname = remember { "_${UUID()}" }

        withBreakpoint(breakpoint) {
            CSSSelector.CSSClass(classname) style {
                property("place-self", block.toString() + (inline?.let { " $it" } ?: ""))
            }
        }

        return classname
    }
}

private fun StyleSheetBuilder.withBreakpoint(
    breakpoint: Breakpoint?,
    block: GenericStyleSheetBuilder<CSSStyleRuleBuilder>.() -> Unit
) {
    val bp = breakpoints[breakpoint]
    if (bp != null) {
        media(minWidth(bp)) {
            block()
        }
    } else {
        block()
    }
}