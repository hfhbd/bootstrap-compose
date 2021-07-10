package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
public fun Box(styling: Styling.() -> Unit, content: ContentBuilder<HTMLDivElement>? = null) {
    val stylingSpecs = Styling()
    stylingSpecs.styling()

    Div(attrs = {
        classes(*stylingSpecs.generateClassStrings())
    }, content = content)
}