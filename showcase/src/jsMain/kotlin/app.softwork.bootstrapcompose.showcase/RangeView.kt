package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.Container
import app.softwork.bootstrapcompose.FormLabel
import app.softwork.bootstrapcompose.Range
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text

@Composable
fun RangeView() {
    Container {
        var example1 by remember { mutableStateOf(5) }
        val example1Id = "ex1"
        FormLabel(forId = example1Id) { Text("Example range") }
        Range(value = example1,
            id = example1Id,
            onInput = { value, _ ->
                value?.let {
                    example1 = it.toInt()
                }
            }
        )
        Hr { }

        val example2Id = "ex2"
        FormLabel(forId = example2Id) { Text("Disabled range") }
        Range(
            value = 7,
            disabled = true,
            id = example2Id,
            onInput = { _, _ -> }
        )
        Hr { }

        var minMax by remember { mutableStateOf(5) }
        val example3Id = "ex3"
        FormLabel(forId = example3Id) { Text("Min and max range") }
        Range(
            value = minMax,
            min = 0,
            max = 5,
            id = example3Id,
            onInput = { v, _ -> v?.let { minMax = v.toInt() } }
        )
        Hr { }

        var step by remember { mutableStateOf(1.5) }
        val example4Id = "ex4"
        FormLabel(forId = example4Id) { Text("Step by 0.5") }
        Range(
            value = step,
            min = 0.0,
            max = 5.0,
            step = 0.5,
            id = example4Id,
            onInput = { v, _ -> v?.let { step = v.toDouble() } }
        )
        Hr { }
    }
}
