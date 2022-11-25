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

    Label(
        forId = forId,
        attrs = {
            classes(BSClasses.formLabel)
            if (styling != null) {
                Styling(styling)
            }
            attrs?.invoke(this)
        },
        content = content
    )
}
