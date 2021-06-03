package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Button(
    title: String,
    color: Color = Color.Primary,
    vararg customClasses: String = emptyArray(),
    type: ButtonType = ButtonType.Submit,
    attrs: AttrsBuilder<Tag.Button>.() -> Unit = { },
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