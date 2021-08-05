@file:JsModule("bootstrap")
@file:JsNonModule
package app.softwork.bootstrapcompose

import org.w3c.dom.*

public external class Modal(element: HTMLDivElement) {
    public fun show(): Unit
    public fun hide(): Unit
    public fun dispose(): Unit
}

public external class Toast(element: HTMLDivElement) {
    public fun show(): Unit
    public fun hide(): Unit
    public fun dispose(): Unit

    public companion object {
        public fun getInstance(element: HTMLDivElement): Toast
    }
}