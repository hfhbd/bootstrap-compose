package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

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

@Composable
private fun Toast(message: ToastContainerState.ToastItem<ContentBuilder<HTMLDivElement>>) {
    Div(
        attrs = {
            classes("toast")
            attr("role", "alert")
            attr("aria-live", "assertive")
            attr("aria-atomic", "true")
            message.toastAttrs?.invoke(this)
        }
    ) {
        DomSideEffect { htmlDivElement ->
            val bsToast = Toast(htmlDivElement)
            htmlDivElement.addEventListener("shown.bs.toast", callback = { _ ->
            })
            htmlDivElement.addEventListener("hidden.bs.toast", callback = { _ ->
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
                DismissButton() {
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

public class ToastContainerState() {
    internal var toasts by mutableStateOf<List<ToastItem<ContentBuilder<HTMLDivElement>>>>(listOf())

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
        toastAttrs: (AttrsBuilder<HTMLDivElement>.() -> Unit)? = null,
        dismissButtonAttrs: (AttrsBuilder<HTMLButtonElement>.() -> Unit)? = null,
        header: ContentBuilder<HTMLDivElement>? = null,
        body: ContentBuilder<HTMLDivElement>
    ): () -> Unit {
        val uuid = UUID()
        val toastItem = ToastItem(
            uuid,
            withDismissButton,
            toastAttrs,
            dismissButtonAttrs,
            header = header,
            body = body
        ) {
            removeToast(uuid)
        }
        toasts += toastItem
        return { removeToast(uuid) }
    }

    private fun removeToast(uuid: UUID) {
        toasts = toasts.filter {
            it.uuid != uuid
        }
    }

    //Type parameter needed to get around https://github.com/JetBrains/compose-jb/issues/746
    internal data class ToastItem<T>(
        val uuid: UUID,
        val withDismissButton: Boolean,
        val toastAttrs: (AttrsBuilder<HTMLDivElement>.() -> Unit)?,
        val dismissButtonAttrs: (AttrsBuilder<HTMLButtonElement>.() -> Unit)?,
        val header: T?,
        val body: T?,
        val remove: () -> Unit
    )
}