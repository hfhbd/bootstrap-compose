package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.attributes.*
import androidx.compose.web.attributes.Tag.*
import androidx.compose.web.elements.*
import org.w3c.dom.*
import org.w3c.dom.Text

@Composable
public inline fun DateTimeInput(
    label: String,
    labelClasses: String = "form-label",
    inputClasses: String = "form-control",
    placeholder: String,
    value: String,
    crossinline attrs: AttrsBuilder<Input>.() -> Unit = { },
    crossinline onChange: (HTMLInputElement) -> Unit
): Unit = Label(forId = "", attrs = {
    classes(labelClasses)
    attr("for", null)
}) {
    Text(label)
    Input(type = InputType.DateTimeLocal, attrs = {
        attrs()
        classes(inputClasses)
        value(value)
        placeholder(placeholder)
        addEventListener("input") {
            onChange(it.nativeEvent.target as HTMLInputElement)
        }
    })
}