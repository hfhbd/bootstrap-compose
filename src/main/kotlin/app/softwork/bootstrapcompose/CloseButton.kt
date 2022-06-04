package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun CloseButton(
    disabled: Boolean = false,
    onClose: () -> Unit
) {
    Style
    Button({
        type(ButtonType.Button)
        classes("btn-close")
        attr("aria-label", "Close")
        if (disabled) {
            disabled()
        }
        onClick {
            onClose()
        }
    })
}
