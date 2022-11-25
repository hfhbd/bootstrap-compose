package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Alert(
    color: Color,
    dismissible: Boolean = true,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    Style
    Div({
        classes("alert", "alert-$color")
        if (dismissible) {
            needsJS
            classes("alert-dismissible")
        }
        if (styling != null) {
            Styling(styling)
        }
        attr("role", "alert")
        attrs?.invoke(this)
    }) {
        content()
    }
}

@Composable
public fun Link(
    href: String?,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    content: ContentBuilder<HTMLAnchorElement>
) {
    Style
    A(href, {
        classes("alert-link")
        if (styling != null) {
            Styling(styling)
        }
        attrs?.invoke(this)
    }, content)
}
