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
    I({
        classes("bi", "bi-$iconName")
        if (styling != null) {
            Styling(styling)
        }
        attrsBuilder?.invoke(this)
    })
}
