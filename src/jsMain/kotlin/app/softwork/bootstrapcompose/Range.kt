package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.forId
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.RangeInput
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLLabelElement

@Composable
public fun Range(
    value: Number,
    min: Number? = null,
    max: Number? = null,
    step: Number = 1,
    disabled: Boolean = false,
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (Number?, HTMLInputElement) -> Unit,
    content: @Composable RangeScope.() -> Unit = {}
) {
    val inputId = remember{ "_${UUID()}" }
    val scope = RangeScope(inputId)

    scope.content()

    RangeInput(value, min, max, step) {
        onInput { event ->
            onInput(event.value, event.target)
        }
        if(disabled){
            disabled()
        }
        id(inputId)
        classes(BSClasses.formRange)
        attrs?.invoke(this)
    }
}


public class RangeScope(public val forId: String) {
    @Composable
    public fun Label(
        attrs: AttrBuilderContext<HTMLLabelElement>? = null,
        content: ContentBuilder<HTMLLabelElement>? = null
    ) {
        org.jetbrains.compose.web.dom.Label(attrs = {
            classes(BSClasses.formLabel)
            forId(forId)
            attrs?.invoke(this)
        }) {
            content?.invoke(this)
        }
    }
}