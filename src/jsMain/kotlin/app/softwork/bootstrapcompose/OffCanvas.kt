package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

/**
 * State for [Offcanvas] composable component. Provides functions to call
 * into Bootstrap's Javascript to show/hide the Offcanvas element.
 */
public class OffcanvasState {
    internal var bsOffcanvas: Offcanvas? = null

    /**
     * Shows the Offcanvas component.
     */
    public fun show() {
        bsOffcanvas?.apply {
            show()
        }
    }

    /**
     * Hides the Offcanvas component.
     */
    public fun hide() {
        bsOffcanvas?.apply {
            hide()
        }
    }
}


/**
 * Creates an Offcanvas component.
 *
 * @param placement The location for the Offcanvas component.
 * @param offcanvasState Control for showing/hiding the Offcanvas component.
 * @param headerContent Composable to render content for the header. A close button will always
 * be provided on the right.
 * @param showHeaderCloseButton If true the default Bootstrap close button will be shown on the right side of the
 * header. If false, the user should provide their own control for closing the Offcanvas component.
 * @param bodyContent Composable to render content for the body of the Offcanvas component.
 */
@Composable
public fun Offcanvas(
    placement: OffcanvasPlacement,
    offcanvasState: OffcanvasState = remember { OffcanvasState() },
    headerContent: ContentBuilder<HTMLDivElement>? = null,
    showHeaderCloseButton: Boolean = true,
    bodyContent: ContentBuilder<HTMLDivElement>? = null
) {
    Div(attrs = {
        classes("offcanvas", placement.toString())
        tabIndex(-1)
    }) {
        DisposableRefEffect { htmlDivElement ->
            offcanvasState.bsOffcanvas = Offcanvas(htmlDivElement)
            onDispose {
                offcanvasState.bsOffcanvas?.hide()
                offcanvasState.bsOffcanvas = null
            }
        }

        Div(attrs = {
            classes("offcanvas-header")
        }) {
            headerContent?.invoke(this)
            if (showHeaderCloseButton) {
                Button(attrs = {
                    type(ButtonType.Button)
                    classes("btn-close", "text-reset")
                    attr("aria-label", "Close")
                    onClick {
                        offcanvasState.bsOffcanvas?.hide()
                    }
                })
            }
        }
        Div(attrs = { classes("offcanvas-body") }, content = bodyContent)
    }
}

/**
 * Used to specify the location of the Offcanvas component.
 */
public enum class OffcanvasPlacement(private val value: String) {
    START("start"),
    END("end"),
    TOP("top"),
    BOTTOM("bottom");

    public override fun toString(): String {
        return "offcanvas-$value"
    }
}
