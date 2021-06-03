package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Row(
    horizontalAlignment: HorizontalAlignment? = null,
    attrs: AttrsBuilder<Tag.Div>.() -> Unit = { },
    content: @Composable () -> Unit
) {
    Div(attrs = {
        classes("row")
        horizontalAlignment?.let { classes(it.toString()) }
        attrs()
    }) { content() }
}