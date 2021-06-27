package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
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
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null
) {
    Nav(attrs = {
        classes(
            BSClasses.navbar,
            "navbar-expand-$breakpoint",
            "navbar-${colorScheme}",
            "bg-${backgroundColor}"
        )
        attr("role", "navigation")
        attrs?.invoke(this)
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
 * @param toggler Whether or not a toggler button should be provided when falling below the breakpoint.
 * @param togglerPosition Specifies if the toggler should be placed on the left or right side of the Navbar.
 * @param togglerAttrs Additional attributes to set on the toggler button
 * @param togglerTargetId Optional id of the toggler, using a random UUID by default
 * @param attrs Additional attributes to set on the Navbar
 * @param navAttrs Additional attributes to set on the navItems section of the Navbar
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
    togglerPosition: TogglerPosition = TogglerPosition.Right,
    togglerTargetId: String = "toggler${UUID()}",
    togglerAttrs: AttrBuilderContext<HTMLButtonElement>? = null,
    attrs: AttrBuilderContext<HTMLElement>? = null,
    navAttrs: AttrBuilderContext<HTMLDivElement>? = null,
    brand: ContentBuilder<HTMLDivElement>,
    navItems: ContentBuilder<HTMLDivElement>
) {
    Navbar(
        breakpoint,
        fluid,
        containerBreakpoint,
        colorScheme,
        backgroundColor,
        attrs
    ) {
        if (togglerPosition == TogglerPosition.Right) {
            brand()
        }

        if (toggler) {
            Toggler(
                target = togglerTargetId,
                controls = togglerTargetId,
                togglerAttrs
            )
            NavbarCollapse(togglerTargetId) {
                NavbarNav(attrs = { navAttrs?.invoke(this) }) {
                    navItems()
                }
            }
        } else {
            NavbarNav {
                navItems()
            }
        }

        if (togglerPosition == TogglerPosition.Left) {
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
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: ContentBuilder<HTMLDivElement>? = null
) {
    Div(attrs = {
        classes(BSClasses.collapse, BSClasses.navbarCollapse)
        id(id)
        attrs?.invoke(this)
    }) {
        content?.invoke(this)
    }
}

/**
 * Bootstrap navbar-nav component to be used within a Navbar.
 */
@Composable
public fun NavbarNav(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    links: ContentBuilder<HTMLDivElement>? = null
) {
    Div(attrs = {
        classes(BSClasses.navbarNav)
        attrs?.invoke(this)
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
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
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
        if (disabled) {
            classes("disabled")
        }
        attrs?.invoke(this)
    }, href = link) {
        content?.invoke(this)
    }
}

public enum class TogglerPosition {
    Left, Right
}
