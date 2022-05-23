package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import app.softwork.bootstrapcompose.GridLayout.Placement

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
        OffcanvasView(OffcanvasPlacement.END, "Offcanvas end")
        OffcanvasView(OffcanvasPlacement.BOTTOM, "Offcanvas bottom")
        OffcanvasView(OffcanvasPlacement.TOP, "Offcanvas top")
    }
}

@Composable
private fun OffcanvasView(placement: OffcanvasPlacement, label: String) {
    val offcanvasState = remember { OffcanvasState() }

    Button(label) {
        offcanvasState.show()
    }

    Offcanvas(placement,
        offcanvasState,
        headerContent = {
            H5 {
                Text(label)
            }
        }) {
        Text("...")
    }
}