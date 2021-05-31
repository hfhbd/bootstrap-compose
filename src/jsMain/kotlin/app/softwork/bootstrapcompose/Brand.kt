package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.elements.*

@Composable
public fun Brand(content: @Composable () -> Unit): Unit = Div(attrs = {
    classes("navbar-brand")
}) {
    content()
}