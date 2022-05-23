package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import kotlinx.coroutines.*
import org.jetbrains.compose.web.dom.*
import kotlin.time.*
import kotlin.time.Duration.Companion.seconds

@Composable
fun ToastView() {
    var count: Int by remember { mutableStateOf(0) }
    val toastContainerState: ToastContainerState = remember { ToastContainerState() }

    Button("Text Toast", styling = {
        Margins {
            All { size = SpacingSpecs.SpacingSize.Small }
        }
    }) {
        count++
        toastContainerState.showToast("Toast #$count")
    }

    Button("With Header", styling = {
        Margins {
            All { size = SpacingSpecs.SpacingSize.Small }
        }
    }) {
        count++
        toastContainerState.showToast(header = {
            Div(attrs = { classes("me-auto") }) {
                Text("Bootstrap")
            }
            Small {
                Text("11 mins ago")
            }
        }) {
            Text("Hello, world! This is a toast message.")
        }
    }

    Button("Color schemes", styling = {
        Margins {
            All { size = SpacingSpecs.SpacingSize.Small }
        }
    }) {
        count++
        toastContainerState.showToast(
            toastAttrs = {
                classes("align-items-center", "text-white", "bg-primary", "border-0")
            },
            dismissButtonAttrs = {
                classes("btn-close-white")
            }
        ) {
            Text("Hello, world! This is a toast message.")
        }
    }

    Button("With 10s Delay to hide", styling = {
        Margins {
            All { size = SpacingSpecs.SpacingSize.Small }
        }
    }) {
        val delay = 10.seconds
        count++
        toastContainerState.showToast(
            delay = delay,
            header = {
                Div(attrs = { classes("me-auto") }) {
                    Text("Bootstrap")
                }
                Small {
                    Text("11 mins ago")
                }
            }) {
            var remainingDelay by remember { mutableStateOf(delay) }
            LaunchedEffect(delay) {
                while (true) {
                    delay(1.seconds)
                    remainingDelay -= 1.seconds
                }
            }
            Text("Hello, world! This is a toast message with a remaining delay of $remainingDelay.")
        }
    }

    ToastContainer(toastContainerState) {
        classes("position-absolute", "bottom-0", "end-0", "p-3")
    }
}
