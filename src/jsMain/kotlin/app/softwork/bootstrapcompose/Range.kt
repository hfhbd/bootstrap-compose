package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.web.attributes.*
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.RangeInput
import org.w3c.dom.HTMLInputElement

@Composable
public fun Range(
    value: Number,
    min: Number? = null,
    max: Number? = null,
    step: Number = 1,
    disabled: Boolean = false,
    id: String = remember { "_${UUID()}" },
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (SyntheticInputEvent<Number?, HTMLInputElement>) -> Unit,
) {
    RangeInput(value, min, max, step) {
        onInput {
            onInput(it)
        }
        if (disabled) {
            disabled()
        }
        id(id)
        classes(BSClasses.formRange)
        attrs?.invoke(this)
    }
}
