package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import app.softwork.bootstrapcompose.Color
import app.softwork.bootstrapcompose.Layout.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun GridBoxView() {
    Box({ Margins { All { size = SpacingSpecs.SpacingSize.Small } } }) {
        example1()
        Hr()
        mozillaExample()
        Hr()
        example13()
    }
}

@Composable
private fun example1() {
    GridBox(
        styling = {
            Layout {
                width = Width.Full
            }
            GridLayout {
                columns {
                    trackList {
                        track(25.fr)
                        track(50.fr)
                        track(25.fr)
                    }
                    gap = 5.px
                    alignment = GridTemplateTrack.Alignment.End
                }

                rows {
                    trackList {
                        track(Grid.Auto)
                    }
                    gap = 10.px
                    alignment = GridTemplateTrack.Alignment.Center
                }

                // Example to show how the layout can be changed at different breakpoints
                areas {
                    row("A", "A", "A")
                    row("B", "B", "B")
                    row("C", "C", "C")
                }
                areas(Breakpoint.Large) {
                    row("A", "A", "A")
                    row("B", "C", "C")
                    row("B", "C", "C")
                }
            }
        }
    ) {
        Box({
            Background {
                color = Color.Primary
            }
            Borders {
                All {
                    width = BorderSpec.BorderWidth.Medium
                    color = Color.Black50
                }
            }
            GridItem {
                area("A")
                placement {
                    inline = PlacementSpec.PlacementType.Stretch
                    block = PlacementSpec.PlacementType.Start
                }
            }
        }) {
            Text("In Area A")
        }
        Box({
            Background {
                color = Color.Secondary
            }
            Borders {
                All {
                    width = BorderSpec.BorderWidth.Medium
                    color = Color.Black50
                }
            }
            GridItem {
                area("B")
                placement {
                    inline = PlacementSpec.PlacementType.Stretch
                    block = PlacementSpec.PlacementType.Stretch
                }
            }
        }) {
            Text("In Area B")
        }
        Box({
            Background {
                color = Color.Success
            }
            Borders {
                All {
                    width = BorderSpec.BorderWidth.Medium
                    color = Color.Black50
                }
            }
            GridItem {
                area("C")
                placement {
                    inline = PlacementSpec.PlacementType.Center
                    block = PlacementSpec.PlacementType.Center
                }
            }
        }) {
            Text("In Area C")
        }
    }
}

/**
 * Example from https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout
 */
@Composable
private fun mozillaExample() {
    GridBox({
        GridLayout {
            columns {
                trackList {
                    track(TrackList.TrackRepeat(3, 1.fr))
                }
                gap = 10.px
            }
            rows {
                auto {
                    track(MinMax(100.px, Grid.Auto))
                }
                gap = 10.px
            }
        }
    }) {
        Box({
            Borders { All { width = BorderSpec.BorderWidth.Medium } }
            Padding { All { size = SpacingSpecs.SpacingSize.Medium } }
            GridItem {
                area {
                    column { 1 to 3 }
                    row { start(1) }
                }
            }
        }) {
            Text("One")
        }
        Box({
            Borders { All { width = BorderSpec.BorderWidth.Medium } }
            Padding { All { size = SpacingSpecs.SpacingSize.Medium } }
            GridItem {
                area {
                    column(2, 4)
                    row(start = 1, end = 3)
                }
            }
        }) {
            Text("Two")
        }
        Box({
            Borders { All { width = BorderSpec.BorderWidth.Medium } }
            Padding { All { size = SpacingSpecs.SpacingSize.Medium } }
            GridItem {
                area {
                    column { start(1) }
                    row { 2 to 5 }
                }
            }
        }) {
            Text("Three")
        }
        Box({
            Borders { All { width = BorderSpec.BorderWidth.Medium } }
            Padding { All { size = SpacingSpecs.SpacingSize.Medium } }
            GridItem {
                area {
                    column { start(3) }
                    row { start(3) }
                }
            }
        }) {
            Text("Four")
        }
        Box({
            Borders { All { width = BorderSpec.BorderWidth.Medium } }
            Padding { All { size = SpacingSpecs.SpacingSize.Medium } }
            GridItem {
                area {
                    column { start(2) }
                    row { start(4) }
                }
            }
        }) {
            Text("Five")
        }
        Box({
            Borders { All { width = BorderSpec.BorderWidth.Medium } }
            Padding { All { size = SpacingSpecs.SpacingSize.Medium } }
            GridItem {
                area {
                    column { start(3) }
                    row { start(4) }
                }
            }
        }) {
            Text("Six")
        }
    }
}

/**
 * Example from: https://gridbyexample.com/examples/example13/
 */
@Composable
private fun example13() {
    fun Styling.boxStyle() {
        Background { color = Color.Secondary }
        Text {
            color = Color.White
            size = 3
        }
        Borders {
            radius(BorderSpec.BorderRadius.All, BorderSpec.RadiusSize.Medium)
        }
        Padding {
            All {
                size = SpacingSpecs.SpacingSize.Small
            }
        }
    }

    GridBox({
        GridLayout {
            gap = 1.em
            areas {
                row("header")
                row("sidebar")
                row("content")
                row("sidebar2")
                row("footer")
            }
            areas(Breakpoint.Small) {
                row("header     header")
                row("sidebar    content")
                row("sidebar2   sidebar2")
                row("footer     footer")
            }
            areas(Breakpoint.Medium) {
                row("header     header      header")
                row("sidebar    content     sidebar2")
                row("footer     footer      footer")
            }

            columns(Breakpoint.Small) {
                trackList {
                    track(20.percent)
                    track(Grid.Auto)
                }
            }
            columns(Breakpoint.Medium) {
                gap = 20.px
                trackList {
                    track(120.px)
                    track(Grid.Auto)
                    track(120.px)
                }
            }
            rows(Breakpoint.Medium) {
                gap = 20.px
            }
        }
    }) {
        Box({
            boxStyle()
            GridItem {
                area("header")
            }
        }) {
            Text("Header")
        }
        Box({
            boxStyle()
            Background { color = Color.Dark }
            GridItem { area("sidebar") }
        }) {
            Text("Sidebar")
        }
        Box({
            boxStyle()
            GridItem { area("sidebar2") }
        }) {
            Text("Sidebar 2")
        }
        Box({
            boxStyle()
            Background { color = Color.Dark }
            GridItem { area("content") }
        }) {
            Text("Content")
            Br {}
            Text("More content than we had before so this column is now quite tall")
        }
        Box({
            boxStyle()
            GridItem { area("footer") }
        }) {
            Text("Footer")
        }
    }
}
