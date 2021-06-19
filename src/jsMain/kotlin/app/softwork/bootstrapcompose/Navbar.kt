package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

/**
 * Bootstrap Navbar component. This version provides more flexibility than the alternative but is more complex
 * to use. For a simpler implementation use the version that takes a brand and navItems as parameters.
 *
 * @param breakpoint Specifies the navbar-expand breakpoint
 * @param fluid Specifies if the inner container is fluid (container-fluid) or not.
 * @param containerBreakpoint Breakpoint for the inner container.
 * @param colorScheme Valid values are Color.Light or Color.Dark to set the navbar-dark/light class.
 * @param backgroundColor Background color to use, with the bg-* classes.
 * @param content Navbar content, placed within the inner container.
 */
@Composable
public fun Navbar(
    breakpoint: Breakpoint = Breakpoint.Medium,
    fluid: Boolean = false,
    containerBreakpoint: Breakpoint? = null,
    colorScheme: Color = Color.Light,
    backgroundColor: Color = Color.Primary,
    attrs: AttrsBuilder<HTMLElement>.() -> Unit = { },
    content: ContentBuilder<HTMLElement>? = null
) {
    Nav(attrs = {
        classes(
            BSClasses.navbar,
            "navbar-expand-$breakpoint",
            "navbar-${colorScheme}",
            "bg-${backgroundColor}"
        )
        attr("role", "navigation")
        attrs()
    }) {
        Container(fluid, containerBreakpoint) {
            content?.invoke(this)
        }
    }
}

/**
 * Bootstrap Navbar component that can optionally enable a Toggler and Brand.
 *
 * @param breakpoint Specifies the navbar-expand breakpoint
 * @param fluid Specifies if the inner container is fluid (container-fluid) or not.
 * @param containerBreakpoint Breakpoint for the inner container.
 * @param colorScheme Valid values are Color.Light or Color.Dark to set the navbar-dark/light class.
 * @param backgroundColor Background color to use, with the bg-* classes.
 * @param content Navbar content, placed within the inner container.
 * @param toggler Whether or not a toggler button should be provided when falling below the breakpoint.
 * @param togglerLeft True if the toggler should be placed on the left of the brand, false if on the right.
 * @param togglerAttrs Additional attributes to set on the toggler button
 * @param attrs Additional attributes to set on the Navbar
 * @param brand Composable implementing the brand elements
 * @param navItems navigation items, normally comprised of NavbarLink and NavbarDropDown menu items.
 */
@Composable
public fun Navbar(
    breakpoint: Breakpoint = Breakpoint.Medium,
    fluid: Boolean = false,
    containerBreakpoint: Breakpoint? = null,
    colorScheme: Color = Color.Light,
    backgroundColor: Color = Color.Primary,
    toggler: Boolean = true,
    togglerLeft: Boolean = true,
    togglerAttrs: AttrsBuilder<HTMLButtonElement>.() -> Unit = { },
    attrs: AttrsBuilder<HTMLElement>.() -> Unit = { },
    brand: (@Composable () -> Unit),
    navItems: (@Composable () -> Unit)
) {
    Navbar(
        breakpoint,
        fluid,
        containerBreakpoint,
        colorScheme,
        backgroundColor,
        attrs
    ) {
        if (!togglerLeft) {
            brand()
        }

        if (toggler) {
            val targetId = "toggler${UUID().toString()}"
            Toggler(
                target = targetId,
                controls = targetId,
                togglerAttrs
            )
            NavbarCollapse(targetId) {
                NavbarNav {
                    navItems()
                }
            }
        } else {
            NavbarNav {
                navItems()
            }
        }

        if (togglerLeft) {
            brand()
        }
    }
}


/**
 * Bootstrap navbar-collapse component to be used with the NavbarToggler.
 * @param id The element id. This value should also be used as the target parameter with a NavbarToggler.
 */
@Composable
public fun NavbarCollapse(
    id: String,
    attrs: AttrsBuilder<HTMLDivElement>.() -> Unit = {},
    content: ContentBuilder<HTMLDivElement>? = null
) {
    Div(attrs = {
        classes(BSClasses.collapse, BSClasses.navbarCollapse)
        attr("id", id)
        attrs()
    }) {
        content?.invoke(this)
    }
}

/**
 * Bootstrap navbar-nav component to be used within a Navbar.
 */
@Composable
public fun NavbarNav(
    attrs: AttrsBuilder<HTMLDivElement>.() -> Unit = {},
    links: ContentBuilder<HTMLDivElement>? = null
) {
    Div(attrs = {
        classes(BSClasses.navbarNav)
        attrs()
    }) {
        links?.invoke(this)
    }
}


/**
 * Bootstrap nav-link component.
 */
@Composable
public fun NavbarLink(
    active: Boolean,
    attrs: AttrsBuilder<HTMLAnchorElement>.() -> Unit = {},
    disabled: Boolean = false,
    link: String? = null,
    content: ContentBuilder<HTMLAnchorElement>? = null
) {
    A(attrs = {
        classes(BSClasses.navLink)
        if (active) {
            classes(BSClasses.active)
            attr("aria-current", "page")
        }
        attr("disabled", disabled.toString())
        link?.let{
            attr("href", it)
        }
        attrs()
    }) {
        content?.invoke(this)
    }
}
