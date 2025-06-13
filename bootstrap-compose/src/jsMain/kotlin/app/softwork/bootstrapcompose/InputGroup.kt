package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.attributes.builders.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.events.*
import org.w3c.dom.*
import kotlin.uuid.*

@Composable
@OptIn(ExperimentalUuidApi::class)
public fun InputGroup(
    inputId: String = remember { "_${Uuid.random()}" },
    size: InputGroupSize = InputGroupSize.Default,
    content: @Composable InputGroupContext.() -> Unit
) {
    Style
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
        }
    ) {
        scope.content()
    }
}

public class InputGroupContext(private val inputId: String) {
    private fun <K> InputAttrsScope<K>.buildInputAttrs(
        disabled: Boolean = false,
        autocomplete: AutoComplete = AutoComplete.off,
        classes: List<String>?,
        attrs: (InputAttrsScope<K>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<K, HTMLInputElement>) -> Unit
    ) {
        classes(BSClasses.formControl)
        if (classes != null) {
            classes(classes)
        }
        id(inputId)
        if (disabled) {
            disabled()
        }
        autoComplete(autocomplete)
        attrs?.invoke(this)
        onInput { event ->
            onInput(event)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun <K> Input(
        type: InputType<K>,
        autocomplete: AutoComplete = AutoComplete.off,
        styling: (Styling.() -> Unit)? = null,
        attrs: (InputAttrsScope<K>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<K, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        Input(
            type = type,
            attrs = {
                buildInputAttrs(false, autocomplete, classes, attrs, onInput)
            }
        )
    }

    @Composable
    @NonRestartableComposable
    public fun DateInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.DateInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun DateTimeLocalInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.DateTimeLocalInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun EmailInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.EmailInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun FileInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.FileInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun HiddenInput(
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.HiddenInput {
            buildInputAttrs(disabled, autocomplete = AutoComplete.off, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun MonthInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.MonthInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun NumberInput(
        value: Number?,
        autocomplete: AutoComplete = AutoComplete.off,
        min: Number? = null,
        max: Number? = null,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<Number?>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<Number?, HTMLInputElement>) -> Unit
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        NumberInput(value, min, max) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun PasswordInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.PasswordInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun SearchInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.SearchInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun TelInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrsBuilder: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.TelInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrsBuilder, onInput)
        }
    }

    @Composable
    public fun TextInput(
        value: String,
        placeholder: String? = null,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrs: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.TextInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrs, onInput)
            placeholder?.let {
                placeholder(it)
            }
        }
    }

    @Composable
    @NonRestartableComposable
    public fun TextAreaInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrs: AttrBuilderContext<HTMLTextAreaElement>? = null,
        onInput: (SyntheticInputEvent<String, HTMLTextAreaElement>) -> Unit
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        TextArea(attrs = {
            classes(BSClasses.formControl)
            if (classes != null) {
                classes(classes = classes)
            }
            autoComplete(autocomplete)
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
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrs: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.TimeInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrs, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun UrlInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrs: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.UrlInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrs, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun WeekInput(
        value: String,
        autocomplete: AutoComplete = AutoComplete.off,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrs: (InputAttrsScope<String>.() -> Unit)? = null,
        onInput: (SyntheticInputEvent<String, HTMLInputElement>) -> Unit,
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        org.jetbrains.compose.web.dom.WeekInput(value) {
            buildInputAttrs(disabled, autocomplete, classes, attrs, onInput)
        }
    }

    @Composable
    @NonRestartableComposable
    public fun SelectInput(
        disabled: Boolean,
        autocomplete: AutoComplete = AutoComplete.off,
        styling: (Styling.() -> Unit)? = null,
        attrs: AttrBuilderContext<HTMLSelectElement>? = null,
        onChange: (List<String>) -> Unit,
        content: @Composable SelectContext.() -> Unit
    ) {
        Style
        Select(
            disabled = disabled,
            id = inputId,
            autocomplete = autocomplete,
            styling = styling,
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
        styling: (Styling.() -> Unit)? = null,
        attrs: AttrBuilderContext<HTMLSpanElement>? = null
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        Span(attrs = {
            classes(BSClasses.inputGroupText)
            if (classes != null) {
                classes(classes = classes)
            }
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
        styling: (Styling.() -> Unit)? = null,
        attrs: AttrBuilderContext<HTMLLabelElement>? = null
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        Label(attrs = {
            classes(BSClasses.inputGroupText)
            if (classes != null) {
                classes(classes = classes)
            }
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
        size: ButtonSize = ButtonSize.Default,
        disabled: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        attrs: AttrBuilderContext<HTMLButtonElement>? = null,
        action: () -> Unit
    ) {
        Style
        Button(title, color, size, false, type, disabled, styling, attrs, action)
    }

    @Composable
    public fun DropDownAddOn(
        title: String,
        color: Color = Color.Primary,
        size: ButtonSize = ButtonSize.Default,
        styling: (Styling.() -> Unit)? = null,
        block: @Composable DropDownBuilder.() -> Unit
    ) {
        Style
        DropDown(title, inputId, size, color, styling, block = block)
    }

    @Composable
    public fun CheckboxAddOn(
        checked: Boolean,
        styling: (Styling.() -> Unit)? = null,
        attrs: AttrBuilderContext<HTMLDivElement>? = null,
        onClick: (Boolean) -> Unit
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        Div(attrs = {
            classes(BSClasses.inputGroupText)
            if (classes != null) {
                classes(classes = classes)
            }
            attrs?.invoke(this)
        }) {
            CheckboxInput(checked, attrs = {
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
        styling: (Styling.() -> Unit)? = null,
        attrs: AttrBuilderContext<HTMLDivElement>? = null,
        onClick: (Boolean) -> Unit
    ) {
        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        Div(attrs = {
            classes(BSClasses.inputGroupText)
            if (classes != null) {
                classes(classes = classes)
            }
            attrs?.invoke(this)
        }) {
            RadioInput(checked, attrs = {
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
