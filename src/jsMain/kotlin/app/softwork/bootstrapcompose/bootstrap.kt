@file:JsModule("bootstrap")
@file:JsNonModule
package app.softwork.bootstrapcompose

import org.w3c.dom.*

internal external class Modal(element: HTMLDivElement) {
    internal fun show()
    internal fun hide()
    internal fun dispose()
}

internal external class Toast(element: HTMLDivElement) {
    internal fun show()
    internal fun hide()
    internal fun dispose()

    internal companion object {
        internal fun getInstance(element: HTMLDivElement): Toast
    }
} 
