package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ChecksAndRadiosView() {
    Container {
        Row {
            Column(size = 6) {
                var checkVal1 by remember { mutableStateOf(false) }
                var checkVal2 by remember { mutableStateOf(true) }
                Card(header = {
                    Text(value = "Checks")
                }) {
                    Checkbox(checkVal1, label = "Default checkbox") {
                        checkVal1 = it
                    }
                    Checkbox(checkVal2, label = "Checked checkbox") {
                        checkVal2 = it
                    }
                }
            }
            Column(size = 6) {
                Card(header = {
                    Text("Disabled Checks")
                }) {
                    Checkbox(checked = false, disabled = true, label = "Disabled checkbox") {}
                    Checkbox(checked = true, disabled = true, label = "Disabled checked checkbox") {}
                }
            }
        }

        Row {
            Column(size = 6) {
                Card(header = {
                    Text("Radios")
                }) {
                    var selectedIndex by remember { mutableStateOf(0) }
                    RadioGroup {
                        Radio("Default radio", checked = selectedIndex == 0) {
                            if (it) selectedIndex = 0
                        }
                        Radio("Default checked radio", checked = selectedIndex == 1) {
                            if (it) selectedIndex = 1
                        }
                    }
                }
            }
            Column(size = 6) {
                Card(header = {
                    Text("Disabled Radios")
                }) {
                    RadioGroup {
                        Radio("Disabled radio", checked = false, disabled = true) {}
                        Radio("Disabled checked radio", checked = true, disabled = true) {}
                    }
                }
            }
        }
        Row {
            Column(size = 4) {
                Card(header = {
                    Text("Switches")
                }) {
                    var checkVals by remember { mutableStateOf(listOf(false, false, false, false)) }
                    Checkbox(checkVals[0], label = "Default switch checkbox input", switch = true) {
                        checkVals = checkVals.toMutableList().apply { set(0, it) }
                    }
                    Checkbox(checkVals[1], label = "Checked switch checkbox input", switch = true) {
                        checkVals = checkVals.toMutableList().apply { set(1, it) }
                    }
                    Checkbox(
                        checkVals[2],
                        label = "Disabled switch checkbox input",
                        disabled = true,
                        switch = true
                    ) {
                        checkVals = checkVals.toMutableList().apply { set(2, it) }
                    }
                    Checkbox(
                        checkVals[3],
                        label = "Disabled checked switch checkbox input",
                        disabled = true,
                        switch = true
                    ) {
                        checkVals = checkVals.toMutableList().apply { set(3, it) }
                    }
                }
            }
            Column(size = 4) {
                var checkVals by remember { mutableStateOf(listOf(false, false, false)) }
                Card(header = {
                    Text("Inline Checks")
                }) {
                    Checkbox(checkVals[0], label = "1", inline = true) {
                        checkVals = checkVals.toMutableList().apply { set(0, it) }
                    }
                    Checkbox(checkVals[1], label = "2", inline = true) {
                        checkVals = checkVals.toMutableList().apply { set(1, it) }
                    }
                    Checkbox(checkVals[2], label = "3 (disabled)", disabled = true, inline = true) {
                        checkVals = checkVals.toMutableList().apply { set(2, it) }
                    }
                }
            }
            Column(size = 4) {
                Card(header = {
                    Text("Inline Radios")
                }) {
                    var selectedIndex by remember { mutableStateOf(0) }
                    RadioGroup {
                        Radio(checked = selectedIndex == 0, inline = true, label = "1") {
                            if (it) selectedIndex = 0
                        }
                        Radio(checked = selectedIndex == 1, inline = true, label = "2") {
                            if (it) selectedIndex = 1
                        }
                        Radio(
                            checked = selectedIndex == 2,
                            inline = true,
                            label = "3 (disabled)",
                            disabled = true
                        ) {
                            if (it) selectedIndex = 3
                        }
                    }
                }
            }
        }
    }
}
