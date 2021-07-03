package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.*


@Composable
fun FormView() {
    Container(fluid = true, attrs = {
        classes("border")
    }) {
        Row() {
            Column(size = 4, attrs = { classes("border", "m-3", "p-2") }) {
                Form {
                    var emailAddress by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    Div(attrs = {classes("mb-3")}){
                        InputGroup(
                            value = emailAddress,
                            type = InputType.Text,
                            onInput = { value, _ -> emailAddress = value }
                        ) {
                            Label() {
                                Text("Email address")
                            }
                            Help() {
                                Text("We'll never share your email with anyone else.")
                            }
                        }
                    }

                    Div(attrs = {classes("mb-3")}) {
                        InputGroup(
                            value = password,
                            type = InputType.Password,
                            onInput = { value, _ -> password = value }
                        ) {
                            Label() {
                                Text("Password")
                            }
                        }
                    }

                    Div(attrs = {classes("mb-3")}) {
                        Checkbox(checked = false, label = "Check me out") {}
                    }

                    Button("Submit") {
                        console.info("submit")
                    }
                }
            }
        }
    }

    Hr {}

    Container(fluid = false, attrs = {
        classes("border")
    }) {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var address2 by remember { mutableStateOf("") }
        var zip by remember { mutableStateOf("") }

        org.jetbrains.compose.web.dom.Form {
            Row(attrs = { classes("g-3") }) {
                H4(attrs = { classes("mb-3") }) { Text("Billing address") }
                Column(size = 6, breakpoint = Breakpoint.Small) {
                    InputGroup(
                        value = firstName,
                        type = InputType.Text,
                        onInput = { value, _ -> firstName = value }
                    ) {
                        Label(
                            attrs = {
                                classes("xyz123")
                            },
                            content = {
                                Text("First Name")
                            }
                        )
                    }
                }

                Column(size = 6, breakpoint = Breakpoint.Small) {
                    InputGroup(
                        value = lastName,
                        type = InputType.Text,
                        onInput = { value, _ -> lastName = value }
                    ) {
                        Label {
                            Text("Last Name")
                        }
                    }
                }

                Column(size = 12) {
                    InputGroup(
                        value = username,
                        type = InputType.Text,
                        placeholder = "Username",
                        onInput = { value, _ -> username = value }
                    ) {
                        Label {
                            Text("Username")
                        }
                        LeftAddOn {
                            Text("@")
                        }
                    }
                }

                Column(size = 12) {
                    InputGroup(
                        value = email,
                        type = InputType.Text,
                        placeholder = "you@example.com",
                        onInput = { value, _ -> email = value }
                    ) {
                        Label {
                            Text("Email ")
                            Span(attrs = { classes("text-muted") }) {
                                Text("(Optional)")
                            }
                        }
                    }
                }

                Column(size = 12) {
                    InputGroup(
                        value = address,
                        type = InputType.Text,
                        placeholder = "1234 Main St",
                        onInput = { value, _ -> address = value }
                    ) {
                        Label {
                            Text("Address")
                        }
                    }
                }

                Column(size = 12) {
                    InputGroup(
                        value = address2,
                        type = InputType.Text,
                        placeholder = "Apartment or suite",
                        onInput = { value, _ -> address2 = value }
                    ) {
                        Label {
                            Text("Address 2 ")
                            Span(attrs = { classes("text-muted") }) {
                                Text("(Optional)")
                            }
                        }
                    }
                }

                Column(size = 5, breakpoint = Breakpoint.Medium) {
                    Select(onChange = { s -> println(s) }) {
                        Label {
                            Text("Country")
                        }
                        Option(value = "") {
                            Text("Choose...")
                        }
                        Option(value = "1") {
                            Text("United States")
                        }
                    }
                }
                Column(size = 4, breakpoint = Breakpoint.Medium) {
                    Select(onChange = { s -> println(s) }) {
                        Label {
                            Text("State")
                        }
                        Option(value = "") {
                            Text("Choose...")
                        }
                        Option(value = "1") {
                            Text("California")
                        }
                    }
                }
                Column(size = 3, breakpoint = Breakpoint.Medium) {
                    InputGroup(value = zip, type = InputType.Text, onInput = { value, _ -> zip = value }) {
                        Label { Text("Zip") }
                    }
                }
            }
            Hr(attrs = { classes("my-4") })
            var shippingAddress by remember { mutableStateOf(false) }
            Checkbox(
                checked = shippingAddress,
                onClick = { value -> shippingAddress = value },
                label = "Shipping address is the same as my billing address"
            )
            var saveForNextTime by remember { mutableStateOf(false) }
            Checkbox(
                checked = saveForNextTime,
                onClick = { value -> saveForNextTime = value },
                label = "Save this information for next time"
            )
            Hr(attrs = { classes("my-4") })

            H4(attrs = { classes("mb-3") }) {
                Text("Payment")
            }

            var paymentType by remember { mutableStateOf(PaymentType.Credit) }
            Div(attrs = { classes("my-3") }) {
                RadioGroup {
                    PaymentType.values().forEach { type ->
                        Radio(label = type.label) { paymentType = type }
                    }
                }
            }

            Row(attrs = { classes("gy-3") }) {
                Column(size = 6, breakpoint = Breakpoint.Medium) {
                    var nameOnCard by remember { mutableStateOf("") }
                    InputGroup(value = nameOnCard, type = InputType.Text, onInput = { value, _ ->
                        nameOnCard = value
                    }) {
                        Label() { Text("Name on card") }
                        Help {
                            Text("Full name as displayed on card")
                        }
                    }
                }
                Column(size = 6, breakpoint = Breakpoint.Medium) {
                    var cardNumber by remember { mutableStateOf("") }
                    InputGroup(value = cardNumber, type = InputType.Text, onInput = { value, _ ->
                        cardNumber = value
                    }) {
                        Label() { Text("Credit card number") }
                    }
                }
                Column(size = 3, breakpoint = Breakpoint.Medium) {
                    var expiration by remember { mutableStateOf("") }
                    InputGroup(
                        value = expiration,
                        type = InputType.Text,
                        onInput = { value, _ -> expiration = value }) {
                        Label { Text("Expiration") }
                    }
                }
                Column(size = 3, breakpoint = Breakpoint.Medium) {
                    var cvv by remember { mutableStateOf("") }
                    InputGroup(value = cvv, type = InputType.Text, onInput = { value, _ -> cvv = value }) {
                        Label { Text("CVV") }
                    }
                }
            }
            Hr(attrs = { classes("my-4") })
            Button(title = "Continue to checkout", attrs = { classes("w-100", "btn-primary", "btn-lg") }) {
            }
        }
    }
}

private enum class PaymentType(val label: String) {
    Credit("Credit card"), Debit("Debit card"), Paypal("PayPal")
}