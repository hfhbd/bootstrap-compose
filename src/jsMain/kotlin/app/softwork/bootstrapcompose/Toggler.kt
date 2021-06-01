package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Toggler(target: String, controls: String) {
    Button(attrs = {
        classes("navbar-toggler")
        attr("data-toggle", "collapse")
        attr("data-target", "#$target")
        attr("aria-controls", controls)
        attr("aria-expanded", "false")
        attr("aria-label", "Toggle navigation")
    }) {
        Span(attrs = { classes("navbar-toggler-icon") }) { }
    }
}
