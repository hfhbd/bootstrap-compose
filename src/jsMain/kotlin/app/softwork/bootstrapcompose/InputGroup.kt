package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.forId
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.value
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun <T> InputGroup(
    value: T,
    type: InputType<T>,
    placeholder: String? = null,
    attrs: AttrBuilderContext<HTMLInputElement>? = null,
    onInput: (T, HTMLInputElement) -> Unit,
    content: InputGroupScope.() -> Unit = {}
) {
    val scope = InputGroupScope()
    scope.content()

    val inputId = remember {"_${UUID()}"}

    scope.label?.let {
        if (!it.floating) {
            Label(attrs = {
                forId(inputId)
                classes(BSClasses.formLabel)
                //it.second?.invoke(this)
                it.attrs?.invoke(this)
            }) {
                it.content?.invoke(this)
            }
        }
    }

    Div(
        attrs = {
            classes(BSClasses.inputGroup)
            if (scope.label?.floating == true) {
                classes(BSClasses.formFloating)
            }
        }) {

        scope.label?.let {
            if (it.floating) {
                Label(attrs = {
                    forId(inputId)
                    classes(BSClasses.formLabel)
                    it.attrs?.invoke(this)
                }) {
                    it.content?.invoke(this)
                }
            }
        }

        scope.leftAddOn?.let {
            Span(attrs = {
                classes(BSClasses.inputGroupText)
                it.attrs?.invoke(this)
            }) {
                it.content?.invoke(this)
            }
        }
        Input(
            type = type,
            attrs = {
                id(inputId)
                classes(BSClasses.formControl)
                attrs?.invoke(this)
                value(value.toString())
                placeholder?.let {
                    placeholder(it)
                }
                onInput {
                    onInput(it.value, it.target)
                }
            })
        scope.rightAddOn?.let {
            Span(attrs = {
                classes(BSClasses.inputGroupText)
                it.attrs?.invoke(this)
            }) {
                it.content?.invoke(this)
            }
        }
    }

    scope.help?.let {
        Div(attrs = {
            classes(BSClasses.formText)
            it.attrs?.invoke(this)
        }) {
            it.content?.invoke(this)
        }
    }
}


public class InputGroupScope {
    //Generic used To get around https://github.com/JetBrains/compose-jb/issues/746
    internal data class LabelContent<T>(
        val floating: Boolean,
        val attrs: AttrBuilderContext<HTMLLabelElement>?,
        val content: T //should be ContentBuilder<HTMLLabelElement>?
    )

    internal data class AddOn<T>(
        val attrs: AttrBuilderContext<HTMLSpanElement>?,
        val content: T //ContentBuilder<HTMLSpanElement>?
    )

    internal data class HelpParams<T>(
        val attrs: AttrBuilderContext<HTMLDivElement>?,
        val content: T //ContentBuilder<HTMLDivElement>?>?
    )

    internal var label: LabelContent<ContentBuilder<HTMLLabelElement>?>? = null
    internal var leftAddOn: AddOn<ContentBuilder<HTMLSpanElement>?>? = null
    internal var rightAddOn: AddOn<ContentBuilder<HTMLSpanElement>?>? = null
    internal var help: HelpParams<ContentBuilder<HTMLDivElement>?>? = null

    public fun Label(
        floating: Boolean = false,
        attrs: AttrBuilderContext<HTMLLabelElement>? = null,
        content: ContentBuilder<HTMLLabelElement>? = null
    ) {
        label = LabelContent(floating, attrs, content)
    }

    public fun LeftAddOn(
        attrs: AttrBuilderContext<HTMLSpanElement>? = null,
        content: ContentBuilder<HTMLSpanElement>? = null
    ) {
        leftAddOn = AddOn(attrs, content)
    }

    public fun RightAddOn(
        attrs: AttrBuilderContext<HTMLSpanElement>? = null,
        content: ContentBuilder<HTMLSpanElement>? = null
    ) {
        rightAddOn = AddOn(attrs, content)
    }

    public fun Help(
        attrs: AttrBuilderContext<HTMLDivElement>? = null,
        content: ContentBuilder<HTMLDivElement>? = null
    ) {
        help = HelpParams(attrs, content)
    }
}