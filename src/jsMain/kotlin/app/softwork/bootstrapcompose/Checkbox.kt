package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Checkbox(
    checked: Boolean,
    label: String,
    id: String = UUID().toString(),
    attrs: AttrsBuilder<Tag.Input>.() -> Unit = { },
    onClick: () -> Unit
) {
    Div({ classes("form-check") }) {
        Input(attrs = {
            classes("form-check-input")
            name("flexRadioDefault1")
            id("a$id")
            checked(checked)
            onClick {
                onClick()
            }
            attrs()
        }, type = InputType.Radio)
        Label(attrs = {
            classes("form-check-label")
            forId("a$id")
        }) {
            Text(label)
        }
    }
}