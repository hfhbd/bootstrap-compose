package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Brand(
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    Style

    Div(attrs = {
        classes("navbar-brand")
        if (styling != null) {
            Styling(styling)
        }
        attrs?.invoke(this)
    }, content)
}
