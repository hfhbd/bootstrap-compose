package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*


@Composable
public fun Modal(
    header: String,
    size: Breakpoint? = null,
    scrollable: Boolean = false,
    id: String = UUID().toString(),
    styling: (Styling.() -> Unit)? = null,
    onDismissRequest: () -> Unit,
    footer: ContentBuilder<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    var bsModal: Modal? = null //Bootstrap Modal class

    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div({
        classes("modal")
        classes(*classes)
        id("_$id")
        tabIndex(-1)
        attr("aria-labelledby", "label$id")
        attr("aria-hidden", "true")
        attr("data-bs-backdrop", "static")
        attr("data-bs-keyboard", "false")
    }) {
        DomSideEffect { htmlDivElement: HTMLDivElement ->
            bsModal = Modal(htmlDivElement)
            htmlDivElement.addEventListener("hidePrevented.bs.modal", callback = { _ ->
                onDismissRequest()
            })
            bsModal?.show()
            onDispose {
                bsModal?.hide()
                bsModal?.dispose()
            }
        }

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
                        //attr("data-bs-dismiss", "modal")
                        attr("aria-label", "Close")
                        onClick {
                            onDismissRequest()
                        }
                    })
                }
                Div({ classes("modal-body") }, content)
                Div({ classes("modal-footer") }, footer)
            }
        }
    }
}
