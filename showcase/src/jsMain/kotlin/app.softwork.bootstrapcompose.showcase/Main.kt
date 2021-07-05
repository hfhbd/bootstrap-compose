package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.*
import org.jetbrains.compose.web.dom.*

data class AppState(val activePage: ActivePage)

fun main() {
    renderComposable(rootElementId = "root") {
        var state by remember { mutableStateOf(AppState(ActivePage.Form)) }

        Navbar(
            collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
            colorScheme = Color.Dark,
            toggler = true,
            togglerPosition = TogglerPosition.Right,
            brand = {
                Brand {
                    Text("bootstrap-compose Showcase")
                }
            },
            navAttrs = { classes("flex-grow-1") }
        ) {
            NavbarDropDown(title = state.activePage.displayName, href = "#") {
                ActivePage.values().forEach {
                    Button(it.displayName) {
                        state = state.copy(activePage = it)
                    }
                }
            }
            A(href = "https://github.com/hfhbd/bootstrap-compose", attrs = {
                classes("nav-link", "ms-auto", "link-secondary")
            }) {
                Text("View on GitHub ")
                Icon("github")
            }
        }

        Main {
            when (state.activePage) {
                ActivePage.ChecksAndRadios -> ChecksAndRadiosView()
                ActivePage.Form -> FormView()
                ActivePage.InputGroup -> InputGroupView()
                ActivePage.ListGroup -> ListGroupView()
                ActivePage.Navbar -> NavbarView()
                ActivePage.Range -> RangeView()
                ActivePage.Select -> SelectView()
                ActivePage.Table -> TableView()
            }
        }
        Footer(attrs = { classes("footer", "mt-auto") }) {
            Container {
                Hr()
                P {
                    Text("Some Footer")
                }
            }
        }
    }
}

enum class ActivePage(val displayName: String) {
    ChecksAndRadios("Checks & radios"),
    Form("Forms"),
    InputGroup("Input group"),
    ListGroup("Lists"),
    Navbar("Navbars"),
    Range("Range"),
    Select("Select"),
    Table("Table")
}

