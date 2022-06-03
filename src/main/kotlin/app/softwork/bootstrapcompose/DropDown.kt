package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

public class DropDownBuilder {
    public sealed class Content {
        public data class Button(val title: String, val styling: (Styling.() -> Unit)?, val onClick: () -> Unit) :
            Content()

        public data class Header(val title: String, val styling: (Styling.() -> Unit)?) : Content()
        public class Custom(public val content: ContentBuilder<HTMLLIElement>) : Content()

        public data class Divider(val styling: (Styling.() -> Unit)?) : Content()
    }

    private val content = mutableListOf<Content>()

    public fun Button(title: String, styling: (Styling.() -> Unit)? = null, onClick: () -> Unit) {
        content.add(Content.Button(title, styling, onClick))
    }

    public fun Divider(styling: (Styling.() -> Unit)? = null) {
        content.add(Content.Divider(styling))
    }

    public fun Header(title: String, styling: (Styling.() -> Unit)? = null) {
        content.add(Content.Header(title, styling))
    }


    public fun Custom(block: ContentBuilder<HTMLLIElement>) {
        content.add(Content.Custom(block))
    }

    internal fun build(): List<Content> = content
}

public object DropDown {
    public enum class Direction(private val classname: String) {
        Down("dropdown"),
        Up("dropup"),
        Right("dropend"),
        Left("dropstart");

        public override fun toString(): String {
            return classname
        }
    }

    public sealed class MenuAlignment(internal vararg val classes: String) {
        public object Start : MenuAlignment("dropdown-menu")
        public object End : MenuAlignment("dropdown-menu", "dropdown-menu-end")
        public class StartThenEndAtBreakpoint(breakpoint: Breakpoint) :
            MenuAlignment("dropdown-menu", "dropdown-menu-$breakpoint-end")

        public class EndThenStartAtBreakpoint(breakpoint: Breakpoint) :
            MenuAlignment("dropdown-menu", "dropdown-menu-end", "dropdown-menu-$breakpoint-start")
    }
}

@Composable
public fun DropDown(
    title: String,
    id: String = remember { "dropdownMenu${UUID()}" },
    size: ButtonSize = ButtonSize.Default,
    color: Color = Color.Primary,
    styling: (Styling.() -> Unit)? = null,
    direction: DropDown.Direction = DropDown.Direction.Down,
    menuAlignment: DropDown.MenuAlignment = DropDown.MenuAlignment.Start,
    block: DropDownBuilder.() -> Unit
) {
    Style
    needsJS
    val trigger = @Composable { classes: Array<String> ->
        Button(attrs = {
            classes("btn", "btn-$color", "dropdown-toggle")
            classes(size.toString())
            classes(*classes)
            id(id)
            attr("data-bs-toggle", "dropdown")
            attr("aria-expanded", "false")
            responsiveAlignmentAttribute(menuAlignment)
        }) {
            Text(title)
        }
    }

    DropDownBase(trigger, id, styling, direction, menuAlignment, block)
}

@Composable
public fun NavbarDropDown(
    title: String,
    href: String?,
    id: String = remember { "dropdownMenu${UUID()}" },
    styling: (Styling.() -> Unit)? = null,
    direction: DropDown.Direction = DropDown.Direction.Down,
    menuAlignment: DropDown.MenuAlignment = DropDown.MenuAlignment.Start,
    block: DropDownBuilder.() -> Unit
) {
    Style
    needsJS
    val trigger = @Composable { classes: Array<String> ->
        A(
            attrs = {
                classes("nav-link", "dropdown-toggle")
                classes(*classes)
                id(id)
                attr("data-bs-toggle", "dropdown")
                attr("aria-expanded", "false")
                responsiveAlignmentAttribute(menuAlignment)
            },
            href = href
        ) {
            Text(title)
        }
    }

    DropDownBase(trigger, id, styling, direction, menuAlignment, block)
}

private fun <T : Element> AttrsScope<T>.responsiveAlignmentAttribute(menuAlignment: DropDown.MenuAlignment) {
    if (menuAlignment is DropDown.MenuAlignment.EndThenStartAtBreakpoint ||
        menuAlignment is DropDown.MenuAlignment.StartThenEndAtBreakpoint
    ) {
        attr("data-bs-display", "static")
    }
}

@Composable
private fun DropDownBase(
    triggerElement: @Composable (classes: Array<String>) -> Unit,
    id: String,
    styling: (Styling.() -> Unit)? = null,
    direction: DropDown.Direction,
    menuAlignment: DropDown.MenuAlignment,
    block: DropDownBuilder.() -> Unit
) {
    Style
    needsJS
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Div({ classes("btn-group", direction.toString()) }) {
        val buttons = DropDownBuilder().apply {
            block()
        }.build()

        triggerElement(classes)

        Ul({
            classes(*menuAlignment.classes)
            attr("aria-labelledby", id)
        }) {

            for (button in buttons) {
                Li {
                    when (button) {
                        is DropDownBuilder.Content.Button -> {
                            val buttonClasses = button.styling?.let {
                                Styling().apply(it).generate()
                            } ?: arrayOf()
                            Button({
                                classes("dropdown-item")
                                classes(*buttonClasses)
                                onClick { button.onClick() }
                            }) {
                                Text(button.title)
                            }
                        }
                        is DropDownBuilder.Content.Divider -> {
                            val buttonClasses = button.styling?.let {
                                Styling().apply(it).generate()
                            } ?: arrayOf()
                            Hr {
                                classes("dropdown-divider")
                                classes(*buttonClasses)
                            }
                        }
                        is DropDownBuilder.Content.Header -> {
                            val buttonClasses = button.styling?.let {
                                Styling().apply(it).generate()
                            } ?: arrayOf()
                            H6({
                                classes("dropdown-header")
                                classes(*buttonClasses)
                            }) {
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
