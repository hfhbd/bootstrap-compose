package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.attributes.builders.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Checkbox(
    checked: Boolean,
    label: String,
    id: String = remember { UUID().toString() },
    disabled: Boolean = false,
    inline: Boolean = false,
    switch: Boolean = false,
    styling: (Styling.() -> Unit)? = null,
    attrs: (InputAttrsScope<Boolean>.() -> Unit)? = null,
    onClick: (Boolean) -> Unit
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    Div({
        classes(BSClasses.formCheck)
        if (classes != null) {
            classes(classes = classes)
        }
        if (inline) {
            classes(BSClasses.formCheckInline)
        }
        if (switch) {
            classes(BSClasses.formSwitch)
        }
    }) {
        Input(attrs = {
            classes(BSClasses.formCheckInput)
            id("_$id")
            checked(checked)
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
            forId("_$id")
        }) {
            Text(label)
        }
    }
}
