package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Row(
    styling: (@Composable RowStyling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    val classes = styling?.let {
        RowStyling(it).generate()
    }

    Div(attrs = {
        classes("row")
        if (classes != null) {
            classes(classes = classes)
        }
        attrs?.invoke(this)
    }, content = content)
}

@Composable
public fun RowStyling(styling: @Composable RowStyling.() -> Unit): RowStyling = RowStyling().apply { styling() }

public class RowStyling : Styling() {
    public val Gutters: Gutters = Gutters()

    @Composable
    override fun generate(): List<String> {
        return super.generate() + Gutters.generate()
    }
}

public class Gutters {
    public operator fun invoke(f: Gutters.() -> Unit) {
        this.f()
    }

    @Composable
    internal fun generate(): List<String> {
        val classes: MutableList<String> = mutableListOf()

        _spec?.let {
            classes += it.generateClassStrings()
        }

        return classes
    }

    public enum class GutterSize(private val value: String) {
        None("0"),
        ExtraSmall("1"),
        Small("2"),
        Medium("3"),
        Large("4"),
        ExtraLarge("5");

        override fun toString(): String {
            return value
        }
    }

    public enum class Direction(private val value: String) {
        Horizontal("x"),
        Vertical("y"),
        HorizontalAndVertical("");

        override fun toString(): String {
            return value
        }
    }

    public class GutterSpec(private val direction: Direction) {
        public var size: GutterSize = GutterSize.Small
        public var breakpoint: Breakpoint? = null

        internal fun generateClassStrings(): String {
            return "g$direction-" + (breakpoint?.let { "$it-" } ?: "") + size.toString()
        }
    }

    private var _spec: GutterSpec? = null

    public operator fun Direction.invoke(spec: GutterSpec.() -> Unit) {
        _spec = GutterSpec(this).apply(spec)
    }
}
