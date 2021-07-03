package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.Composable
import app.softwork.bootstrapcompose.*

@Composable
fun ChecksAndRadiosView() {

    Container {
        Row {
            Column(size = 6) {
                Card("Checks") {
                    Checkbox(false, label = "Default checkbox") {
                    }
                    Checkbox(true, label = "Checked checkbox") {
                    }
                }
            }
            Column(size = 6) {
                Card("Disabled Checks") {
                    Checkbox(checked = false, disabled = true, label = "Disabled checkbox") {}
                    Checkbox(checked = true, disabled = true, label = "Disabled checked checkbox") {}
                }
            }
        }

        Row {
            Column(size = 6) {
                Card("Radios") {
                    RadioGroup {
                        Radio("Default radio") {}
                        Radio("Default checked radio", checked = true) {}
                    }
                }
            }
            Column(size = 6) {
                Card("Disabled Radios") {
                    RadioGroup() {
                        Radio("Disabled radio", disabled = true) {}
                        Radio("Disabled checked radio", checked = true, disabled = true) {}
                    }
                }
            }
        }
        Row {
            Column(size = 4) {
                Card("Switches") {
                    Checkbox(false, label = "Default switch checkbox input", switch = true) {}
                    Checkbox(true, label = "Checked switch checkbox input", switch = true) {}
                    Checkbox(false, label = "Disabled switch checkbox input", disabled = true, switch = true) {}
                    Checkbox(true, label = "Disabled checked switch checkbox input", disabled = true, switch = true) {}
                }
            }
            Column(size = 4) {
                Card("Inline Checks") {
                    Checkbox(false, label = "1", inline = true) {}
                    Checkbox(false, label = "2", inline = true) {}
                    Checkbox(false, label = "3 (disabled)", disabled = true, inline = true) {}
                }
            }
            Column(size = 4) {
                Card("Inline Radios") {
                    RadioGroup {
                        Radio(checked = false, inline = true, label = "1") {}
                        Radio(checked = false, inline = true, label = "2") {}
                        Radio(checked = false, inline = true, label = "3 (disabled)", disabled = true) {}
                    }
                }
            }
        }
    }
}