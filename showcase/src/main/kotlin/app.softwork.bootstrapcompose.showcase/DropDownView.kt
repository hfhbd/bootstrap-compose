package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.css.*

@Composable
fun DropDownView() {
    GridBox(styling = {
        Margins {
            All {
                size = SpacingSpecs.SpacingSize.Small
            }
        }
        GridLayout {
            columns {
                trackList { track(repeat = TrackList.TrackRepeat(4, 25.fr)) }
                gap = 0.5.em
            }
            rows { gap = 0.5.em }
        }
    }) {
        DropDown("Dropdown") {
            items()
        }

        DropDown("Right-aligned menu", menuAlignment = DropDown.MenuAlignment.End) {
            items()
        }

        DropDown(
            "Left-aligned, right-aligned lg",
            menuAlignment = DropDown.MenuAlignment.StartThenEndAtBreakpoint(Breakpoint.Large)
        ) {
            items()
        }

        DropDown(
            "Right-aligned, left-aligned lg",
            menuAlignment = DropDown.MenuAlignment.EndThenStartAtBreakpoint(Breakpoint.Large)
        ) {
            items()
        }

        DropDown("Dropstart", direction = DropDown.Direction.Left) {
            items()
        }

        DropDown("Dropend", direction = DropDown.Direction.Right) {
            items()
        }

        DropDown("Dropup", direction = DropDown.Direction.Up) {
            items()
        }
    }
}

@Composable
private fun DropDownBuilder.items() {
    Button("Menu Item") {}
    Button("Menu Item") {}
    Button("Menu Item") {}
}
