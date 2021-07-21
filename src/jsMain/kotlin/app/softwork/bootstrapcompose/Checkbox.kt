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
    id: String = remember { UUID().toString() },
    disabled: Boolean = false,
    inline: Boolean = false,
    switch: Boolean = false,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onClick: (Boolean) -> Unit
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div({
        classes(BSClasses.formCheck)
        classes(*classes)
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
            forId("_$id")
        }) {
            Text(label)
        }
    }
}
