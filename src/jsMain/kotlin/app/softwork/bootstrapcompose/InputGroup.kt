package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.web.attributes.InputAttrsBuilder
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun InputGroup(
    inputId: String = remember { "_${UUID()}" },
    size: InputGroupSize = InputGroupSize.Default,
    content: @Composable InputGroupContext.() -> Unit = {}
) {
    val scope = InputGroupContext(inputId)

    Div(
        attrs = {
            classes(BSClasses.inputGroup)
            when (size) {
                InputGroupSize.Small -> classes(BSClasses.inputGroupSmall)
                InputGroupSize.Large -> classes(BSClasses.inputGroupLarge)
                else -> {
                }
            }
        }) {
        scope.content()
    }
}


public class InputGroupContext(private val inputId: String) {
    private fun <K> buildInputAttrs(
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<K>.() -> Unit)? = null,
        onInput: (K, HTMLInputElement) -> Unit
    ): (InputAttrsBuilder<K>.() -> Unit) {
        return {
            classes(BSClasses.formControl)
            id(inputId)
            if (disabled) disabled()
            attrs?.invoke(this)
            onInput { event ->
                onInput(event.value, event.target)
            }
        }
    }

    @Composable
    @NonRestartableComposable
    public fun <K> Input(
        type: InputType<K>,
        attrs: (InputAttrsBuilder<K>.() -> Unit)? = null,
        onInput: (K, HTMLInputElement) -> Unit,
    ) {
        Input(type = type,
            attrs = { buildInputAttrs(false, attrs, onInput)() })
    }

    @Composable
    @NonRestartableComposable
    public fun DateInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: InputAttrsBuilder<String>.() -> Unit = {},
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.DateInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun DateTimeLocalInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: InputAttrsBuilder<String>.() -> Unit = {},
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.DateTimeLocalInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun EmailInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: InputAttrsBuilder<String>.() -> Unit = {},
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.EmailInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun FileInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.FileInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun HiddenInput(
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.HiddenInput() {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun MonthInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.MonthInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun NumberInput(
        value: Number? = null,
        min: Number? = null,
        max: Number? = null,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<Number?>.() -> Unit)? = null,
        onInput: (Number?, HTMLInputElement) -> Unit
    ) {
        org.jetbrains.compose.web.dom.NumberInput(value, min, max) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun PasswordInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.PasswordInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun SearchInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.SearchInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun TelInput(
        value: String = "",
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.TelInput(value) {
            buildInputAttrs(disabled, attrsBuilder, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun TextInput(
        value: String = "",
        placeholder: String = "",
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.TextInput(value) {
            placeholder(placeholder)
            buildInputAttrs(disabled, attrs, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun TextAreaInput(
        value: String = "",
        disabled: Boolean = false,
        attrs: AttrBuilderContext<HTMLTextAreaElement>? = null,
        onInput: (String) -> Unit
    ) {
        TextArea(attrs = {
            classes(BSClasses.formControl)
            id(inputId)
            if (disabled) disabled()
            attrs?.invoke(this)
            onInput { event ->
                onInput(event.value)
            }
        }, value = value)
    }

    @Composable
    @NonRestartableComposable
    public fun TimeInput(
        value: String = "",
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.TimeInput(value) {
            buildInputAttrs(disabled, attrs, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun UrlInput(
        value: String = "",
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.UrlInput(value) {
            buildInputAttrs(disabled, attrs, onInput)()
        }
    }

    @Composable
    @NonRestartableComposable
    public fun WeekInput(
        value: String = "",
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (String, HTMLInputElement) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.WeekInput(value) {
            buildInputAttrs(disabled, attrs, onInput)()
        }
    }

    @Composable
    public fun SelectInput(
        disabled: Boolean = false,
        attrs: AttrBuilderContext<HTMLSelectElement>? = null,
        onChange: (List<String>) -> Unit,
        content: @Composable SelectContext.() -> Unit
    ) {
        Select(
            disabled = disabled,
            id = inputId,
            attrs = attrs,
            onChange = onChange,
            content = content
        )
    }

    /**
     * Implements the Input group add on as text in a Span element.
     */
    @Composable
    public fun TextAddOn(
        text: String,
        attrs: AttrBuilderContext<HTMLSpanElement>? = null
    ) {
        Span(attrs = {
            classes(BSClasses.inputGroupText)
            attrs?.invoke(this)
        }) {
            Text(text)
        }
    }

    /**
     * Implements the Input group add on as text in a Label element. The label element's forId is set equal to the
     * Input's id. An example of this usage is in the Custom select Bootstrap documentation
     * at https://getbootstrap.com/docs/5.0/forms/input-group/#custom-select.
     */
    @Composable
    public fun LabelAddOn(
        text: String,
        attrs: AttrBuilderContext<HTMLLabelElement>? = null
    ) {
        Label(attrs = {
            classes(BSClasses.inputGroupText)
            forId(inputId)
            attrs?.invoke(this)
        }) {
            Text(text)
        }
    }

    @Composable
    public fun ButtonAddOn(
        title: String,
        color: Color = Color.Primary,
        type: ButtonType = ButtonType.Submit,
        attrs: AttrBuilderContext<HTMLButtonElement>? = null,
        action: () -> Unit
    ) {
        Button(title, color, type, attrs, action)
    }

    @Composable
    public fun DropDownAddOn(
        title: String,
        color: Color = Color.Primary,
        block: DropDownBuilder.() -> Unit
    ) {
        DropDown(title, inputId, color, block)
    }

    @Composable
    public fun CheckboxAddOn(
        checked: Boolean,
        attrs: AttrBuilderContext<HTMLDivElement>? = null,
        onClick: (Boolean) -> Unit
    ) {
        Div(attrs = {
            classes(BSClasses.inputGroupText)
            attrs?.invoke(this)
        }) {
            CheckboxInput(checked, attrsBuilder = {
                onInput { event ->
                    onClick(event.value)
                }
                classes(BSClasses.formCheckInput, "mt-0")
            })
        }
    }

    @Composable
    public fun RadioAddOn(
        checked: Boolean,
        attrs: AttrBuilderContext<HTMLDivElement>? = null,
        onClick: (Boolean) -> Unit
    ) {
        Div(attrs = {
            classes(BSClasses.inputGroupText)
            attrs?.invoke(this)
        }) {
            RadioInput(checked, attrsBuilder = {
                onInput { event ->
                    onClick(event.value)
                }
                classes(BSClasses.formCheckInput, "mt-0")
            })
        }
    }
}

public enum class InputGroupSize {
    Small,
    Default,
    Large
}
