package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun Modal(
    header: String,
    buttonTitle: String,
    size: Breakpoint? = null,
    scrollable: Boolean = false,
    id: String = UUID().toString(),
    buttonColor: Color = Color.Primary,
    styling: (Styling.() -> Unit)? = null,
    footer: ContentBuilder<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div {
        Button({
            classes("btn", "btn-$buttonColor")
            attr("data-bs-toggle", "modal")
            attr("data-bs-target", "#_$id")
        }) {
            Text(buttonTitle)
        }
        Div({
            classes("modal")
            id("_$id")
            tabIndex(-1)
            attr("aria-labelledby", "label$id")
            attr("aria-hidden", "true")
        }) {

            Div({
                classes("modal-dialog")
                if (size != null) {
                    classes("modal-$size")
                }
                if (scrollable) {
                    classes("modal-dialog-scrollable")
                }
            }) {
                Div({
                    classes("modal-content")
                    classes(*classes)
                }) {
                    Div({ classes("modal-header") }) {
                        H5({
                            classes("modal-title")
                            id("label$id")
                        }) {
                            Text(header)
                        }
                        Button({
                            classes("btn-close")
                            attr("data-bs-dismiss", "modal")
                            attr("aria-label", "Close")
                        })
                    }
                    Div({ classes("modal-body") }, content)
                    Div({ classes("modal-footer") }, footer)
                }
            }
        }
    }
}
