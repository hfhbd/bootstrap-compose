package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.testutils.*
import kotlin.test.*

@ComposeWebExperimentalTestsApi
class TableTest {

    @Test
    fun variable() = runTest {
        val s: @Composable () -> Unit = @Composable { Text("a") }
        composition {
            s()
        }
        assertEquals("a", root.innerHTML)
    }

    class Holder {
        var s: @Composable () -> Unit = { }
    }

    @Test
    fun classTest() = runTest {
        val holder = Holder()
        holder.s = @Composable { Text("a") }
        composition {
            holder.s()
        }
        assertEquals("a", root.innerHTML)
    }

    class HolderLateinit {
        lateinit var s: @Composable () -> Unit
    }

    @Test
    fun classLateinitTest() = runTest {
        val holder = HolderLateinit()
        holder.s = @Composable { Text("a") }
        composition {
            holder.s()
        }
        assertEquals("a", root.innerHTML)
    }

    data class DataHolder(val s: @Composable () -> Unit)

    @Test
    fun dataClassTest() = runTest {
        val holder = DataHolder @Composable { Text("a") }
        composition {
            holder.s()
        }
        assertEquals("a", root.innerHTML)
    }

    @Test
    fun button() = runTest {
        composition {
            Button(title = "a") { }
        }
        assertEquals("""<button type="submit" class="btn btn-primary">a</button>""", root.innerHTML)
    }

    @Test
    fun placeholder() = runTest {
        composition {
            Input(type = InputType.Text) {
                placeholder("")
            }
        }
        assertEquals("""<input type="text" placeholder="">""", root.innerHTML)
    }

    @Test
    fun placeholderTextInput() = runTest {
        composition {
            TextInput {
                placeholder("")
            }
        }
        assertEquals("""<input type="text" placeholder="">""", root.innerHTML)
    }


    @Test
    fun table() = runTest {
        composition {
            Table(listOf("Foo", "Bar")) { _, it ->
                column("Title", header = Table.Header(attrs = { }) {
                    Row {
                        Column {
                            Text("Title")
                        }
                        Column(auto = true) {
                            Button(title = "Text") { }
                        }
                    }
                }) {
                    Text(it)
                }
                column("empty") {

                }
            }
        }

        assertEquals(
            expected = """<table class="table"><thead><tr><th scope="col"><div class="row"><div class="col">Title</div><div class="col col-auto"><button type="submit" class="btn btn-primary">Text</button></div></div></th><th scope="col">empty</th></tr></thead><tbody><tr><td>Foo</td><td></td></tr><tr><td>Bar</td><td></td></tr></tbody></table>""",
            actual = root.innerHTML
        )
    }

    @Test
    fun tableCalcButtonsTest() {
        val numberOfButtons = 5
        val pages = 11

        assertEquals(tableCalcButtons(0, pages = pages, numberOfButtons = numberOfButtons), 0..4)
        assertEquals(tableCalcButtons(0, pages = pages, numberOfButtons = numberOfButtons), 0..4)
        assertEquals(tableCalcButtons(2, pages = pages, numberOfButtons = numberOfButtons), 0..4)
        assertEquals(tableCalcButtons(3, pages = pages, numberOfButtons = numberOfButtons), 1..5)
        assertEquals(tableCalcButtons(4, pages = pages, numberOfButtons = numberOfButtons), 2..6)
        assertEquals(tableCalcButtons(5, pages = pages, numberOfButtons = numberOfButtons), 3..7)
        assertEquals(tableCalcButtons(6, pages = pages, numberOfButtons = numberOfButtons), 4..8)
        assertEquals(tableCalcButtons(7, pages = pages, numberOfButtons = numberOfButtons), 5..9)
        assertEquals(tableCalcButtons(8, pages = pages, numberOfButtons = numberOfButtons), 6..10)
        assertEquals(tableCalcButtons(9, pages = pages, numberOfButtons = numberOfButtons), 6..10)
        assertEquals(tableCalcButtons(10, pages = pages, numberOfButtons = numberOfButtons), 6..10)
    }
}
