package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*
import org.w3c.dom.Text

@Composable
public fun DateTimeInput(
    label: String,
    placeholder: String,
    value: String,
    labelClasses: String = "form-label",
    inputClasses: String = "form-control",
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (HTMLInputElement) -> Unit
) {
    Label(forId = null, attrs = {
        classes(labelClasses)
    }) {
        Text(label)
        Input(type = InputType.DateTimeLocal, attrs = {
            attrs?.invoke(this)
            classes(inputClasses)
            value(value)
            placeholder(placeholder)
            addEventListener("input") {
                onInput(it.nativeEvent.target as HTMLInputElement)
            }
        })
    }
}
