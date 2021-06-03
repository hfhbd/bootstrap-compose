package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Column(
    auto: Boolean = false,
    horizontalAlignment: HorizontalAlignment? = null,
    breakpoint: Breakpoint? = null,
    size: Int? = null,
    content: @Composable () -> Unit
) {
    Div(attrs = {
        classes("col")
        if (breakpoint != null) {
            classes("col-$breakpoint")
        }
        if (size != null) {
            classes("col-$size")
        }
        if (auto) {
            classes("col-auto")
        }
        horizontalAlignment?.let { classes(it.toString()) }
    }) {
        content()
    }
}