package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Toggler(target: String, controls: String, attrs: AttrsBuilder<HTMLButtonElement>.() -> Unit = { }) {
    Button(attrs = {
        classes("navbar-toggler")
        attr("data-bs-toggle", "collapse")
        attr("data-bs-target", "#$target")
        attr("aria-controls", controls)
        attr("aria-expanded", "false")
        attr("aria-label", "Toggle navigation")
        attrs()
    }) {
        Span(attrs = { classes("navbar-toggler-icon") }) { }
    }
}
