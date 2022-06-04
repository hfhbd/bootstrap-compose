package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*

@Composable
public fun ListGroup(
    flush: Boolean = false,
    listGroupDirection: ListGroupDirection = ListGroupDirection.Vertical,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLUListElement>? = null,
    content: ContentBuilder<HTMLUListElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Ul(
        attrs = {
            ListGroupAttrs(flush, false, listGroupDirection, attrs)
            classes(*classes)
        },
        content = content
    )
}

@Composable
public fun NumberedListGroup(
    flush: Boolean = false,
    listGroupDirection: ListGroupDirection = ListGroupDirection.Vertical,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLOListElement>? = null,
    content: ContentBuilder<HTMLOListElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Ol(
        attrs = {
            ListGroupAttrs(flush, true, listGroupDirection, attrs)
            classes(*classes)
        },
        content = content
    )
}

private fun <T : HTMLElement> AttrsScope<T>.ListGroupAttrs(
    flush: Boolean = false,
    numbered: Boolean = false,
    listGroupDirection: ListGroupDirection = ListGroupDirection.Vertical,
    attrs: AttrBuilderContext<T>? = null,
) {
    classes(BSClasses.listGroup)
    if (flush) {
        classes(BSClasses.listGroupFlush)
    }
    if (numbered) {
        classes(BSClasses.listGroupNumbered)
    }

    if (listGroupDirection is ListGroupDirection.Horizontal) {
        classes(listGroupDirection.classname())
    }
    attrs?.invoke(this)
}

@Composable
public fun ListItem(
    active: Boolean = false,
    disabled: Boolean = false,
    background: Color? = null,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLLIElement>? = null,
    content: ContentBuilder<HTMLLIElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Li(attrs = {
        ListItemAttrs(active, disabled, false, background, attrs)
        classes(*classes)
    }, content = content)
}

@Composable
public fun AnchorListItem(
    href: String? = null,
    active: Boolean = false,
    disabled: Boolean = false,
    background: Color? = null,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    content: ContentBuilder<HTMLAnchorElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    A(
        href = href,
        attrs = {
            ListItemAttrs(active, disabled, true, background, attrs)
            classes(*classes)
        }, content = content
    )
}

@Composable
public fun ButtonListItem(
    active: Boolean = false,
    disabled: Boolean = false,
    background: Color? = null,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null
) {
    Style
    val classes = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Button(attrs = {
        ListItemAttrs(active, disabled, true, background, attrs)
        classes(*classes)
    }, content = content)
}

private fun <T : HTMLElement> AttrsScope<T>.ListItemAttrs(
    active: Boolean = false,
    disabled: Boolean = false,
    actionable: Boolean = false,
    background: Color? = null,
    attrs: AttrBuilderContext<T>? = null,
) {
    classes(BSClasses.listGroupItem)
    if (active) {
        classes(BSClasses.active)
        attr("aria-current", "true")
    }
    if (disabled) {
        classes(BSClasses.disabled)
        attr("aria-disabled", "true")
    }
    if (actionable) {
        classes(BSClasses.listGroupItemAction)
    }
    background?.let {
        classes("${BSClasses.listGroupItem}-$it")
    }
    attrs?.invoke(this)
}

/**
 * Specifies a ListGroup's direction, and optionally for the Horizontal variant a breakpoint.
 */
public sealed class ListGroupDirection {
    public object Vertical : ListGroupDirection()
    public data class Horizontal(val breakpoint: Breakpoint? = null) : ListGroupDirection() {
        internal fun classname(): String {
            return "${BSClasses.listGroupHorizontal}${breakpoint?.let { "-$it" } ?: ""}"
        }
    }
}
