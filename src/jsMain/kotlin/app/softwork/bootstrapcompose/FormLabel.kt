package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*
import org.w3c.dom.events.*

@Composable
public fun FormLabel(
    forId: String? = null,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLLabelElement>? = null,
    content: ContentBuilder<HTMLLabelElement>? = null
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Label(
        forId = forId,
        attrs = {
            classes(BSClasses.formLabel)
            classes(*classes)
            attrs?.invoke(this)
        },
        content = content
    )
}

public fun AttrsBuilder<HTMLFormElement>.onSubmit(options: Options = Options.DEFAULT, listener: (Event) -> Unit) {
    addEventListener("submit", options) {
        it.nativeEvent.preventDefault()
        listener(it.nativeEvent)
    }
}
