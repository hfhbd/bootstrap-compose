package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import app.softwork.bootstrapcompose.icons.*
import org.jetbrains.compose.web.*
import org.jetbrains.compose.web.dom.*

data class AppState(val activePage: ActivePage)

@JsModule("./custom.scss")
@JsNonModule
private external val customScss: dynamic

fun main() {
    customScss
    renderComposable(rootElementId = "root") {
        var state by remember { mutableStateOf(AppState(ActivePage.Box)) }

        Navbar(state) {
            state = it
        }

        Main {
            when (state.activePage) {
                ActivePage.Alert -> AlertView()
                ActivePage.Autocomplete -> AutoCompleteView()
                ActivePage.Box -> BoxView()
                ActivePage.ChecksAndRadios -> ChecksAndRadiosView()
                ActivePage.DropDown -> DropDownView()
                ActivePage.Form -> FormView()
                ActivePage.Grid -> GridBoxView()
                ActivePage.InputGroup -> InputGroupView()
                ActivePage.ListGroup -> ListGroupView()
                ActivePage.Modal -> ModalView()
                ActivePage.Navbar -> NavbarView()
                ActivePage.Offcanvas -> OffcanvasView()
                ActivePage.Range -> RangeView()
                ActivePage.Select -> SelectView()
                ActivePage.Table -> TableView()
                ActivePage.Toast -> ToastView()
            }
        }
        Footer(attrs = { classes("footer", "mt-auto") }) {
            Container {
                org.jetbrains.compose.web.dom.Hr()
                P {
                    Text("Some Footer")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
private fun Navbar(state: AppState, onChange: (AppState) -> Unit) {
    Navbar(
        placement = NavbarPlacement.StickyTop,
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
                    onChange(state.copy(activePage = it))
                }
            }
        }
        NavbarDropDown(title = "Login", href = "#") {
            Custom {
                RawInputView()
            }
        }
        A(href = "https://github.com/hfhbd/bootstrap-compose", attrs = {
            classes("nav-link", "ms-auto", "link-secondary")
        }) {
            Text("View on GitHub ")
            Github()
        }
    }
}

enum class ActivePage(val displayName: String) {
    Alert("Alert"),
    Autocomplete("Autocomplete"),
    Box("Boxes"),
    ChecksAndRadios("Checks & radios"),
    DropDown("Dropdowns"),
    Form("Forms"),
    Grid("Grid Layout"),
    InputGroup("Input group"),
    ListGroup("Lists"),
    Modal("Modal"),
    Navbar("Navbars"),
    Offcanvas("Offcanvas"),
    Range("Range"),
    Select("Select"),
    Table("Table"),
    Toast("Toasts")
}
