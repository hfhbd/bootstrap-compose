package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.dom.*


@Composable
fun FormView() {
    Container(fluid = true, attrs = {
        classes("border")
    }) {
        Row {
            Column(size = 4, attrs = { classes("border", "m-3", "p-2") }) {
                FormOverview()
            }
        }
    }

    Hr()
    CheckoutFormExample()
    Hr()
    HorizontalFormView()
}


@Composable
private fun FormOverview() {
    Form {
        var emailAddress by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var check by remember { mutableStateOf(false) }

        Div(attrs = { classes("mb-3") }) {
            FormLabel { Text("Email address") }
            InputGroup {
                TextInput(value = emailAddress, placeholder = "user@server.com", autocomplete = AutoComplete.email) {
                    emailAddress = it.value
                }
            }
            Div(attrs = { classes(BSClasses.formText) }) { Text("We'll never share your email with anyone else.") }
        }

        Div(attrs = { classes("mb-3") }) {
            FormLabel { Text("Password") }
            InputGroup {
                PasswordInput(value = password, autocomplete = AutoComplete.currentPassword) { password = it.value }
            }
        }

        Div(attrs = { classes("mb-3") }) {
            Checkbox(checked = check, label = "Check me out") { check = it }
        }

        Button("Submit") {
            console.info("submit")
        }
    }
}

@Composable
private fun CheckoutFormExample() {
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
        var nameOnCard by remember { mutableStateOf("") }
        var cardNumber by remember { mutableStateOf("") }
        var expiration by remember { mutableStateOf("") }
        var cvv by remember { mutableStateOf("") }

        Form {
            Row(attrs = { classes("g-3") }) {
                H4(attrs = { classes("mb-3") }) { Text("Billing address") }
                Column(size = 6, breakpoint = Breakpoint.Small) {
                    FormLabel { Text("First Name") }
                    InputGroup {
                        TextInput(
                            value = firstName,
                            placeholder = "",
                            autocomplete = AutoComplete.givenName
                        ) { firstName = it.value }
                    }
                }

                Column(size = 6, breakpoint = Breakpoint.Small) {
                    FormLabel { Text("Last Name") }
                    InputGroup {
                        TextInput(
                            value = lastName,
                            placeholder = "",
                            autocomplete = AutoComplete.familyName
                        ) { lastName = it.value }
                    }
                }

                Column(size = 12) {
                    FormLabel { Text("Username") }
                    InputGroup {
                        TextAddOn("@")
                        TextInput(
                            value = username,
                            autocomplete = AutoComplete.username,
                            placeholder = "Username"
                        ) { username = it.value }
                    }
                }

                Column(size = 12) {
                    FormLabel {
                        Text("Email ")
                        Span(attrs = { classes("text-muted") }) {
                            Text("(Optional)")
                        }
                    }
                    InputGroup {
                        TextInput(
                            value = email,
                            autocomplete = AutoComplete.email,
                            placeholder = "you@example.com"
                        ) { email = it.value }
                    }
                }

                Column(size = 12) {
                    FormLabel { Text("Address") }
                    InputGroup {
                        TextInput(
                            value = address,
                            autocomplete = AutoComplete.streetAddress,
                            placeholder = "1234 Main St"
                        ) { address = it.value }
                    }
                }

                Column(size = 12) {
                    FormLabel {
                        Text("Address 2 ")
                        Span(attrs = { classes("text-muted") }) {
                            Text("(Optional)")
                        }
                    }
                    InputGroup {
                        TextInput(
                            value = address2,
                            placeholder = "Apartment or suite"
                        ) { address2 = it.value }
                    }
                }

                Column(size = 5, breakpoint = Breakpoint.Medium) {
                    FormLabel(forId = "countryId") { Text("Country") }
                    Select(autocomplete = AutoComplete.countryName, onChange = { s -> println(s) }) {
                        Option(value = "") {
                            Text("Choose...")
                        }
                        Option(value = "1") {
                            Text("United States")
                        }
                    }
                }

                Column(size = 4, breakpoint = Breakpoint.Medium) {
                    FormLabel(forId = "stateId") { Text("State") }
                    Select(id = "stateId", autocomplete = AutoComplete.addressLevel1, onChange = { s -> println(s) }) {
                        Option(value = "") {
                            Text("Choose...")
                        }
                        Option(value = "1") {
                            Text("California")
                        }
                    }
                }

                Column(size = 3, breakpoint = Breakpoint.Medium) {
                    FormLabel { Text("Zip") }
                    InputGroup {
                        TextInput(value = zip, placeholder = "", autocomplete = AutoComplete.postalCode) {
                            zip = it.value
                        }
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
                        Radio(label = type.label, checked = paymentType == type) { paymentType = type }
                    }
                }
            }

            Row(attrs = { classes("gy-3") }) {
                Column(size = 6, breakpoint = Breakpoint.Medium) {
                    FormLabel { Text("Name on card") }
                    InputGroup {
                        TextInput(value = nameOnCard, placeholder = "John Doe", autocomplete = AutoComplete.ccName) {
                            nameOnCard = it.value
                        }
                    }
                    Small(attrs = { classes("text-muted") }) {
                        Text("Full name as displayed on card")
                    }
                }
                Column(size = 6, breakpoint = Breakpoint.Medium) {
                    FormLabel { Text("Credit card number") }
                    InputGroup {
                        TextInput(
                            value = cardNumber,
                            placeholder = "",
                            autocomplete = AutoComplete.ccNumber
                        ) { cardNumber = it.value }
                    }
                }
                Column(size = 3, breakpoint = Breakpoint.Medium) {
                    FormLabel { Text("Expiration") }
                    InputGroup {
                        TextInput(
                            value = expiration,
                            placeholder = "",
                            autocomplete = AutoComplete.ccExp
                        ) { expiration = it.value }
                    }
                }
                Column(size = 3, breakpoint = Breakpoint.Medium) {
                    FormLabel { Text("CVV") }
                    InputGroup {
                        TextInput(value = cvv, placeholder = "", autocomplete = AutoComplete.ccSecurityCode) {
                            cvv = it.value
                        }
                    }
                }
            }
            Hr(attrs = { classes("my-4") })
            Button(title = "Continue to checkout", attrs = { classes("w-100", "btn-primary", "btn-lg") }) {
            }
        }
    }
}


