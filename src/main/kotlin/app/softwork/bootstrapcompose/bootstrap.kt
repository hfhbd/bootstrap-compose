@file:JsModule("bootstrap")

package app.softwork.bootstrapcompose

import org.w3c.dom.*

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

internal external val needsJS: dynamic
