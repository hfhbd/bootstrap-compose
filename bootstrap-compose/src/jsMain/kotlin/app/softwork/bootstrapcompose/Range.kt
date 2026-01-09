package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.events.*
import org.w3c.dom.*
import kotlin.uuid.*

@Composable
@OptIn(ExperimentalUuidApi::class)
public fun Range(
    value: Number,
    min: Number? = null,
    max: Number? = null,
    step: Number = 1,
    disabled: Boolean = false,
    id: String = remember { "_${Uuid.random()}" },
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (SyntheticInputEvent<Number?, HTMLInputElement>) -> Unit,
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    RangeInput(value, min, max, step) {
        if (classes != null) {
            classes(classes = classes)
        }
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
