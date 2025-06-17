@file:JsModule("bootstrap")
@file:JsNonModule

package app.softwork.bootstrapcompose

import org.w3c.dom.*

internal external val Alert: dynamic
internal external val Collapse: dynamic

@JsName("Dropdown")
internal external val DropDownJs: dynamic

@JsName("Modal")
internal external val ModalJs: dynamic

@JsName("Offcanvas")
internal external val OffcanvasJs: dynamic

@JsName("Toast")
internal external val ToastJs: dynamic

internal external class Modal(element: HTMLDivElement) {
    internal fun show()
    internal fun hide()
    internal fun dispose()
}

internal external class Toast(element: HTMLDivElement) {
    internal fun show()
    internal fun dispose()
}

internal external class Offcanvas(element: HTMLDivElement) {
    internal fun show()
    internal fun hide()
}
