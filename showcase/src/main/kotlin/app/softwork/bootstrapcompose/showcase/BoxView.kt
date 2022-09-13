package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import app.softwork.bootstrapcompose.BorderSpec.BorderWidth.Thicker
import app.softwork.bootstrapcompose.SpacingSpecs.SpacingSize
import app.softwork.bootstrapcompose.SpacingSpecs.SpacingSize.Large
import app.softwork.bootstrapcompose.SpacingSpecs.SpacingSize.Small
import app.softwork.bootstrapcompose.Text.Alignment
import app.softwork.bootstrapcompose.Text.Alignment.Center
import app.softwork.bootstrapcompose.Text.Style.Italic
import app.softwork.bootstrapcompose.Text.Transform.Uppercase
import app.softwork.bootstrapcompose.Text.Weight.Bold
import app.softwork.bootstrapcompose.Text.Wraps.NoWrap
import app.softwork.bootstrapcompose.icons.*
import org.jetbrains.compose.web.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.svg.*

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun BoxView() {
    Container {
        Row {
            Column {
                Box(
                    styling = {
                        Margins {
                            Top {
                                size = Large
                            }
                        }
                        Borders {
                            All {
                                width = Thicker
                                color = Color.Primary
                            }
                            radius(BorderSpec.BorderRadius.Top, BorderSpec.RadiusSize.Large)
                        }
                        Text {
                            align(alignment = Center)
                            style = Italic
                        }
                    }
                ) {
                    Text("Thick primary color borders with large radius on top corners and a resized Bootstrap icon")
                    Bootstrap {
                        height(50)
                        width(50)
                        fill("green")
                    }
                }
            }

            Column {
                Box(styling = {
                    Margins {
                        All {
                            size = Large
                        }
                    }

                    Borders {
                        All {
                            width = Thicker
                            color = Color.Primary
                        }
                        radius(type = BorderSpec.BorderRadius.Pill, size = BorderSpec.RadiusSize.Small)
                    }

                    Padding {
                        (Start + End) {
                            size = Large
                        }
                    }

                    Background {
                        color = Color.Danger
                        gradient = true
                    }

                    Text {
                        align(Center)
                    }
                }) {
                    Text("Thick primary color pillbox borders")
                }
            }

            Column {
                Box(styling = {
                    Margins {
                        All {
                            size = Large
                        }
                    }

                    Borders {
                        (Top + Bottom) {
                            width = Thicker
                            color = Color.Primary
                        }
                    }
                }) {
                    Text("Top and Bottom borders")
                }
            }

            Column {
                Color.values().forEach {
                    BackgroundGradientBox(it)
                }
            }
        }
    }
}

@Composable
private fun BackgroundGradientBox(color: Color) {
    Box(styling = {
        Background {
            this.color = color
            gradient = true
        }
        Padding {
            All { size = SpacingSize.Medium }
        }
        Margins {
            Bottom {
                size = Small
            }
        }
        Text {
            align(Alignment.Start)
            align(Center, Breakpoint.Medium)
            align(Alignment.End, Breakpoint.Large)
            wrap = NoWrap
            transform = Uppercase
            size = 4
            weight = Bold
        }
    }) {
        Text(color.toString())
    }
}
