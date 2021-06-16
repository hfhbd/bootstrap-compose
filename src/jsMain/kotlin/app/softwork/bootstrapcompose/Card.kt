package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun Card(
    header: String,
    color: Color = Color.Dark,
    attrs: AttrsBuilder<HTMLDivElement>.() -> Unit = {},
    footer: @Composable () -> Unit = {},
    body: @Composable () -> Unit
) {
    Div({
        classes("card", "border-$color", "mb-3")
        attrs()
    }) {
        Div({ classes("card-header") }) {
            Text(header)
        }
        Div({ classes("card-body") }) {
            body()
        }
        Div({ classes("card-footer") }) {
            footer()
        }
    }
}
