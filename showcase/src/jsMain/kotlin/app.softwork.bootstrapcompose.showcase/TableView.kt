package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*


data class Todo(val title: String, val finished: Boolean)

@Composable
fun TableView() {
    var todos by remember {
        mutableStateOf(
            listOf(
                Todo("Learn Compose Web", finished = true),
                Todo("Create a demo", finished = false)
            )
        )
    }

    var filter by remember { mutableStateOf(false) }

    val filteredTodos = todos.filter {
        if (filter) {
            !it.finished
        } else {
            true
        }
    }
    Container {
        P {
            var newTitle by remember { mutableStateOf("") }
            Form(attrs = {
                onSubmit {
                    todos += Todo(title = newTitle, finished = false)
                    newTitle = ""
                }
            }) {
                Div {
                    FormLabel { Text("New Todo Title: $newTitle") }
                    InputGroup {
                        TextInput(
                            value = newTitle,
                            placeholder = "Title",
                        ) {
                            newTitle = it.value
                        }
                        Button("Create", attrs = {
                            if (newTitle.isBlank()) {
                                disabled()
                            }
                        }) {
                        }
                    }
                }
            }
        }
        Table(filteredTodos) { index, todo ->
            column("Index") {
                Text(index.toString())
            }
            column("Title") {
                Text(todo.title)
            }
            column("Finished", color = if (todo.finished) Color.Success else Color.Warning, action = {
                val text = if (filter) "Show finished" else "Hide finished"
                Button(text) {
                    filter = !filter
                }
            }) {
                Text(todo.finished.toString())
            }
        }
    }
}
