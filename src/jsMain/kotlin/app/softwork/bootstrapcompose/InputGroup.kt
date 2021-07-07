package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.attributes.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun InputGroup(
    inputId: String = remember { "_${UUID()}" },
    size: InputGroupSize = InputGroupSize.Default,
    content: @Composable InputGroupContext.() -> Unit
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
    private fun <K> InputAttrsBuilder<K>.buildInputAttrs(
        disabled: Boolean = false,
        autocomplete: String = AutoComplete.off,
        attrs: (InputAttrsBuilder<K>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<K, HTMLInputElement>) -> Unit
    ) {
        classes(BSClasses.formControl)
        id(inputId)
        if (disabled) {
            disabled()
        }
        attr("autocomplete", autocomplete)
        attrs?.invoke(this)
        onInput { event ->
            onInput(event)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun <K> Input(
        type: InputType<K>,
        autocomplete: String = AutoComplete.off,
        attrs: (InputAttrsBuilder<K>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<K, HTMLInputElement>) -> Unit,
    ) {
        Input(type = type,
            attrs = {
                buildInputAttrs(false, autocomplete, attrs, onInput)
            })
    }

    @Composable
    @NonRestartableComposable
    public fun DateInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.DateInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun DateTimeLocalInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.DateTimeLocalInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun EmailInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.EmailInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun FileInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.FileInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun HiddenInput(
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.HiddenInput {
            buildInputAttrs(disabled, autocomplete = AutoComplete.off, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun MonthInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.MonthInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun NumberInput(
        value: Number?,
        autocomplete: String = AutoComplete.off,
        min: Number? = null,
        max: Number? = null,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<Number?>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<Number?, HTMLInputElement>) -> Unit
    ) {
        NumberInput(value, min, max) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun PasswordInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.PasswordInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun SearchInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.SearchInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun TelInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrsBuilder: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.TelInput(value) {
            buildInputAttrs(disabled, autocomplete, attrsBuilder, onInput)
        }
    }

    @Composable
    public fun TextInput(
        value: String,
        placeholder: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        TextInput(value) {
            buildInputAttrs(disabled, autocomplete, attrs, onInput)
            placeholder(placeholder)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun TextAreaInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrs: AttrBuilderContext<HTMLTextAreaElement>? = null,
        onInput: (SyntheticInputEvent<String, HTMLTextAreaElement>) -> Unit
    ) {
        TextArea(attrs = {
            classes(BSClasses.formControl)
            attr("autocomplete", autocomplete)
            id(inputId)
            if (disabled) {
                disabled()
            }
            attrs?.invoke(this)
            onInput { event ->
                onInput(event)
            }
        }, value = value)
    }

    @Composable
    @NonRestartableComposable
    public fun TimeInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.TimeInput(value) {
            buildInputAttrs(disabled, autocomplete, attrs, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun UrlInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.UrlInput(value) {
            buildInputAttrs(disabled, autocomplete, attrs, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun WeekInput(
        value: String,
        autocomplete: String = AutoComplete.off,
        disabled: Boolean = false,
        attrs: (InputAttrsBuilder<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        org.jetbrains.compose.web.dom.WeekInput(value) {
            buildInputAttrs(disabled, autocomplete, attrs, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun SelectInput(
        disabled: Boolean,
        autocomplete: String = AutoComplete.off,
        attrs: AttrBuilderContext<HTMLSelectElement>? = null,
        onChange: (List<String>) -> Unit,
        content: @Composable SelectContext.() -> Unit
    ) {
        Select(
            disabled = disabled,
            id = inputId,
            autocomplete = autocomplete,
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
