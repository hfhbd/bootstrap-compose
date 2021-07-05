package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Label
import org.w3c.dom.HTMLLabelElement

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
        }) {
        content?.invoke(this)
    }
}