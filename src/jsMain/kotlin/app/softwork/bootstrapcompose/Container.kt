package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Container(
    fluid: Boolean = false,
    type: Breakpoint? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    Div(attrs = {
        classes("container")
        if (type != null) {
            classes("container-$type")
        }
        if (fluid) {
            classes("container-fluid")
        }
        attrs?.invoke(this)
    }) {
        content()
    }
}
