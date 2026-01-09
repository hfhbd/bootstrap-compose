package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*
import kotlin.time.*
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.*

@Composable
public fun ToastContainer(
    toastContainerState: ToastContainerState,
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null
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
private fun Toast(message: ToastContainerState.ToastItem) {
    ToastJs
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
        DisposableEffect(message) {
            val htmlDivElement = scopeElement
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
                htmlDivElement.removeEventListener("shown.bs.toast", callback = {
                })
                htmlDivElement.removeEventListener("hidden.bs.toast", callback = {
                    message.remove()
                })
            }
        }
        message.header?.let { header ->
            Div(attrs = {
                classes("toast-header")
            }) {
                header()
                if (message.withDismissButton) {
                    DismissButton(attrs = message.dismissButtonAttrs)
                }
            }
        }

        if (message.header == null && message.withDismissButton) {
            Div(attrs = { classes("d-flex") }) {
                Div(attrs = { classes("toast-body") }, content = message.body)
                DismissButton(styling = {
                    Margins {
                        End {
                            size = SpacingSpecs.SpacingSize.Small
                        }
                        All {
                            size = SpacingSpecs.SpacingSize.Auto
                        }
                    }
                }) {
                    message.dismissButtonAttrs?.invoke(this)
                }
            }
        } else {
            Div(attrs = { classes("toast-body") }, content = message.body)
        }
    }
}

@Composable
private fun DismissButton(
    styling: (Styling.() -> Unit)? = null,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null
) {
    val style = styling?.let {
        Styling().apply(it).generate()
    }
    Button(attrs = {
        type(ButtonType.Button)
        classes("btn-close")
        attr("data-bs-dismiss", "toast")
        attr("aria-label", "close")
        if (style != null) {
            classes(classes = style)
        }
        attrs?.invoke(this)
    })
}

@OptIn(ExperimentalUuidApi::class)
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
     * @param withDismissButton Set to True to show a default dismiss button icon, and false to not.
     * You can also choose to include your own dismiss button in the [header] or [body] content
     * instead of in addition to the default.
     *
     * @return A function the caller can invoke to dismiss the toast, for example, if you provide your own
     * dismiss button in the header or body.
     */
    public fun showToast(
        withDismissButton: Boolean = true,
        delay: Duration = 5.seconds,
        toastAttrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
        dismissButtonAttrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
        header: ContentBuilder<HTMLDivElement>? = null,
        body: ContentBuilder<HTMLDivElement>
    ): () -> Unit {
        val uuid = Uuid.random()
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

    private fun removeToast(uuid: Uuid) {
        toasts.removeAll {
            it.uuid == uuid
        }
    }

    internal data class ToastItem(
        val uuid: Uuid,
        val delay: Duration,
        val withDismissButton: Boolean,
        val toastAttrs: (AttrsScope<HTMLDivElement>.() -> Unit)?,
        val dismissButtonAttrs: (AttrsScope<HTMLButtonElement>.() -> Unit)?,
        val header: ContentBuilder<HTMLDivElement>?,
        val body: ContentBuilder<HTMLDivElement>?,
        val remove: () -> Unit
    )
}
