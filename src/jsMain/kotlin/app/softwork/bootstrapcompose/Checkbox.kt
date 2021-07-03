package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.attributes.SyntheticInputEvent
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
    disabled: Boolean = false,
    inline: Boolean = false,
    switch: Boolean = false,
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onClick: (Boolean) -> Unit
) {
    Div({
        classes(BSClasses.formCheck)
        if (inline) {
            classes(BSClasses.formCheckInline)
        }
        if(switch){
            classes(BSClasses.formSwitch)
        }
    }) {
        Input(attrs = {
            classes(BSClasses.formCheckInput)
            id("a$id")
            if (checked) {
                checked()
            }
            onInput { event ->
                onClick(event.value)
            }
            if (disabled) {
                disabled()
            }
            attrs?.invoke(this)
        }, type = InputType.Checkbox)
        Label(attrs = {
            classes(BSClasses.formCheckLabel)
            forId("a$id")
        }) {
            Text(label)
        }
    }
}
