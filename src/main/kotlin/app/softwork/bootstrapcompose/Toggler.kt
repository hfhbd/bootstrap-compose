package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Toggler(
    target: String,
    controls: String,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Button(attrs = {
        classes("navbar-toggler")
        classes(*classes)
        attr("data-bs-toggle", "collapse")
        attr("data-bs-target", "#$target")
        attr("aria-controls", controls)
        attr("aria-expanded", "false")
        attr("aria-label", "Toggle navigation")
        attrs?.invoke(this)
    }) {
        Span(attrs = { classes("navbar-toggler-icon") })
    }
}
