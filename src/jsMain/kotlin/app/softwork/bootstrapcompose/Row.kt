package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Row(
    horizontalAlignment: HorizontalAlignment? = null,
    attrs: AttrsBuilder<HTMLDivElement>.() -> Unit = { },
    content: @Composable () -> Unit
) {
    Div(attrs = {
        classes("row")
        horizontalAlignment?.let { classes(it.toString()) }
        attrs()
    }) { content() }
}
