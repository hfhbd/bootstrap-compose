package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

public object HrTag : Tag

// https://github.com/JetBrains/compose-jb/pull/741

@Composable
public fun Hr(attrs: AttrBuilderContext<HrTag> = {}) {
    TagElement<HrTag, HTMLHRElement>("hr", applyAttrs = attrs, content = null)
}