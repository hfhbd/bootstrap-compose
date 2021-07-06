package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Brand(attrs: AttrBuilderContext<HTMLDivElement>? = null, content: ContentBuilder<HTMLDivElement>) {
    Div(attrs = {
        classes("navbar-brand")
        attrs?.invoke(this)
    }, content)
}
