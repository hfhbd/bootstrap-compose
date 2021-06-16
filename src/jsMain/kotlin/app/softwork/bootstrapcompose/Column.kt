package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Column(
    auto: Boolean = false,
    horizontalAlignment: HorizontalAlignment? = null,
    breakpoint: Breakpoint? = null,
    size: Int? = null,
    attrs: AttrsBuilder<HTMLDivElement>.() -> Unit = { },
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
        attrs()
    }) {
        content()
    }
}
