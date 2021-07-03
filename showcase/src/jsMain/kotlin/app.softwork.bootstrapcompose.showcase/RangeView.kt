package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.Composable
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
        Range(value = 5,
            onInput = { _, _ -> }
        ) {
            Label {
                Text("Example range")
            }
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

        Range(
            value = 2,
            attrs = {
                min("0")
                max("5")
            },
            onInput = { _, _ -> }) {
            Label {
                Text("Min and max range")
            }
        }
        Hr { }

        Range(
            value = 1.5,
            attrs = {
                min("0")
                max("5")
                step(0.5)
            },
            onInput = { _, _ -> }) {
            Label {
                Text("Step by 0.5")
            }
        }
        Hr { }
    }
}
