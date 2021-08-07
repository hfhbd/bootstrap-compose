@file:JsModule("bootstrap")
@file:JsNonModule
package app.softwork.bootstrapcompose

import org.w3c.dom.*

public external class Modal(element: HTMLDivElement) {
    public fun show()
    public fun hide()
    public fun dispose()
}
