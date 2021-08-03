package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ModalView() {
    var showModal by remember { mutableStateOf(false) }
    var items: List<String> by remember { mutableStateOf(listOf()) }

    Box(styling = { Padding { All { size = SpacingSpecs.SpacingSize.Small } } }) {
        Button("Open Modal") {
            showModal = true
        }

        if (showModal) {
            Modal(
                "Header",
                onDismissRequest = { showModal = false }
            ) {
                Button("add item") {
                    items = items + "Item ${items.size + 1}"
                }

                ListGroup {
                    items.forEach {
                        ListItem {
                            Text(it)
                        }
                    }
                }
            }
        }
    }
}