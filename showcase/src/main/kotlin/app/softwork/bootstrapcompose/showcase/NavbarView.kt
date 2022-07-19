package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.attributes.*
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
    Container(
        fluid = false,
        type = Breakpoint.ExtraLarge,
        styling = { Margins { Bottom { size = SpacingSpecs.SpacingSize.Large } } },
    ) {
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
        fluid = fluid,
        toggler = true,
        brand = {
            Brand {
                Text(title)
            }
        },
        navAttrs = {
            classes("me-auto")
        },
        additionalNavContent = {
            Form(attrs = { classes("m-0") }) {
                Input(type = InputType.Text, attrs = {
                    classes("form-control")
                    attr("placeholder", "Search")
                })
            }
        }
    ) {
        NavbarLink(active = true, link = "#") { Text("Home") }
        NavbarLink(active = false, link = "#") { Text("Link") }
        NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
        NavbarDropDown("Dropdown", href = "#") {
            Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
            Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
            Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Something else here") } }
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
        containerBreakpoint = Breakpoint.ExtraLarge,
        brand = {
            Brand {
                Text("Container XL")
            }
        },
        navAttrs = {
            classes("me-auto")
        },
        additionalNavContent = {
            Form(attrs = { classes("m-0") }) {
                Input(type = InputType.Text, attrs = {
                    classes("form-control")
                    attr("placeholder", "Search")
                })
            }
        }
    ) {
        NavbarLink(active = true, link = "#") { Text("Home") }
        NavbarLink(active = false, link = "#") { Text("Link") }
        NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
        NavbarDropDown("Dropdown", href = "#") {
            Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
            Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
            Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Something else here") } }
        }
    }
}

@Composable
private fun centeredNavOnly() {
    Navbar(
        collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
        colorScheme = Color.Dark,
        backgroundColor = Color.Dark,
        fluid = true,
        brand = {}
    ) {
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

@Composable
private fun lightNavbar() {
    Container(fluid = false) {
        Navbar(
            collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
            colorScheme = Color.Light,
            backgroundColor = Color.Light,
            fluid = true,
            styling = { Borders { radius(BorderSpec.BorderRadius.All, BorderSpec.RadiusSize.Small) } },
            brand = {
                Brand {
                    Text("Navbar")
                }
            },
            navAttrs = {
                classes("me-auto", "mb-2", "mb-lg-0")
            },
            additionalNavContent = {
                Form(attrs = { classes("m-0") }) {
                    Input(type = InputType.Text, attrs = {
                        classes("form-control")
                        attr("placeholder", "Search")
                    })
                }
            }
        ) {
            NavbarLink(active = true, link = "#") { Text("Home") }
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

@Composable
private fun lightCenteredNavbar() {
    Container(fluid = false) {
        Navbar(
            collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
            colorScheme = Color.Light,
            backgroundColor = Color.Light,
            fluid = true,
            styling = { Borders { radius(BorderSpec.BorderRadius.All, BorderSpec.RadiusSize.Small) } },
            brand = { },
            navAttrs = {
                classes("justify-content-md-center")
            }
        ) {
            NavbarLink(active = true, link = "#") { Text("Centered nav only") }
            NavbarLink(active = false, link = "#") { Text("Link") }
            NavbarLink(active = false, disabled = true, link = "#") { Text("Disabled") }
            NavbarDropDown("Dropdown", href = "#") {
                Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Action") } }
                Custom { A(href = "#", attrs = { classes("dropdown-item") }) { Text("Another action") } }
                Custom {
                    A(
                        href = "#",
                        attrs = { classes("dropdown-item") }
                    ) { Text("Something else here") }
                }
            }
        }
    }
}
