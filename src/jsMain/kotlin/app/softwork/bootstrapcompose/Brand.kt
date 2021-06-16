package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun Brand(attrs: AttrsBuilder<HTMLDivElement>.() -> Unit = { }, content: @Composable () -> Unit) {
    Div(attrs = {
        classes("navbar-brand")
        attrs()
    }) {
        content()
    }
}
