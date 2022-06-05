package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Container(
    fluid: Boolean = false,
    type: Breakpoint? = null,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>?
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div(attrs = {
        classes(classes = classes)
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
