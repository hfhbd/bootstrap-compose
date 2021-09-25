package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun Card(
    header: String,
    color: Color = Color.Dark,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    footer: ContentBuilder<HTMLDivElement>? = null,
    body: ContentBuilder<HTMLDivElement>
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div({
        classes("card", "border-$color", "mb-3")
        classes(*classes)
        attrs?.invoke(this)
    }) {
        Div({ classes("card-header") }) {
            Text(header)
        }
        Div({ classes("card-body") }, body)
        footer?.let{
            Div({ classes("card-footer") }, footer)
        }
    }
}
