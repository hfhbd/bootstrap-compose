package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.Container
import app.softwork.bootstrapcompose.Range
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.max
import org.jetbrains.compose.web.attributes.min
import org.jetbrains.compose.web.attributes.step
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text

@Composable
fun RangeView() {
    Container {
        var example1 by remember { mutableStateOf(5) }
        Range(value = example1,
            onInput = { value, _ ->
                value?.let {
                    example1 = it.toInt()
                }
            }
        ) {
            Label { Text("Example range") }
        }
        Hr { }

        Range(
            value = 7,
            attrs = { disabled() },
            onInput = { _, _ -> }
        ) {
            Label {
                Text("Disabled range")
            }
        }
        Hr { }

        var minMax by remember { mutableStateOf(5) }
        Range(
            value = minMax,
            min = 0,
            max = 5,
            onInput = { v, _ -> v?.let { minMax = v.toInt() } }
        ) {
            Label {
                Text("Min and max range")
            }
        }
        Hr { }

        var step by remember { mutableStateOf(1.5) }
        Range(
            value = step,
            min = 0.0,
            max = 5.0,
            step = 0.5,
            onInput = { v, _ -> v?.let { step = v.toDouble() } }
        ) {
            Label {
                Text("Step by 0.5")
            }
        }
        Hr { }
    }
}
