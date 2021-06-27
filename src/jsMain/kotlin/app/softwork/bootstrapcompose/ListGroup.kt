package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.AttrsBuilder
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.*


@Composable
public fun ListGroup(
    flush: Boolean = false,
    listGroupDirection: ListGroupDirection = ListGroupDirection.Vertical,
    attrs: AttrBuilderContext<HTMLUListElement>? = null,
    content: ContentBuilder<HTMLUListElement>? = null
) {
    Ul(attrs = {
        ListGroupAttrs(flush, false, listGroupDirection, attrs)
    }
    ) {
        content?.invoke(this)
    }
}

@Composable
public fun NumberedListGroup(
    flush: Boolean = false,
    listGroupDirection: ListGroupDirection = ListGroupDirection.Vertical,
    attrs: AttrBuilderContext<HTMLOListElement>? = null,
    content: ContentBuilder<HTMLOListElement>? = null
) {
    Ol(attrs = {
        ListGroupAttrs(flush, true, listGroupDirection, attrs)
    }
    ) {
        content?.invoke(this)
    }
}

private fun <T : HTMLElement> AttrsBuilder<T>.ListGroupAttrs(
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
public fun DOMScope<HTMLUListElement>.ListItem(
    active: Boolean = false,
    disabled: Boolean = false,
    background: Color? = null,
    attrs: AttrBuilderContext<HTMLLIElement>? = null,
    content: ContentBuilder<HTMLLIElement>? = null
) {
    Li(attrs = {
        ListItemAttrs(active, disabled, false, background, attrs)
    }) {
        content?.invoke(this)
    }
}

@Composable
public fun DOMScope<HTMLOListElement>.ListItem(
    active: Boolean = false,
    disabled: Boolean = false,
    background: Color? = null,
    attrs: AttrBuilderContext<HTMLLIElement>? = null,
    content: ContentBuilder<HTMLLIElement>? = null
) {
    Li(attrs = {
        ListItemAttrs(active, disabled, false, background, attrs)
    }) {
        content?.invoke(this)
    }
}

@Composable
public fun AnchorListItem(
    href: String? = null,
    active: Boolean = false,
    disabled: Boolean = false,
    background: Color? = null,
    attrs: AttrBuilderContext<HTMLAnchorElement>? = null,
    content: ContentBuilder<HTMLAnchorElement>? = null
) {
    A(
        href = href,
        attrs = {
            ListItemAttrs(active, disabled, true, background, attrs)
        }) {
        content?.invoke(this)
    }
}

@Composable
public fun ButtonListItem(
    active: Boolean = false,
    disabled: Boolean = false,
    background: Color? = null,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null
) {
    Button(attrs = {
        ListItemAttrs(active, disabled, true, background, attrs)
    }) {
        content?.invoke(this)
    }
}

private fun <T : HTMLElement> AttrsBuilder<T>.ListItemAttrs(
    active: Boolean = false,
    disabled: Boolean = false,
    actionable: Boolean = false,
    background: Color? = null,
    attrs: AttrBuilderContext<T>? = null,
) {
    classes(BSClasses.listGroupItem)
    if (active) {
        classes("active")
        attr("aria-current", "true")
    }
    if (disabled) {
        classes("disabled")
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
