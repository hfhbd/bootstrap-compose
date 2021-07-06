package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Container(
    fluid: Boolean = false,
    type: Breakpoint? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>?
) {
    Div(attrs = {
        when {
            fluid -> {
                classes("container-fluid")
            }
            type != null -> {
                classes("container-$type")
            }
            else -> {
                classes("container")
            }
        }

        attrs?.invoke(this)
    }, content)
}
