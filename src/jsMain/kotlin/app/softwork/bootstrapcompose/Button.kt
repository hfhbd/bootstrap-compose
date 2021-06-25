package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun Button(
    title: String,
    color: Color = Color.Primary,
    type: ButtonType = ButtonType.Submit,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    action: () -> Unit
) {
    Button(attrs = {
        classes("btn", "btn-$color")
        attrs?.invoke(this)
        type(type)
        onClick {
            action()
        }
    }) {
        Text(title)
    }
}
