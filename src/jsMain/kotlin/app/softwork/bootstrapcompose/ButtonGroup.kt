package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun ButtonGroup(content: @Composable () -> Unit) {
    Div({ classes("btn-group") }) {
        content()
    }
}
