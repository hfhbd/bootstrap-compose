package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.Composable
import app.softwork.bootstrapcompose.*
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.*

@Composable
fun NavbarView() {
    Hr()

    navBarExpand(NavbarCollapseBehavior.Always, true, "Never expand")
    Hr()

    navBarExpand(NavbarCollapseBehavior.Never, true, "Always expand")
    Hr()

    navBarExpand(NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Small), true, "Expand at sm")
    Hr()

    navBarExpand(NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Medium), true, "Expand at md")
    Hr()

    navBarExpand(NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large), true, "Expand at lg")
    Hr()

    navBarExpand(NavbarCollapseBehavior.AtBreakpoint(Breakpoint.ExtraLarge), true, "Expand at xl")
    Hr()

    navBarExpand(NavbarCollapseBehavior.AtBreakpoint(Breakpoint.ExtraExtraLarge), true, "Expand at xxl")
    Hr()

    navBarExpand(NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large), false, "Container")
    Hr()

    containerXL()
    Container(fluid = false, type = Breakpoint.ExtraLarge, attrs = { classes("mb-4") }) {
        Text("Matching .container-xl...")
    }
    Hr()

    centeredNavOnly()
    Hr()

    lightNavbar()
    Hr()

    lightCenteredNavbar()
    Hr()
}

@Composable
private fun navBarExpand(collapseBehavior: NavbarCollapseBehavior, fluid: Boolean, title: String) {
    Navbar(
        collapseBehavior = collapseBehavior,
        colorScheme = Color.Dark,
        backgroundColor = Color.Dark,
        fluid = fluid
    ) {
        Brand {
            Text(title)
        }

        val togglerTargetId = "toggler${UUID()}"

        Toggler(
            target = togglerTargetId,
            controls = togglerTargetId
        )
        NavbarCollapse(togglerTargetId) {
            NavbarNav(attrs = { classes("me-auto") }) {
                NavbarLink(active = true, link = "#") { Text("Home") }
                NavbarLink(active = false, link = "#") { Text("Link") }
                NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
                NavbarDropDown("Dropdown", href = "#") {
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Something else here") } }
                }
            }

            org.jetbrains.compose.web.dom.Form(attrs = { classes("m-0") }) {
                Input(type = InputType.Text, attrs = {
                    classes("form-control")
                    attr("placeholder", "Search")
                })
            }
        }
    }
}

@Composable
private fun containerXL() {
    Navbar(
        collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
        colorScheme = Color.Dark,
        backgroundColor = Color.Dark,
        fluid = false,
        containerBreakpoint = Breakpoint.ExtraLarge
    ) {
        Brand {
            Text("Container XL")
        }

        val togglerTargetId = "toggler${UUID()}"

        Toggler(
            target = togglerTargetId,
            controls = togglerTargetId
        )
        NavbarCollapse(togglerTargetId) {
            NavbarNav(attrs = { classes("me-auto") }) {
                NavbarLink(active = true, link = "#") { Text("Home") }
                NavbarLink(active = false, link = "#") { Text("Link") }
                NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
                NavbarDropDown("Dropdown", href = "#") {
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Something else here") } }
                }
            }

            org.jetbrains.compose.web.dom.Form(attrs = { classes("m-0") }) {
                Input(type = InputType.Text, attrs = {
                    classes("form-control")
                    attr("placeholder", "Search")
                })
            }
        }
    }
}

@Composable
private fun centeredNavOnly() {
    Navbar(
        collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
        colorScheme = Color.Dark,
        backgroundColor = Color.Dark,
        fluid = true
    ) {
        val togglerTargetId = "toggler${UUID()}"

        Toggler(target = togglerTargetId, controls = togglerTargetId)
        NavbarCollapse(togglerTargetId, attrs = { classes("justify-content-md-center") }) {
            NavbarNav {
                NavbarLink(active = true, link = "#") { Text("Centered nav only") }
                NavbarLink(active = false, link = "#") { Text("Link") }
                NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
                NavbarDropDown("Dropdown", href = "#") {
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
                    Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Something else here") } }
                }
            }
        }
    }
}

@Composable
private fun lightNavbar() {
    Container(fluid = false) {

        Navbar(
            collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
            colorScheme = Color.Light,
            backgroundColor = Color.Light,
            fluid = true,
            attrs = { classes("rounded") }
        ) {
            Brand {
                Text("Navbar")
            }
            val togglerTargetId = "toggler${UUID()}"

            Toggler(target = togglerTargetId, controls = togglerTargetId)
            NavbarCollapse(togglerTargetId) {
                NavbarNav(attrs = { classes("me-auto", "mb-2", "mb-lg-0") }) {
                    NavbarLink(active = true, link = "#") { Text("Home") }
                    NavbarLink(active = false, link = "#") { Text("Link") }
                    NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
                    NavbarDropDown("Dropdown", href = "#") {
                        Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
                        Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
                        Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Something else here") } }
                    }
                }
                org.jetbrains.compose.web.dom.Form(attrs = { classes("m-0") }) {
                    Input(type = InputType.Text, attrs = {
                        classes("form-control")
                        attr("placeholder", "Search")
                    })
                }
            }
        }
    }
}

@Composable
private fun lightCenteredNavbar() {
    Container(fluid = false) {
        Navbar(
            collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
            colorScheme = Color.Light,
            backgroundColor = Color.Light,
            fluid = true,
            attrs = { classes("rounded") }
        ) {
            val togglerTargetId = "toggler${UUID()}"

            Toggler(target = togglerTargetId, controls = togglerTargetId)
            NavbarCollapse(togglerTargetId, attrs = { classes("justify-content-md-center") }) {
                NavbarNav {
                    NavbarLink(active = true, link = "#") { Text("Centered nav only") }
                    NavbarLink(active = false, link = "#") { Text("Link") }
                    NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
                    NavbarDropDown("Dropdown", href = "#") {
                        Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
                        Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
                        Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Something else here") } }
                    }
                }
            }
        }
    }
}
