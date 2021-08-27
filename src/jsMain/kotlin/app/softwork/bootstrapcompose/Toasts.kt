package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*
import kotlin.time.*

@Composable
public fun ToastContainer(
    toastContainerState: ToastContainerState,
    attrs: (AttrsBuilder<HTMLDivElement>.() -> Unit)? = null
) {
    Div(attrs = {
        classes("toast-container")
        attrs?.invoke(this)
    }) {
        toastContainerState.toasts.forEach { toastItem ->
            key(toastItem) {
                Toast(toastItem)
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun Toast(message: ToastContainerState.ToastItem) {
    Div(
        attrs = {
            classes("toast")
            attr("role", "alert")
            attr("data-bs-delay", message.delay.inWholeMilliseconds.toString())
            attr("aria-live", "assertive")
            attr("aria-atomic", "true")
            message.toastAttrs?.invoke(this)
        }
    ) {
        DomSideEffect { htmlDivElement ->
            val bsToast = Toast(htmlDivElement)
            htmlDivElement.addEventListener("shown.bs.toast", callback = {
            })
            htmlDivElement.addEventListener("hidden.bs.toast", callback = {
                message.remove()
            })
            bsToast.show()
            onDispose {
                bsToast.dispose()
                message.remove()
            }
        }
        message.header?.let { header ->
            Div(attrs = {
                classes("toast-header")
            }) {
                header()
                if (message.withDismissButton) {
                    DismissButton(message.dismissButtonAttrs)
                }
            }
        }

        if (message.header == null && message.withDismissButton) {
            Div(attrs = { classes("d-flex") }) {
                Div(attrs = { classes("toast-body") }, content = message.body)
                DismissButton {
                    message.dismissButtonAttrs?.invoke(this)
                    classes("me-2", "m-auto")
                }
            }
        } else {
            Div(attrs = { classes("toast-body") }, content = message.body)
        }
    }
}

@Composable
private fun DismissButton(attrs: (AttrsBuilder<HTMLButtonElement>.() -> Unit)? = null) {
    Button(attrs = {
        type(ButtonType.Button)
        classes("btn-close")
        attr("data-bs-dismiss", "toast")
        attr("aria-label", "close")
        attrs?.invoke(this)
    })
}

@OptIn(ExperimentalTime::class)
public class ToastContainerState {
    internal val toasts = mutableStateListOf<ToastItem>()

    public fun showToast(toastMessage: String) {
        showToast(body = {
            Text(toastMessage)
        })
    }

    /**
     * Shows a new Toast.
     * @param header Composable to generate the header content
     * @param body Composable to generate the body content
     * @param withDismissButton Set to True to show a default dismiss button icon, and false to not. You can also choose to
     * include your own dismiss button in the [header] or [body] content instead of in addition to the default.
     *
     * @return A function the caller can invoke to dismiss the toast, for example, if you provide your own
     * dismiss button in the header or body.
     */
    public fun showToast(
        withDismissButton: Boolean = true,
        delay: Duration = Duration.seconds(5),
        toastAttrs: (AttrsBuilder<HTMLDivElement>.() -> Unit)? = null,
        dismissButtonAttrs: (AttrsBuilder<HTMLButtonElement>.() -> Unit)? = null,
        header: ContentBuilder<HTMLDivElement>? = null,
        body: ContentBuilder<HTMLDivElement>
    ): () -> Unit {
        val uuid = UUID()
        val toastItem = ToastItem(
            uuid,
            delay = delay,
            withDismissButton,
            toastAttrs,
            dismissButtonAttrs,
            header = header,
            body = body
        ) {
            removeToast(uuid)
        }
        toasts.add(toastItem)
        return { removeToast(uuid) }
    }

    private fun removeToast(uuid: UUID) {
        toasts.removeAll {
            it.uuid == uuid
        }
    }

    internal data class ToastItem(
        val uuid: UUID,
        val delay: Duration,
        val withDismissButton: Boolean,
        val toastAttrs: (AttrsBuilder<HTMLDivElement>.() -> Unit)?,
        val dismissButtonAttrs: (AttrsBuilder<HTMLButtonElement>.() -> Unit)?,
        val header: ContentBuilder<HTMLDivElement>?,
        val body: ContentBuilder<HTMLDivElement>?,
        val remove: () -> Unit
    )
}
