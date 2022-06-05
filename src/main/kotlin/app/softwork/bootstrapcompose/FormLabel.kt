package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun FormLabel(
    forId: String? = null,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLLabelElement>? = null,
    content: ContentBuilder<HTMLLabelElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Label(
        forId = forId,
        attrs = {
            classes(BSClasses.formLabel)
            classes(classes = classes)
            attrs?.invoke(this)
        },
        content = content
    )
}
