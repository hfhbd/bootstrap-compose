package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.elements.*

@Composable
public fun Row(content: @Composable () -> Unit): Unit = Div(attrs = { classes("row") }) { content() }