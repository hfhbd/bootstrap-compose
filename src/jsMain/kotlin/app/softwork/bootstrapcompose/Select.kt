package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*


@Composable
public fun Select(
    multiple: Boolean = false,
    size: SelectSize = SelectSize.Default,
    rows: Int? = null,
    disabled: Boolean = false,
    id: String = remember { "_${UUID()}" },
    attrs: AttrBuilderContext<HTMLSelectElement>? = null,
    onChange: (List<String>) -> Unit,
    content: @Composable SelectContext.() -> Unit
) {
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
        val scope = SelectContext()
        scope.content()
    }
}

public class SelectContext {
    @Composable
    public fun Option(
        value: String,
        selected: Boolean? = null,
        attrs: AttrBuilderContext<HTMLOptionElement>? = null,
        content: ContentBuilder<HTMLOptionElement>? = null
    ) {
        org.jetbrains.compose.web.dom.Option(
            value = value,
            attrs = {
                if (selected == true) {
                    selected()
                }
                attrs?.invoke(this)
            }, content = content
        )
    }
}


public enum class SelectSize {
    Small,
    Default,
    Large
}
