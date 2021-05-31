package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.elements.*

@Composable
public fun Column(
    breakpoint: Breakpoint? = null,
    size: Int? = null,
    content: @Composable () -> Unit
): Unit =
    Div(attrs = { classes("col${breakpoint?.let { "-$it" } ?: ""}${size?.let { "-$it" } ?: ""}") }) {
        content()
    }