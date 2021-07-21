package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import kotlinx.browser.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
fun RawInputView() {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Container {
        Form(attrs = {
            onSubmit {
                it.preventDefault()
                window.alert("Hello $name")
                name = ""
                password = ""
            }
        }) {
            Row {
                Column {
                    Input(value = name, placeholder = "John Doe", label = "Name", type = InputType.Text) {
                        name = it.value
                    }
                }
            }
            Row {
                Column {
                    Input(value = password, placeholder = "*****", label = "Password", type = InputType.Password) {
                        password = it.value
                    }
                }
            }
            Row {
                Column {
                    Button(title = "Login", attrs = {
                        if (name.isBlank() || password.isBlank()) {
                            disabled()
                        }
                    }) { }
                }
            }
        }
    }
}
