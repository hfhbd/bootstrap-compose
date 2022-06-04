package app.softwork.bootstrapcompose.icons

import androidx.compose.runtime.*
import kotlinx.browser.*
import org.jetbrains.compose.web.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.svg.*
import org.w3c.dom.*
import org.w3c.dom.svg.*

public fun AttrsScope<SVGElement>.width(px: Number) {
    attr("width", px.toString())
}

public fun AttrsScope<SVGElement>.height(px: Number) {
    attr("height", px.toString())
}

public fun AttrsScope<SVGElement>.xmlns(nameSpace: String) {
    attr("xmlns", nameSpace)
}

public fun AttrsScope<SVGElement>.fill(color: String) {
    attr("fill", color)
}

public fun AttrsScope<SVGPathElement>.fillRule(fill: String) {
    attr("fill-rule", fill)
}

public fun AttrsScope<SVGRectElement>.rx(rx: Number) {
    attr("rx", rx.toString())
}

@Composable
@ExperimentalComposeWebSvgApi
public fun ElementScope<SVGElement>.Rect(
    width: Number,
    height: Number,
    rx: Number,
    transform: String,
    attrs: AttrBuilderContext<SVGRectElement>? = null,
    content: ContentBuilder<SVGRectElement>? = null
) {
    TagElement(
        elementBuilder = Rect,
        applyAttrs = {
            attr("width", width.toString())
            attr("height", height.toString())
            rx(rx)
            attr("transform", transform)
            attrs?.invoke(this)
        },
        content = content
    )
}

private val Rect = ElementBuilderNS<SVGRectElement>("rect", SVG_NS)

private open class ElementBuilderNS<TElement : Element>(private val tagName: String, private val namespace: String) :
    ElementBuilder<TElement> {
    private val el: Element by lazy { document.createElementNS(namespace, tagName) }
    override fun create(): TElement = el.cloneNode().unsafeCast<TElement>()
}
