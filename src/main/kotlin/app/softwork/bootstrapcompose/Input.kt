package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.attributes.builders.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.events.*
import org.w3c.dom.*
import org.w3c.dom.Text

@Composable
public fun <T> Input(
    label: String,
    value: String,
    type: InputType<T>,
    placeholder: String? = null,
    autocomplete: AutoComplete = AutoComplete.off,
    labelClasses: String = "form-label",
    inputClasses: String = "form-control",
    attrs: (InputAttrsScope<T>.() -> Unit)? = null,
    onInput: (SyntheticInputEvent<T, HTMLInputElement>) -> Unit
) {
    Style
    Label(forId = null, attrs = {
        classes(labelClasses)
    }) {
        Text(label)
        Input(type = type, attrs = {
            autoComplete(autocomplete)
            attrs?.invoke(this)
            classes(inputClasses)
            value(value)
            if(placeholder != null) {
                placeholder(placeholder)
            }
            onInput {
                onInput(it)
            }
        })
    }
}
