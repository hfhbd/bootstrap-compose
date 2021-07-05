package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.dom.*

@Composable
fun InputGroupView() {
    BasicExampleView()
    Hr { }
    SizingView()
    Hr { }
    MutipleAddons()
    Hr { }
    CheckboxesAndRadiosView()
    Hr { }
    ButtonAddOnsView()
    Hr { }
    ButtonsWithDropdownsView()
    Hr { }
    CustomSelectView()
    Hr { }
    CustomFileInputView()
}

@Composable
fun MutipleAddons() {
    Card("Multiple addons") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("$")
                TextAddOn("0.00")
                TextInput { _, _ -> }
            }
        }

        InputGroup {
            TextInput { _, _ -> }
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
                TextInput(value = username, placeholder = "Username") { v, _ -> username = v }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Recipient's username") }) { _, _ -> }
                TextAddOn("@example.com")
            }
        }

        Div(attrs = { classes("mb-3") }) {
            Label(attrs = { classes(BSClasses.formLabel) }) { Text("Your vanity URL") }
            InputGroup {
                TextAddOn("https://example.com/users/")
                TextInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("$")
                TextInput { _, _ -> }
                TextAddOn(".00")
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Username") }) { _, _ -> }
                TextAddOn("@")
                TextInput(attrs = { placeholder("Server") }) { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("With textarea")
                TextAreaInput(value = "") { _ -> }
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
                TextInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextAddOn("Default")
                TextInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup(
                size = InputGroupSize.Large,
            ) {
                TextAddOn("Large")
                TextInput { _, _ -> }
            }
        }
    }
}

@Composable
fun CheckboxesAndRadiosView() {
    Card("Checkboxes and radios") {
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                CheckboxAddOn(false) { _ -> }
                TextInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                RadioAddOn(false) { _ -> }
                TextInput { _, _ -> }
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
                TextInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Recipient's username") }) { _, _ -> }
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
                TextInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput(attrs = { placeholder("Recipient's username") }) { _, _ -> }
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
                TextInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                TextInput { _, _ -> }
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
                TextInput { _, _ -> }
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
                SelectInput(onChange = { _ -> }) {
                    buildOptions()
                }
            }
        }
        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                SelectInput(onChange = { _ -> }) {
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
                SelectInput(onChange = { _ -> }) {
                    buildOptions()
                }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                SelectInput(onChange = { _ -> }) {
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
                FileInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                FileInput { _, _ -> }
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
                FileInput { _, _ -> }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            InputGroup {
                FileInput { _, _ -> }
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
            }
        }
    }
}