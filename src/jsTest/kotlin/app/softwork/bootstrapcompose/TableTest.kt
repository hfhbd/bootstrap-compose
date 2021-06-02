package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import kotlin.test.*

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

    class HolderConstructor(val s: @Composable () -> Unit)


    @Test
    @Ignore // Constructor is not yet supported
    fun classConstructorTest() = runTest {
        val holder = HolderConstructor(@Composable { Text("a") })
        composition {
            holder.s()
        }
        assertEquals("a", root.innerHTML)
    }

    data class DataHolder(val s: @Composable () -> Unit)

    @Test
    @Ignore // Constructor is not yet supported
    fun dataClassTest() = runTest {
        val holder = DataHolder @Composable { Text("a") }
        composition {
            holder.s()
        }
        assertEquals("a", root.innerHTML)
    }


    @Test
    fun table() = runTest {
        composition {
            Table(listOf("Foo", "Bar")) {
                column("Title") {
                    Text(it)
                }
            }
        }

        assertEquals(
            expected = """<table class="table"><thead><tr><th scope="col">Title</th></tr></thead><tbody><tr><td>Foo</td></tr><tr><td>Bar</td></tr></tbody></table>""",
            actual = root.innerHTML
        )
    }
}