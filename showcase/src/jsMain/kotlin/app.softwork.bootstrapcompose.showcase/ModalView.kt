package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ModalView() {
    var showModal by remember { mutableStateOf(false) }

    Box(styling = { Padding { All { size = SpacingSpecs.SpacingSize.Small } } }) {
        Button("Open Modal") {
            showModal = true
        }

        if (showModal) {
            Modal(
                "Header",
                onDismissRequest = { showModal = false }
            ) {
                Text("My modal content")
            }
        }
    }
}