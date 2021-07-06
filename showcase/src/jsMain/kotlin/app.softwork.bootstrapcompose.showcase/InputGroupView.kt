package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
fun InputGroupView() {
    BasicExampleView()
    Hr()
    SizingView()
    Hr()
    MutipleAddons()
    Hr()
    CheckboxesAndRadiosView()
    Hr()
    ButtonAddOnsView()
    Hr()
    ButtonsWithDropdownsView()
    Hr()
    CustomSelectView()
    Hr()
    CustomFileInputView()
}

@Composable
fun MutipleAddons() {
    Card("Multiple addons") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("$")
                TextAddOn("0.00")
                TextInput { }
            }
        }

        InputGroup {
            TextInput { }
            TextAddOn("$")
            TextAddOn("0.00")
        }
    }
}

@Composable
private fun BasicExampleView() {
    var username by remember { mutableStateOf("") }
    var range by remember { mutableStateOf(0.5) }

    Card("Basic example") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("@")
                TextInput(value = username, placeholder = "Username") { username = it.value }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Recipient's username") }) { }
                TextAddOn("@example.com")
            }
        }

        Div(attrs = { classes("mb-3") }) {
            Label(attrs = { classes(BSClasses.formLabel) }) { Text("Your vanity URL") }
            InputGroup {
                TextAddOn("https://example.com/users/")
                TextInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("$")
                TextInput { }
                TextAddOn(".00")
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Username") }) { }
                TextAddOn("@")
                TextInput(attrs = { placeholder("Server") }) { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("With textarea")
                TextAreaInput(value = "") { }
            }
        }
    }
}

@Composable
fun SizingView() {
    Card("Sizing") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup(
                size = InputGroupSize.Small,
            ) {
                TextAddOn("Small")
                TextInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("Default")
                TextInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup(
                size = InputGroupSize.Large,
            ) {
                TextAddOn("Large")
                TextInput { }
            }
        }
    }
}

@Composable
fun CheckboxesAndRadiosView() {
    Card("Checkboxes and radios") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                CheckboxAddOn(false) { }
                TextInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                RadioAddOn(false) { }
                TextInput { }
            }
        }
    }
}

@Composable
fun ButtonAddOnsView() {
    Card("Button addons") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                TextInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Recipient's username") }) { }
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                TextInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Recipient's username") }) { }
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
            }
        }
    }
}

@Composable
private fun ButtonsWithDropdownsView() {
    fun DropDownBuilder.makeDropDown() {
        Button("Action") {}
        Button("Another action") {}
        Button("Something else here") {}
        Divider()
        Button("Separated link") {}
    }

    Card("Buttons with dropdowns") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                //TODO DropDown doesn't support adding btn-outline-secondary class
                DropDownAddOn(
                    "Dropdown",
                    color = Color.Light
                ) {
                    makeDropDown()
                }
                TextInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput { }
                DropDownAddOn(
                    "Dropdown",
                    color = Color.Light
                ) {
                    makeDropDown()
                }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                DropDownAddOn(
                    "Dropdown",
                    color = Color.Light
                ) {
                    makeDropDown()
                }
                TextInput { }
                DropDownAddOn(
                    "Dropdown",
                    color = Color.Light
                ) {
                    makeDropDown()
                }
            }
        }
    }
}

@Composable
private fun CustomSelectView() {
    //Creates the select options that are reused in many examples
    @Composable
    fun SelectContext.buildOptions() {
        Option(value = "", selected = true) { Text("Choose...") }
        Option(value = "1") { Text("One") }
        Option(value = "2") { Text("Two") }
        Option(value = "3") { Text("Three") }
    }

    Card("Custom select") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                LabelAddOn("Options")
                SelectInput(onChange = { }) {
                    buildOptions()
                }
            }
        }
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                SelectInput(onChange = { }) {
                    buildOptions()
                }
                LabelAddOn("Options")
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                SelectInput(onChange = { }) {
                    buildOptions()
                }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                SelectInput(onChange = { }) {
                    buildOptions()
                }
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
            }
        }
    }
}

@Composable
private fun CustomFileInputView() {
    Card("Custom file input") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                LabelAddOn("Upload")
                FileInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                FileInput { }
                LabelAddOn("Upload")
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                FileInput { }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                FileInput { }
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
            }
        }
    }
}
