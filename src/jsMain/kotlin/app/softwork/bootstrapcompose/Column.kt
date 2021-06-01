package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Column(
    breakpoint: Breakpoint? = null,
    size: Int? = null,
    content: @Composable () -> Unit
): Unit =
    Div(attrs = {
        classes("col")
        if (breakpoint != null) {
            classes("col-$breakpoint")
        }
        if (size != null) {
            classes("col-$size")
        }
    }) {
        content()
    }