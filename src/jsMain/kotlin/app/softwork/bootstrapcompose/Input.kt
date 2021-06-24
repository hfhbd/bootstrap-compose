package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*
import org.w3c.dom.Text

@Composable
public fun Input(
    label: String,
    placeholder: String,
    value: String,
    type: InputType = InputType.Text,
    labelClasses: String = "form-label",
    inputClasses: String = "form-control",
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onChange: (HTMLInputElement) -> Unit
) {
    Label(forId = null, attrs = {
        classes(labelClasses)
    }) {
        require(type != InputType.DateTimeLocal) { "Use DateTimeInput instead." }
        Text(label)
        Input(type = type, attrs = {
            attrs?.invoke(this)
            classes(inputClasses)
            value(value)
            placeholder(placeholder)
            addEventListener("input") {
                val target = it.nativeEvent.target as HTMLInputElement
                onChange(target)
            }
        })
    }
}
