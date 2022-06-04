package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Collapse(
    title: String,
    id: String = UUID().toString(),
    color: Color = Color.Primary,
    buttonAttrs: AttrBuilderContext<HTMLButtonElement>? = null,
    contentAttrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    Style
    needsJS
    Button(attrs = {
        classes("btn", "btn-$color")
        attr("data-bs-toggle", "collapse")
        attr("data-bs-target", "#_$id")
        attr("aria-expanded", "false")
        attr("aria-controls", "_$id")
        buttonAttrs?.invoke(this)
    }, type = ButtonType.Button, title = title) { }
    Div({
        classes("collapse")
        id("_$id")
        contentAttrs?.invoke(this)
    }, content)
}