@Composable
private fun HorizontalFormView() {
    Card("Horizontal form") {
        Row(attrs = { classes("mb-3") }) {
            Label(attrs = { classes(BSClasses.colFormLabel, "col-sm-2") }) { Text("Email") }
            Div(attrs = { classes("col-sm-10") }) {
                InputGroup {
                    EmailInput { }
                }
            }
        }
        Row(attrs = { classes("mb-3") }) {
            Label(attrs = { classes(BSClasses.colFormLabel, "col-sm-2") }) { Text("Password") }
            Div(attrs = { classes("col-sm-10") }) {
                InputGroup {
                    PasswordInput { }
                }
            }
        }
        Fieldset(attrs = { classes("row", "mb-3") }) {
            Legend(attrs = { classes("col-form-label", "col-sm-2", "pt-0") }) { Text("Radios") }
            Div(attrs = { classes("col-sm-10") }) {
                RadioGroup {
                    Radio("First radio") { }
                    Radio("Second radio") { }
                    Radio("Third disabled radio", disabled = true) { }
                }
            }
        }
        Row(attrs = { classes("mb-3") }) {
            Column(attrs = { classes("offset-sm-2") }) {
                Checkbox(checked = false, label = "Example checkbox") {
                }
            }
        }
    }
}


private enum class PaymentType(val label: String) {
    Credit("Credit card"),
    Debit("Debit card"),
    Paypal("PayPal")
}
