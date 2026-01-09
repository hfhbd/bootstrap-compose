package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun ButtonGroup(
    styling: (Styling.() -> Unit)? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    Div({
        classes("btn-group")
        if (classes != null) {
            classes(classes = classes)
        }
    }, content)
}
