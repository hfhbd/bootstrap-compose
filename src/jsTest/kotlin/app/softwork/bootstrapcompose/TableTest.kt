package app.softwork.bootstrapcompose

import org.jetbrains.compose.web.dom.*
import kotlin.test.*

class TableTest {
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
            expected = "2",
            actual = root.innerHTML
        )
    }
}