package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun Row(content: @Composable () -> Unit): Unit = Div(attrs = { classes("row") }) { content() }