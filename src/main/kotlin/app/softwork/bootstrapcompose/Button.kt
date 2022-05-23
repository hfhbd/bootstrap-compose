package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.type
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text
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
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

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
        classes(*classes)
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
