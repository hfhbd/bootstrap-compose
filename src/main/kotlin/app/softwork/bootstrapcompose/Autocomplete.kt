package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.browser.*
import org.jetbrains.compose.web.attributes.builders.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*
import org.w3c.dom.events.*


private class ReferenceHolder<T>(var ref: T? = null)

/**
 * A TextInput widget with autocomplete/search suggestions that will display below the TextField. This component
 * only supports the "controlled" Input variant. This version provides limited support and expects the user to
 * listen to all input change events and build the suggestions list.
 *
 * @param inputAttrs attributes builder.
 * @param suggestions Composable to build the list of suggestions to display
 */
@Composable
public fun Autocomplete(
    inputAttrs: InputAttrsScope<String>.() -> Unit = {},
    suggestions: ContentBuilder<HTMLDivElement>? = null
) {
    var itemsVisible by remember { mutableStateOf(false) }
    val parentElement = remember { ReferenceHolder<Element>() }

    DisposableEffect(true) {
        val effect: (Event) -> Unit = { event ->
            if (parentElement.ref?.isChild(event.target) != true) {
                itemsVisible = false
            }
        }
        window.document.addEventListener("mousedown", effect)
        onDispose {
            window.document.removeEventListener("mousedown", effect)
        }
    }

    Div(
        attrs = {
            style {
                padding(0.px)
            }
        })
    {
        DisposableEffect(true) {
            parentElement.ref = scopeElement
            onDispose { parentElement.ref = null }
        }

        TextInput(attrs = {
            inputAttrs()
            onInput {
                itemsVisible = true
            }
        })

        Div(
            attrs = {
                Style
                classes("dropdown-menu")
                if (itemsVisible) {
                    classes("d-block")
                } else {
                    classes("d-none")
                }
                onClick {
                    itemsVisible = false
                }
                style {
                    padding(0.px)
                }
            },
            content = suggestions
        )
    }
}

/**
 * An AutoComplete that displays Strings as suggestions.
 *
 * @param value The value for the TextInput
 * @param onValueChange callback when the user changes the Input text
 * @param inputId The Id to assign to the TextInput
 * @param suggestions List of suggestions to be presented to the user
 * @param onSelection callback for when the user selects a suggestion. The TextInput value will not be updated until
 * this Composable is recomposed with an updated value.
 */
@Composable
public fun Autocomplete(
    value: String,
    onValueChange: (String) -> Unit,
    inputId: String? = null,
    suggestions: List<String> = listOf(),
    onSelection: (String) -> Unit
) {
    Autocomplete(
        value = value,
        onValueChange = onValueChange,
        inputId = inputId,
        suggestions = suggestions,
        content = { _, item ->
            Text(item)
        },
        onSelection = { _, item -> onSelection(item) }
    )
}

/**
 * A TextInput widget with autocomplete/search suggestions that will display below the TextField. This component
 * only supports the "controlled" Input variant. It uses a callback for displaying suggestion items.
 *
 * @param value The value for the TextInput
 * @param onValueChange callback when the user changes the Input text
 * @param inputId The Id to assign to the TextInput
 * @param suggestions List of suggestions to be presented to the user
 * @param content Composable for rendering item content. The functions first parameter is the items index and the 2nd
 * parameter is the item to be rendered.
 * @param onSelection callback for when the user selects a suggestion. The TextInput value will not be updated until
 * this Composable is recomposed with an updated value.
 *
 * This function would be better if using a generic type for the List items, content Composable callback parameter,
 * and onSelection callback, except 2 issues may be responsible for compiler failures:
 * https://github.com/JetBrains/compose-jb/issues/774
 * https://github.com/JetBrains/compose-jb/issues/1226
 */
@Composable
public fun Autocomplete(
    value: String,
    onValueChange: (String) -> Unit,
    inputId: String? = null,
    suggestions: List<String> = listOf(),
    content: @Composable ElementScope<HTMLButtonElement>.(index: Int, item: String) -> Unit,
    onSelection: (index: Int, item: String) -> Unit
) {
    Autocomplete(inputAttrs = {
        inputId?.let { id(inputId) }
        value(value)
        onInput {
            onValueChange(it.value)
        }
    }) {
        Ul(attrs = {
            style {
                padding(0.px)
                margin(0.px)
                listStyle("none")
            }
        }) {
            suggestions.forEachIndexed { index, item ->
                Li(attrs = {
                    style {
                        padding(0.cssRem)
                    }
                }) {
                    Button(attrs = {
                        classes("dropdown-item")
                        onClick {
                            onSelection(index, item)
                        }
                    }) {
                        content(index, item)
                    }
                    if (index < suggestions.lastIndex) {
                        Hr(attrs = {
                            style {
                                margin(0.px)
                            }
                        })
                    }
                }
            }
        }
    }
}

/**
 * Traverses the element tree to see if this Element is a parent of the provided element.
 * @param element Element to check if it is a child of this Element
 */
private fun Element.isChild(element: Element): Boolean {
    var nextElement: Element? = element
    while (nextElement != this && nextElement != null) {
        nextElement = nextElement.parentElement
    }
    return nextElement == this
}

/**
 * Traverses the element tree to see if this Element is a parent of the provided EventTarget.
 * @param eventTarget EventTarget to check if it is a child of this Element
 */
private fun Element.isChild(eventTarget: EventTarget?): Boolean {
    return if (eventTarget is Element) {
        isChild(eventTarget)
    } else {
        false
    }
}
