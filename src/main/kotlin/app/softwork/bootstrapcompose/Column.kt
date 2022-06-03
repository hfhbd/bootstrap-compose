package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Column(
    auto: Boolean = false,
    breakpoint: Breakpoint? = null,
    size: Int? = null,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div(attrs = {
        classes("col" + (breakpoint?.let { "-$it" } ?: "") + (size?.let { "-$it" } ?: ""))
        classes(*classes)
        if (auto) {
            classes("col-auto")
        }
        attrs?.invoke(this)
    }, content)
}
