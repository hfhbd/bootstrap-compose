package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ListGroupView() {
    Container {
        H1 { Text("Basic example") }
        ListGroup {
            ListItem { Text("An item") }
            ListItem { Text("A second item") }
            ListItem(active = true) { Text("An active third item") }
            ListItem { Text("A fourth item") }
            ListItem(disabled = true) { Text("And a disabled fifth item") }
        }

        Hr()
        H1 { Text("Links and buttons") }
        ListGroup {
            AnchorListItem(href = "#", active = true) { Text("The current link item") }
            AnchorListItem(href = "#") { Text("A second link item") }
            AnchorListItem(href = "#") { Text("A third link item") }
            AnchorListItem(href = "#", disabled = true) { Text("A disabled link item") }
        }

        Hr()
        ListGroup {
            ButtonListItem(active = true) { Text("The current button") }
            ButtonListItem { Text("A second button") }
            ButtonListItem { Text("A third button") }
            ButtonListItem(disabled = true) { Text("A disabled button item") }
        }

        Hr()
        H1 { Text("Flush") }
        ListGroup(flush = true) {
            ListItem { Text("An item") }
            ListItem { Text("A second item") }
            ListItem { Text("A third item") }
            ListItem { Text("A fourth item") }
            ListItem { Text("And a fifth one") }
        }

        Hr()
        H1 { Text("Numbered") }
        NumberedListGroup {
            ListItem { Text("An item") }
            ListItem { Text("A second item") }
            ListItem { Text("A third item") }
            ListItem { Text("A fourth item") }
            ListItem { Text("And a fifth one") }
        }

        Hr()
        H1 { Text("Horizontal") }
        ListGroup(listGroupDirection = ListGroupDirection.Horizontal()) {
            ListItem { Text("An item") }
            ListItem { Text("A second item") }
            ListItem { Text("A third item") }
        }

        Hr()
        H1 { Text("Contextual classes") }
        ListGroup {
            ListItem { Text("A simple default list group item") }
            ListItem(background = Color.Primary) { Text("A simple primary list group item") }
            ListItem(background = Color.Secondary) { Text("A simple secondary list group item") }
            ListItem(background = Color.Success) { Text("A simple success list group item") }
        }

        Hr()
        H1 { Text("Custom content") }
        ListGroup {
            AnchorListItem(href = "#", active = true) {
                Div(attrs = { classes("d-flex", "w-100", "justify-content-between") }) {
                    H5(attrs = { classes("mb-1") }) { Text("List group item heading") }
                    Small { Text("3 days ago") }
                }
                P(attrs = { classes("mb-1") }) { Text("Some placeholder content in a paragraph.") }
                Small { Text("And some small print.") }
            }
            AnchorListItem(href = "#") {
                Div(attrs = { classes("d-flex", "w-100", "justify-content-between") }) {
                    H5(attrs = { classes("mb-1") }) { Text("List group item heading") }
                    Small { Text("3 days ago") }
                }
                P(attrs = { classes("mb-1") }) { Text("Some placeholder content in a paragraph.") }
                Small(attrs = { classes("text-muted") }) { Text("And some muted small print.") }
            }
            AnchorListItem(href = "#") {
                Div(attrs = { classes("d-flex", "w-100", "justify-content-between") }) {
                    H5(attrs = { classes("mb-1") }) { Text("List group item heading") }
                    Small { Text("3 days ago") }
                }
                P(attrs = { classes("mb-1") }) { Text("Some placeholder content in a paragraph.") }
                Small(attrs = { classes("text-muted") }) { Text("And some muted small print.") }
            }
        }

        Hr()
        H1 { Text("Checkboxes") }
        ListGroup {
            ListItem {
                Input(type = InputType.Checkbox, attrs = {
                    classes("form-check-input", "me-1")
                })
                Text("First checkbox")
            }
            ListItem {
                Input(type = InputType.Checkbox, attrs = {
                    classes("form-check-input", "me-1")
                })
                Text("Second checkbox")
            }
            ListItem {
                Input(type = InputType.Checkbox, attrs = {
                    classes("form-check-input", "me-1")
                })
                Text("Third checkbox")
            }
        }
    }
}
