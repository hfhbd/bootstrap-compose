package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Brand(attrs: AttrsBuilder<Tag.Div>.() -> Unit = { }, content: @Composable () -> Unit) {
    Div(attrs = {
        classes("navbar-brand")
        attrs()
    }) {
        content()
    }
}