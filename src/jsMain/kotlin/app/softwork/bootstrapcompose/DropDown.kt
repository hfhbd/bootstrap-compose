package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

public class DropDownBuilder {
    public sealed class Content {
        public data class Button(val title: String, val onClick: () -> Unit) : Content()
        public data class Header(val title: String) : Content()
        public class Custom : Content() {
            public lateinit var content: ContentBuilder<HTMLLIElement>
        }

        public object Divider : Content()
    }

    private val content = mutableListOf<Content>()

    public fun Button(title: String, onClick: () -> Unit) {
        content.add(Content.Button(title, onClick))
    }

    public fun Divider() {
        content.add(Content.Divider)
    }

    public fun Header(title: String) {
        content.add(Content.Header(title))
    }


    public fun Custom(block: ContentBuilder<HTMLLIElement>) {
        content.add(Content.Custom().apply { content = block })
    }

    internal fun build(): List<Content> = content
}

@Composable
public fun DropDown(title: String, id: String = remember{UUID().toString()}, color: Color = Color.Primary, block: DropDownBuilder.() -> Unit) {
    Div({ classes("dropdown") }) {
        val buttons = DropDownBuilder().apply {
            block()
        }.build()

        Button(attrs = {
            classes("btn", "btn-$color", "dropdown-toggle")
            id("dropdownMenu$id")
            attr("data-bs-toggle", "dropdown")
            attr("aria-expanded", "false")
        }) {
            Text(title)
        }

        Ul({
            classes("dropdown-menu")
            attr("aria-labelledby", "dropdownMenu$id")
        }) {

            for (button in buttons) {
                Li {
                    when (button) {
                        is DropDownBuilder.Content.Button -> {
                            Button({
                                classes("dropdown-item")
                                onClick { button.onClick() }
                            }) {
                                Text(button.title)
                            }
                        }
                        is DropDownBuilder.Content.Divider -> {
                            Hr { classes("dropdown-divider") }
                        }
                        is DropDownBuilder.Content.Header -> {
                            H6({classes("dropdown-header")}) {
                                Text(button.title)
                            }
                        }
                        is DropDownBuilder.Content.Custom -> {
                            button.content(this)
                        }
                    }
                }
            }
        }
    }
}

@Composable
public fun NavbarDropDown(title: String, href: String?, id: String = remember{UUID().toString()}, block: DropDownBuilder.() -> Unit) {
    Div({ classes("dropdown") }) {
        val buttons = DropDownBuilder().apply {
            block()
        }.build()

        A(attrs = {
            classes("nav-link", "dropdown-toggle")
            id("dropdownMenu$id")
            attr("data-bs-toggle", "dropdown")
            attr("aria-expanded", "false")
        },
            href = href
        ) {
            Text(title)
        }

        Ul({
            classes("dropdown-menu")
            attr("aria-labelledby", "dropdownMenu$id")
        }) {

            for (button in buttons) {
                Li {
                    when (button) {
                        is DropDownBuilder.Content.Button -> {
                            Button({
                                classes("dropdown-item")
                                onClick { button.onClick() }
                            }) {
                                Text(button.title)
                            }
                        }
                        is DropDownBuilder.Content.Divider -> {
                            Hr { classes("dropdown-divider") }
                        }
                        is DropDownBuilder.Content.Header -> {
                            H6({classes("dropdown-header")}) {
                                Text(button.title)
                            }
                        }
                        is DropDownBuilder.Content.Custom -> {
                            button.content(this)
                        }
                    }
                }
            }
        }
    }
}
