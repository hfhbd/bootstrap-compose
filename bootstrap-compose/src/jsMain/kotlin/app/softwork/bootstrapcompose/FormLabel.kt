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
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    Label(
        forId = forId,
        attrs = {
            classes(BSClasses.formLabel)
            if (classes != null) {
                classes(classes = classes)
            }
            attrs?.invoke(this)
        },
        content = content
    )
}
