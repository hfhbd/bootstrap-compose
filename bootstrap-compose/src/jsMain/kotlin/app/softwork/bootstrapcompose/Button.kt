package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLButtonElement

@Composable
public fun Button(
    title: String,
    color: Color = Color.Primary,
    size: ButtonSize = ButtonSize.Default,
    outlined: Boolean = false,
    type: ButtonType = ButtonType.Submit,
    disabled: Boolean = false,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    action: () -> Unit
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    Button(attrs = {
        classes("btn")
        classes(size.toString())
        if (outlined) {
            classes("btn-outline-$color")
        } else {
            classes("btn-$color")
        }
        if (disabled) {
            disabled()
        }
        if (classes != null) {
            classes(classes = classes)
        }
        attrs?.invoke(this)
        type(type)
        onClick {
            action()
        }
    }) {
        Text(title)
    }
}

public enum class ButtonSize(private val prefix: String) {
    Default("btn"), Large("btn-lg"), Small("btn-sm");

    override fun toString(): String = prefix
}

@Composable
public fun Button(
    content: ContentBuilder<HTMLButtonElement>,
    color: Color = Color.Primary,
    outlined: Boolean = false,
    type: ButtonType = ButtonType.Submit,
    disabled: Boolean = false,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    action: () -> Unit
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    Button(attrs = {
        classes("btn")
        if (outlined) {
            classes("btn-outline-$color")
        } else {
            classes("btn-$color")
        }
        if (disabled) {
            disabled()
        }
        if (classes != null) {
            classes(classes = classes)
        }
        attrs?.invoke(this)
        type(type)
        onClick {
            action()
        }
    }, content)
}
