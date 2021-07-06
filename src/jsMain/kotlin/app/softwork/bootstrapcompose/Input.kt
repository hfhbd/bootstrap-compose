package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.attributes.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*
import org.w3c.dom.Text

@Composable
public fun<T> Input(
    label: String,
    placeholder: String,
    value: String,
    type: InputType<T>,
    labelClasses: String = "form-label",
    inputClasses: String = "form-control",
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (SyntheticInputEvent<T, HTMLInputElement>) -> Unit
) {
    Label(forId = null, attrs = {
        classes(labelClasses)
    }) {
        Text(label)
        Input(type = type, attrs = {
            attrs?.invoke(this)
            classes(inputClasses)
            value(value)
            placeholder(placeholder)
            onInput {
                onInput(it)
            }
        })
    }
}
