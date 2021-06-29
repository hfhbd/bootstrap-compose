package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

data class AppState(val activePage: ActivePage)

fun main() {
    renderComposable(rootElementId = "root") {
        var state by remember { mutableStateOf(AppState(ActivePage.ListGroup)) }

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
            NavbarDropDown(title = state.activePage.toString(), href = "#") {
                ActivePage.values().forEach {
                    Button(it.name) {
                        state = state.copy(activePage = it)
                    }
                }
            }
        }

        when (state.activePage) {
            ActivePage.ListGroup -> ListGroupView()
            ActivePage.Navbar -> NavbarView()
        }
    }
}

enum class ActivePage {
    ListGroup,
    Navbar
}

