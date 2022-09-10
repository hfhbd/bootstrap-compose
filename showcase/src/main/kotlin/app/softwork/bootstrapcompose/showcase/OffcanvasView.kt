package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import app.softwork.bootstrapcompose.Grid
import app.softwork.bootstrapcompose.GridLayout.*
import app.softwork.bootstrapcompose.Layout.Display.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun OffcanvasView() {
    GridBox(styling = {
        Margins {
            All {
                size = SpacingSpecs.SpacingSize.Small
            }
        }
        GridLayout {
            gap = 1.cssRem
            columns {
                trackList { track(Grid.Auto) }
                alignment = GridTemplateTrack.Alignment.Start
            }
            alignContent = Placement.Start
            justifyContent = Placement.Start
        }
    }) {
        OffcanvasView(OffcanvasPlacement.START, "Offcanvas start")

        val showUnlessBreakpointLarge = Styling {
            Layout {
                display(None)
                display(Block, Breakpoint.Large)
            }
        }.generate()
        Div({
            classes(showUnlessBreakpointLarge)
        }) {
            Text("Decrease screen wide to toggle Offcanvas with Large breakpoint")
        }

        val showUntilBreakpointLarge = Styling {
            Layout.display(None, Breakpoint.Large)
        }.generate()

        Div({
            classes(showUntilBreakpointLarge)
        }) {
            OffcanvasView(OffcanvasPlacement.START, "Offcanvas start-Large", Breakpoint.Large)
        }
        OffcanvasView(OffcanvasPlacement.END, "Offcanvas end")
        OffcanvasView(OffcanvasPlacement.BOTTOM, "Offcanvas bottom")
        OffcanvasView(OffcanvasPlacement.TOP, "Offcanvas top")
    }
}

@Composable
private fun OffcanvasView(placement: OffcanvasPlacement, label: String, breakpoint: Breakpoint? = null) {
    val offcanvasState = remember { OffcanvasState() }

    Button(label) {
        offcanvasState.show()
    }

    Offcanvas(
        placement,
        breakpoint = breakpoint,
        offcanvasState,
        headerContent = {
            H5 {
                Text(label)
            }
        }
    ) {
        Text("...")
    }
}
