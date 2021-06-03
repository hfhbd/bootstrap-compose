package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Collapse(title: String, id: String, color: Color = Color.Primary, content: @Composable () -> Unit) {
    Button(attrs = {
        classes("btn", "btn-$color")
        attr("data-bs-toggle", "collapse")
        attr("data-bs-target", "#a$id")
        attr("aria-expanded", "false")
        attr("aria-controls", id)
    }, type = ButtonType.Button, title = title) {}
    Div({
        classes("collapse")
        id("a$id")
    }) {
        content()
    }
}