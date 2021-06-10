package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Collapse(
    title: String, id: String = UUID().toString(), color: Color = Color.Primary,
    buttonAttrs: AttrsBuilder<Tag.Button>.() -> Unit = { },
    contentAttrs: AttrsBuilder<Tag.Div>.() -> Unit = { },
    content: @Composable () -> Unit
) {
    Button(attrs = {
        classes("btn", "btn-$color")
        attr("data-bs-toggle", "collapse")
        attr("data-bs-target", "#a$id")
        attr("aria-expanded", "false")
        attr("aria-controls", id)
        buttonAttrs()
    }, type = ButtonType.Button, title = title) {}
    Div({
        classes("collapse")
        id("a$id")
        contentAttrs()
    }) {
        content()
    }
}