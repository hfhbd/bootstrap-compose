package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.dom.*

@Composable
fun AlertView() {
    Color.values().forEach {
        var show by remember { mutableStateOf(true) }
        if (show) {
            Alert(it) {
                Text("A simple alert—check it out!")
                Link("#") {
                    Text("Example link")
                }
                CloseButton {
                    show = false
                }
            }
        }
    }
}
