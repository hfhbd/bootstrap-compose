package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Container(
    fluid: Boolean = false,
    type: Breakpoint? = null,
    content: @Composable () -> Unit
): Unit = Div(attrs = {
    classes("container")
    if (type != null) {
        classes("container-$type")
    }
    if(fluid) {
        classes("container-fluid")
    }
}) {
    content()
}
