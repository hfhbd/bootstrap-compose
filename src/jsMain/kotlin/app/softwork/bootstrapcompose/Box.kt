package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
public fun Box(styling: (Styling.() -> Unit)? = null, content: ContentBuilder<HTMLDivElement>? = null) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div(attrs = {
        classes(*classes)
    }, content = content)
}