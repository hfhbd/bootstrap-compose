package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.attributes.*
import androidx.compose.web.elements.*
import androidx.compose.web.elements.Text
import org.w3c.dom.*

@Composable
public inline fun Input(
    type: InputType = InputType.Text,
    label: String,
    labelClasses: String = "form-label",
    inputClasses: String = "form-control",
    placeholder: String,
    value: String,
    crossinline attrs: AttrsBuilder<Tag.Input>.() -> Unit = { },
    crossinline onChange: (HTMLInputElement) -> Unit
): Unit = Label(forId = "", attrs = {
    classes(labelClasses)
    attr("for", null)
}) {
    require(type != InputType.DateTimeLocal)
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