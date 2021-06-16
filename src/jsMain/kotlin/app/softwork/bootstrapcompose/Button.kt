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
    vararg customClasses: String = emptyArray(),
    type: ButtonType = ButtonType.Submit,
    attrs: AttrsBuilder<HTMLButtonElement>.() -> Unit = { },
    onClick: () -> Unit
) {
    Button(attrs = {
        classes("btn", "btn-$color", *customClasses)
        attrs()
        type(type)
        onClick {
            onClick()
        }
    }) {
        Text(title)
    }
}
