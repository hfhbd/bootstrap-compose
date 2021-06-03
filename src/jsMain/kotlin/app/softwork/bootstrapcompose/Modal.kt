package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Modal(
    size: Breakpoint? = null,
    scrollable: Boolean = false,
    buttonTitle: String,
    id: String,
    buttonColor: Color = Color.Primary,
    header: String,
    footer: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Div {
        Button({
            classes("btn", "btn-$buttonColor")
            attr("data-bs-toggle", "modal")
            attr("data-bs-target", "#a$id")
        }) {
            Text(buttonTitle)
        }
        Div({
            classes("modal")
            id("a$id")
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
                Div({ classes("modal-content") }) {
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
                    Div({ classes("modal-body") }) {
                        content()
                    }
                    Div({ classes("modal-footer") }) {
                        footer()
                    }
                }
            }
        }
    }
}