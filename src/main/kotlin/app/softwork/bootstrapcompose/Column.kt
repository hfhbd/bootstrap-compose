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
    Div(attrs = {
        classes("col" + (breakpoint?.let { "-$it" } ?: "") + (size?.let { "-$it" } ?: ""))
        if (styling != null) {
            Styling(styling)
        }
        if (auto) {
            classes("col-auto")
        }
        attrs?.invoke(this)
    }, content)
}
