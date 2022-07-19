package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import app.softwork.bootstrapcompose.Color
import kotlinx.coroutines.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun TableView() {
    Container {
        val todos = remember {
            mutableStateListOf(
                Todo("Learn Compose Web", finished = true),
                Todo("Create a demo", finished = false)
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
        Row {
            P {
                var newTitle by remember { mutableStateOf("") }
                Form(attrs = {
                    onSubmit {
                        it.preventDefault()
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
                            Button(
                                title = "Create",
                                disabled = newTitle.isBlank()
                            ) {
                            }
                        }
                    }
                }
            }
            val numberOfEntries = remember { mutableStateOf(3) }
            Table(
                pagination =
                Table.OffsetPagination(
                    data = filteredTodos,
                    entriesPerPageLimit = numberOfEntries,
                    actionNavigateBack = { currentPage, previousPage ->
                        println("go from ${currentPage.index} to ${previousPage.index}")
                    },
                    actionNavigateForward = { currentPage, previousPage ->
                        println("go from ${currentPage.index} to ${previousPage.index}")
                    }
                ),
                fixedHeader = Table.FixedHeaderProperty(topSize = 56.px, zIndex = ZIndex(800))
            ) { index, todo ->
                column(
                    "Index",
                    header = Table.Header(color = Color.Light), scope = Scope.Row,
                    verticalAlignment = Layout.VerticalAlignment.TextTop
                ) {
                    Text(index.toString())
                }
                column("Title", header = Table.Header(color = Color.Light)) {
                    Text(todo.title)
                }
                column(
                    "Finished",
                    header = Table.Header(Color.Dark) {
                        Div(attrs = { classes("d-flex", "justify-content-between") }) {
                            Text("Finished")
                            val text = if (filter) "Show finished" else "Hide finished"
                            Button(text) {
                                filter = !filter
                            }
                        }
                    },
                    cellColor = if (todo.finished) Color.Success else Color.Warning
                ) {
                    Column {
                        Text(todo.finished.toString())
                    }
                }
            }
        }

        Row {
            val scope = rememberCoroutineScope()
            val pagination = CalcPagination(scope)
            Table(pagination = pagination, caption = {
                Text("Table with custom pagination containing cell calculation")
            }) { index, i ->
                column("Index") {
                    Text("$index")
                }
                column("Some Calculation") {
                    Text("$i")
                }
            }
        }
    }
}

data class Todo(val title: String, val finished: Boolean)

private class CalcPagination(scope: CoroutineScope) : Table.Pagination<Int>, CoroutineScope by scope {

    override val startPageIndex = 0

    fun createPage(index: Int, size: Int) = Table.Pagination.Page(
        index = index,
        items = List(10) {
            max(it - 2 + it - 1, 0) * size
        },
        numberOfPages = size
    )

    suspend fun createPages(size: Int): List<Table.Pagination.Page<Int>> {
        delay(500.milliseconds)
        return List(size + 1) { index ->
            createPage(index, size + 1)
        }
    }

    override val position: Table.Pagination.Position = Table.Pagination.Position.Top

    override val pages: MutableState<List<Table.Pagination.Page<Int>>> =
        mutableStateOf(listOf(createPage(0, 1)))

    override val numberOfButtons: Int get() = 10

    override val entriesPerPageLimit: State<Int>? get() = null

    override val actionNavigateBack: ((CurrentPage<Int>, Table.Pagination.Page<Int>) -> Unit) =
        { _, previousPage ->
            launch {
                pages.value = createPages(previousPage.index)
            }
        }
    override val actionNavigateForward: ((CurrentPage<Int>, Table.Pagination.Page<Int>) -> Unit) =
        { currentPage, _ ->
            launch {
                pages.value = createPages(currentPage.index + 1)
            }
        }

    override val control: PageControl<Int> = custom()
}

private fun CalcPagination.custom(): PageControl<Int> = { pages, currentPage, goTo ->
    Pagination(size = PaginationSize.Small) {
        PageItem(disabled = currentPage.index == 0) {
            PageLink("<") {
                val previousIndex = currentPage.index - 1
                actionNavigateBack.invoke(currentPage, pages[previousIndex])
                goTo(previousIndex)
            }
        }

        PageItem(active = true, disabled = false) {
            PageLink(title = "${currentPage.index + 1}") { }
        }

        PageItem {
            PageLink(">") {
                val nextIndex = currentPage.index + 1
                actionNavigateForward.invoke(currentPage, currentPage)
                goTo(nextIndex)
            }
        }
    }
}
