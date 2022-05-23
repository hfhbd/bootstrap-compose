package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.selectors.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Alert(
    color: Color,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()
    Div({
        classes("alert", "alert-$color", "alert-dismissible")
        classes(*classes)
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
    } ?: arrayOf()
    A(href, {
        classes("alert-link")
        classes(*classes)
        attrs?.invoke(this)
    }, content)
}

@Composable
public fun CloseButton(onClose: () -> Unit) {
    Button({
        type(ButtonType.Button)
        classes("btn-close")
        onClick {
            onClose()
        }
    })
}
