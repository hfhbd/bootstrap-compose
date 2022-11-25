package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Badge(
    backgroundColor: Color,
    textColor: Color? = null,
    round: Boolean = false,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>
) {
    Style

    Span(attrs = {
        classes("badge", backgroundColor.background())
        if (textColor != null) {
            classes(textColor.text())
        }
        if (round) {
            classes("rounded-pill")
        }
        if (styling != null) {
            Styling(styling)
        }
        attrs?.invoke(this)
    }, content)
}
