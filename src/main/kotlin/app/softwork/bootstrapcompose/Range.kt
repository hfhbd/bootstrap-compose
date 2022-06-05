package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.events.*
import org.w3c.dom.*

@Composable
public fun Range(
    value: Number,
    min: Number? = null,
    max: Number? = null,
    step: Number = 1,
    disabled: Boolean = false,
    id: String = remember { "_${UUID()}" },
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (SyntheticInputEvent<Number?, HTMLInputElement>) -> Unit,
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    RangeInput(value, min, max, step) {
        classes(classes = classes)
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
