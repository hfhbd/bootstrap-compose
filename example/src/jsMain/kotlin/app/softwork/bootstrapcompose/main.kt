package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.*
import org.jetbrains.compose.web.dom.*

private external fun require(module: String): dynamic

public data class Todo(val title: String, val finished: Boolean) {
    internal companion object {
        internal val samples = listOf(
            Todo("Learn Compose Web", finished = true),
            Todo("Create a demo", finished = false)
        )
    }
}

public fun main() {
    require("bootstrap/dist/css/bootstrap.css")
    require("bootstrap/dist/js/bootstrap.bundle.min.js")

    renderComposableInBody {
        Navbar(
            fluid = true,
            colorScheme = Color.Dark,
            backgroundColor = Color.Dark,
            breakpoint = Breakpoint.Large,
            brand = {
                Brand {
                    Text("Bootstrap Compose")
                }
            }
        ) {
            NavbarLink(active = true, link = "/#") {
                Text("Home")
            }
            NavbarLink(active = false, link = "/#foo") {
                Text("Foo")
            }
            NavbarLink(active = false, disabled = true, link = "/#bar") {
                Text("Bar")
            }
        }
        Main {
            Container {
                TodoTable()
            }
        }
    }
}

@Composable
private fun TodoTable() {
    var filter by remember { mutableStateOf(false) }

    val todos = Todo.samples.filter {
        if (filter) {
            !it.finished
        } else {
            true
        }
    }

    Table(todos) { index, todo ->
        column("Index") {
            Text(index.toString())
        }
        column("Title") {
            Text(todo.title)
        }
        column("Finished", color = if (todo.finished) Color.Success else Color.Warning, action = {
            val text = if(filter) "Show finished" else "Hide finished"
            Button(text) {
                filter = !filter
            }
        }) {
            Text(todo.finished.toString())
        }
    }
}
