package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

/**
 * Bootstrap Icon shortcut. You must include Bootstrap Icons by yourself.
 * [Install](https://icons.getbootstrap.com/#install)
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
    } ?: arrayOf()

    I({
        classes("bi", "bi-$iconName")
        classes(classes = classes)
        attrsBuilder?.invoke(this)
    })
}
