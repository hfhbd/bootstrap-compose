package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun Button(
    title: String,
    color: Color = Color.Primary,
    outlined: Boolean = false,
    type: ButtonType = ButtonType.Submit,
    disabled: Boolean = false,
    styling: (Styling.() -> Unit)?=null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    action: () -> Unit
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Button(attrs = {
        classes("btn")
        if(outlined){
            classes("btn-outline-$color")
        }else{
            classes("btn-$color")
        }
        if(disabled){
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
