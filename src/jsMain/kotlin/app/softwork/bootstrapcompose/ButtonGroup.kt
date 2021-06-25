package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun ButtonGroup(content: ContentBuilder<HTMLDivElement>) {
    Div({ classes("btn-group") }) {
        content()
    }
}
