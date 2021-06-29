package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

/**
 * Bootstrap Icon shortcut. You must include Bootstrap Icons by yourself.
 * [Install](https://icons.getbootstrap.com/#install)
 */
@Composable
public fun Icon(iconName: String, attrsBuilder: AttrBuilderContext<HTMLElement>? = null) {
    I({
        classes("bi", "bi-$iconName")
        attrsBuilder?.invoke(this)
    })
}
