package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
@NonRestartableComposable
public fun FormFloating(
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    }
    Div(attrs = {
        classes("form-floating")
        if (classes != null) {
            classes(classes = classes)
        }
        if (attrs != null) {
            attrs()
        }
    }, content)
}
