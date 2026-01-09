package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Alert(
    color: Color,
    dismissible: Boolean = true,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    }
    Div({
        classes("alert", "alert-$color")
        if (dismissible) {
            Alert
            classes("alert-dismissible")
        }
        if (classes != null) {
            classes(classes = classes)
        }
        attr("role", "alert")
        attrs?.invoke(this)
    }) {
        content()
    }
}

@Composable
public fun Link(
    href: String?,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    content: ContentBuilder<HTMLAnchorElement>
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    }
    A(href, {
        classes("alert-link")
        if (classes != null) {
            classes(classes = classes)
        }
        attrs?.invoke(this)
    }, content)
}
