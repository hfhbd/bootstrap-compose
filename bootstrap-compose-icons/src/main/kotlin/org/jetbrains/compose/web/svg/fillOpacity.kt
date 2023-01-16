package org.jetbrains.compose.web.svg

import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.*
import org.w3c.dom.svg.SVGElement

public fun AttrsScope<SVGElement>.fillOpacity(fill: Number) {
    attr("fill-opacity", fill.toString())
}

public fun AttrsScope<SVGElement>.fillOpacity(fill: CSSPercentageValue) {
    attr("fill-opacity", fill.toString())
}
