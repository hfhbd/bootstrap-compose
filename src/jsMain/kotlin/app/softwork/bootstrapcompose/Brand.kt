package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Brand(content: @Composable () -> Unit): Unit = Div(attrs = {
    classes("navbar-brand")
}) {
    content()
}