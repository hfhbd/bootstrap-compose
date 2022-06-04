package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Card(
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    header: ContentBuilder<HTMLDivElement>? = null,
    footer: ContentBuilder<HTMLDivElement>? = null,
    body: ContentBuilder<HTMLDivElement>
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div({
        classes("card")
        classes(*classes)
        attrs?.invoke(this)
    }) {
        header?.let {
            Div({ classes("card-header") }, header)
        }
        Div({ classes("card-body") }, body)
        footer?.let {
            Div({ classes("card-footer") }, footer)
        }
    }
}
