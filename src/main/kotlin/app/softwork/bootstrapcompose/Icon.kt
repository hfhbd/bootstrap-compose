package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

/**
 * Bootstrap Icon shortcut when using with already embedded icons.
 * Alternative use `bootstrap-compose-icons`.
 */
@Composable
public fun Icon(
    iconName: String,
    styling: (Styling.() -> Unit)? = null,
    attrsBuilder: AttrBuilderContext<HTMLElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    I({
        classes("bi", "bi-$iconName")
        if (classes != null) {
            classes(classes = classes)
        }
        attrsBuilder?.invoke(this)
    })
}
