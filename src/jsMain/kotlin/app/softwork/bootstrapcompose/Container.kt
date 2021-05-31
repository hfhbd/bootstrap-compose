package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.elements.*

@Composable
public fun Container(
    type: Breakpoint? = null,
    content: @Composable () -> Unit
): Unit = Div(attrs = {
    classes("container${type?.let { "-$it" } ?: ""}")
}) {
    content()
}