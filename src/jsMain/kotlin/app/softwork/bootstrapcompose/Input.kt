package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*
import org.w3c.dom.Text

@Composable
public fun Input(
    type: InputType = InputType.Text,
    label: String,
    labelClasses: String = "form-label",
    inputClasses: String = "form-control",
    placeholder: String,
    value: String,
    attrs: AttrsBuilder<Tag.Input>.() -> Unit = { },
    onChange: (HTMLInputElement) -> Unit
) {
    Label(forId = "", attrs = {
        classes(labelClasses)
        attr("for", null)
    }) {
        require(type != InputType.DateTimeLocal) { "Use DateTimeInput instead." }
        Text(label)
        Input(type = type, attrs = {
            attrs()
            classes(inputClasses)
            value(value)
            placeholder(placeholder)
            this.onInput {
                val target = it.nativeEvent.target as HTMLInputElement
                onChange(target)
            }
        })
    }
}