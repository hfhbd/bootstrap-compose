package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.multiple
import org.jetbrains.compose.web.attributes.selected
import org.jetbrains.compose.web.attributes.size
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLLabelElement
import org.w3c.dom.HTMLOptionElement
import org.w3c.dom.HTMLSelectElement


@Composable
public fun Select(
    multiple: Boolean = false,
    size: SelectSize = SelectSize.Normal,
    rows: Int? = null,
    disabled: Boolean = false,
    attrs: AttrBuilderContext<HTMLSelectElement>? = null,
    onChange: (List<String>) -> Unit,
    content: SelectScope.() -> Unit
) {
    val scope = SelectScope()
    scope.content()

    val id = "_${UUID()}"

    scope.label?.let {
        Label(forId = id, attrs = {
            classes(BSClasses.formLabel)
            it.attrs?.invoke(this)
        }) {
            it.content?.invoke(this)
        }
    }

    Select(attrs = {
        id(id)
        classes(BSClasses.formSelect)
        if (multiple) {
            multiple()
        }
        when (size) {
            SelectSize.Small -> classes(BSClasses.formSelectSmall)
            SelectSize.Large -> classes(BSClasses.formSelectLarge)
            else -> {
            }
        }
        rows?.let {
            size(it)
        }
        if (disabled) {
            disabled()
        }
        attrs?.invoke(this)
        onChange { event ->
            val selectElement = event.nativeEvent.target as HTMLSelectElement
            val options = selectElement.selectedOptions
            val results: MutableList<String> = mutableListOf()
            for (i in 0..options.length) {
                options.item(i)?.let {
                    results += (it as HTMLOptionElement).value
                }
            }
            onChange(results)
        }
    }) {
        scope.options.forEach {
            Option(
                value = it.value,
                attrs = {
                    if (it.selected == true) {
                        selected()
                    }
                    it.attrs?.invoke(this)
                }
            ) {
                it.content?.invoke(this)
            }
        }
    }
}

public class SelectScope() {
    internal data class LabelContent<T>(
        val attrs: AttrBuilderContext<HTMLLabelElement>?,
        val content: T //should be ContentBuilder<HTMLLabelElement>?
    )

    internal data class SelectOption<T>(
        val value: String,
        val selected: Boolean?,
        val attrs: AttrBuilderContext<HTMLOptionElement>? = null,
        val content: T? = null //ContentBuilder<HTMLOptionElement>? = null
    )

    internal val options: MutableList<SelectOption<ContentBuilder<HTMLOptionElement>?>> = mutableListOf()
    internal var label: LabelContent<ContentBuilder<HTMLLabelElement>?>? = null

    public fun Option(
        value: String,
        selected: Boolean? = null,
        attrs: AttrBuilderContext<HTMLOptionElement>? = null,
        content: ContentBuilder<HTMLOptionElement>? = null
    ) {
        options += SelectOption(value, selected, attrs, content)
    }

    public fun Label(
        attrs: AttrBuilderContext<HTMLLabelElement>? = null,
        content: ContentBuilder<HTMLLabelElement>? = null
    ) {
        label = LabelContent(attrs, content)
    }
}

public enum class SelectSize {
    Small,
    Normal,
    Large
}
