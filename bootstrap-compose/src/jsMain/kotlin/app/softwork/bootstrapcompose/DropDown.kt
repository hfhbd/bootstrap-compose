package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*
import kotlin.uuid.*

public interface DropDownBuilder : ElementScope<HTMLUListElement> {
    @Composable
    @NonRestartableComposable
    public fun Button(
        title: String,
        onClick: () -> Unit
    ) {
        Button(
            title = title,
            styling = null,
            onClick = onClick
        )
    }

    @Composable
    public fun Button(
        title: String,
        styling: (Styling.() -> Unit)?,
        onClick: () -> Unit
    ) {
        Li {
            val buttonClasses = styling?.let {
                Styling().apply(it).generate()
            }
            Button({
                classes("dropdown-item")
                if (buttonClasses != null) {
                    classes(classes = buttonClasses)
                }
                onClick { onClick() }
            }) {
                Text(title)
            }
        }
    }

    @Composable
    @NonRestartableComposable
    public fun Divider() {
        Divider(null)
    }

    @Composable
    public fun Divider(styling: (Styling.() -> Unit)?) {
        Li {
            val buttonClasses = styling?.let {
                Styling().apply(it).generate()
            }
            Hr {
                classes("dropdown-divider")
                if (buttonClasses != null) {
                    classes(classes = buttonClasses)
                }
            }
        }
    }

    @Composable
    @NonRestartableComposable
    public fun Header(
        title: String,
    ) {
        Header(title, null)
    }

    @Composable
    public fun Header(
        title: String,
        styling: (Styling.() -> Unit)?,
    ) {
        Li {
            val buttonClasses = styling?.let {
                Styling().apply(it).generate()
            }
            H6({
                classes("dropdown-header")
                if (buttonClasses != null) {
                    classes(buttonClasses)
                }
            }) {
                Text(title)
            }
        }
    }

    @Composable
    public fun Custom(block: ContentBuilder<HTMLLIElement>) {
        Li {
            block()
        }
    }
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
@OptIn(ExperimentalUuidApi::class)
public fun DropDown(
    title: String,
    id: String = remember { "dropdownMenu${Uuid.random()}" },
    size: ButtonSize = ButtonSize.Default,
    color: Color = Color.Primary,
    styling: (Styling.() -> Unit)? = null,
    direction: DropDown.Direction = DropDown.Direction.Down,
    menuAlignment: DropDown.MenuAlignment = DropDown.MenuAlignment.Start,
    block: @Composable DropDownBuilder.() -> Unit
) {
    Style
    DropDownJs
    popper
    val trigger = @Composable { classes: List<String>? ->
        Button(attrs = {
            classes("btn", "btn-$color", "dropdown-toggle")
            classes(size.toString())
            if (classes != null) {
                classes(classes)
            }
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
@OptIn(ExperimentalUuidApi::class)
public fun NavbarDropDown(
    title: String,
    href: String?,
    id: String = remember { "dropdownMenu${Uuid.random()}" },
    styling: (Styling.() -> Unit)? = null,
    direction: DropDown.Direction = DropDown.Direction.Down,
    menuAlignment: DropDown.MenuAlignment = DropDown.MenuAlignment.Start,
    block: @Composable DropDownBuilder.() -> Unit
) {
    Style
    DropDownJs
    val trigger = @Composable { classes: List<String>? ->
        A(
            attrs = {
                classes("nav-link", "dropdown-toggle")
                if (classes != null) {
                    classes(classes)
                }
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
    triggerElement: @Composable (classes: List<String>?) -> Unit,
    id: String,
    styling: (Styling.() -> Unit)? = null,
    direction: DropDown.Direction,
    menuAlignment: DropDown.MenuAlignment,
    block: @Composable DropDownBuilder.() -> Unit
) {
    Style
    DropDownJs
    val classes = styling?.let {
        Styling().apply(it).generate()
    }

    Div({ classes("btn-group", direction.toString()) }) {
        triggerElement(classes)

        Ul({
            classes(classes = menuAlignment.classes)
            attr("aria-labelledby", id)
        }) {
            DropDownImpl(this).block()
        }
    }
}

private class DropDownImpl(scope: ElementScope<HTMLUListElement>) :
    DropDownBuilder, ElementScope<HTMLUListElement> by scope
