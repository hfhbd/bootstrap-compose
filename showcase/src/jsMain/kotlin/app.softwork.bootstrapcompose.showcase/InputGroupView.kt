package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*
import app.softwork.bootstrapcompose.SpacingSpecs.SpacingSize.*

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
        Box(styling = { Margins { Bottom { size = Medium } } }) {
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
    var server by remember { mutableStateOf("") }

    Card("Basic example") {
        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                TextAddOn("@")
                TextInput(value = username, placeholder = "Username") { username = it.value }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                TextInput(value = username, placeholder = "Recipient's username") { }
                TextAddOn("@example.com")
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            Label(attrs = { classes(BSClasses.formLabel) }) { Text("Your vanity URL") }
            InputGroup {
                TextAddOn("https://example.com/users/")
                TextInput("") { }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                TextAddOn("$")
                TextInput("") { }
                TextAddOn(".00")
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                TextInput(username, placeholder = "Username") { username = it.value }
                TextAddOn("@")
                TextInput(server, placeholder = "Server") { server = it.value }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
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
        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup(
                size = InputGroupSize.Small,
            ) {
                TextAddOn("Small")
                TextInput { }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                TextAddOn("Default")
                TextInput { }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
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
        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                CheckboxAddOn(false) { }
                TextInput { }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
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
        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                TextInput { }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                TextInput("", placeholder = "Recipient's username") { }
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
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

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                TextInput("", placeholder = "Recipient's username") { }
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
        Box(styling = { Margins { Bottom { size = Medium } } }) {
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

        Box(styling = { Margins { Bottom { size = Medium } } }) {
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

        Box(styling = { Margins { Bottom { size = Medium } } }) {
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
        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                LabelAddOn("Options")
                SelectInput(false, onChange = { }) {
                    buildOptions()
                }
            }
        }
        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                SelectInput(false, onChange = { }) {
                    buildOptions()
                }
                LabelAddOn("Options")
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                SelectInput(false, onChange = { }) {
                    buildOptions()
                }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                SelectInput(false, onChange = { }) {
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
        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                LabelAddOn("Upload")
                FileInput { }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                FileInput { }
                LabelAddOn("Upload")
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
                FileInput { }
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                FileInput { }
                ButtonAddOn(
                    "Button",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {}
            }
        }

        Box(styling = { Margins { Bottom { size = Medium } } }) {
            InputGroup {
                var upload: HTMLInputElement? = null
                FileInput {
                    hidden()
                    ref {
                        upload = it
                        onDispose {

                        }
                    }
                }
                ButtonAddOn(
                    "Upload using hidden FileInput",
                    Color.Transparent,
                    ButtonType.Button,
                    attrs = { classes("btn-outline-secondary") }) {
                    upload!!.value = ""
                    upload!!.click()
                }
            }
        }
    }
}
