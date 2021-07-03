package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import androidx.compose.web.attributes.SyntheticInputEvent
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Range(
    value: Number,
    disabled: Boolean = false,
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (Number?, HTMLInputElement) -> Unit,
    content: @Composable RangeScope.() -> Unit = {}
) {
    val inputId = "_${UUID()}"
    val scope = RangeScope(inputId)

    scope.content()

    Input(
        type = InputType.Range,
        attrs = {
            console.info("setting value to: $value")
            value(value.toString())
            if (disabled) {
                disabled()
            }
            id(inputId)
            classes(BSClasses.formRange)
            attrs?.invoke(this)
            onInput {
                onInput(it.value, it.target)
            }
        })
}


public class RangeScope(public val forId: String) {
    @Composable
    public fun Label(
        attrs: AttrBuilderContext<HTMLLabelElement>? = null,
        content: ContentBuilder<HTMLLabelElement>? = null
    ) {
        org.jetbrains.compose.web.dom.Label(attrs = {
            classes("form-label")
            forId(forId)
            attrs?.invoke(this)
        }) {
            content?.invoke(this)
        }
    }
}