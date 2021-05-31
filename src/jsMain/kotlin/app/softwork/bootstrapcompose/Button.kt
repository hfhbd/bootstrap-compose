package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.attributes.*
import androidx.compose.web.elements.*

@Composable
public inline fun Button(
    title: String,
    color: Color = Color.Primary,
    vararg customClasses: String = emptyArray(),
    type: ButtonType = ButtonType.Submit,
    crossinline attrs: AttrsBuilder<Tag.Button>.() -> Unit = { },
    crossinline onClick: () -> Unit
): Unit = Button(attrs = {
    classes("btn", "btn-$color", *customClasses)
    attrs()
    type(type)
    onClick {
        onClick()
    }
}) {
    Text(title)
}