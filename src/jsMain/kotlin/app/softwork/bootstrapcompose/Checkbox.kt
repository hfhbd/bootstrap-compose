package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun Checkbox(
    checked: Boolean,
    label: String,
    id: String = UUID().toString(),
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onClick: () -> Unit
) {
    Div({ classes("form-check") }) {
        Input(attrs = {
            classes("form-check-input")
            name("flexRadioDefault1")
            id("a$id")
            if(checked) {
                checked()
            }
            onClick {
                onClick()
            }
            attrs?.invoke(this)
        }, type = InputType.Radio)
        Label(attrs = {
            classes("form-check-label")
            forId("a$id")
        }) {
            Text(label)
        }
    }
}
