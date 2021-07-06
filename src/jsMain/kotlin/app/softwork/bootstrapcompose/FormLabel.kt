package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun FormLabel(
    forId: String? = null,
    attrs: AttrBuilderContext<HTMLLabelElement>? = null,
    content: ContentBuilder<HTMLLabelElement>? = null
) {
    Label(
        forId = forId,
        attrs = {
            classes(BSClasses.formLabel)
            attrs?.invoke(this)
        },
        content = content
    )
}
