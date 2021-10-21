package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

@Composable
public fun Pagination(
    size: PaginationSize = PaginationSize.Normal,
    styling: (Styling.() -> Unit)? = null,
    attrs: AttrBuilderContext<HTMLUListElement>? = null,
    content: @Composable PaginationScope.() -> Unit
) {
    val style = styling?.let {
        Styling().apply(it).generate()
    } ?: arrayOf()

    Ul(attrs = {
        classes("pagination")
        classes(size.toString())
        classes(*style)
        attrs?.invoke(this)
    }) {
        PaginationScope().content()
    }
}

public enum class PaginationSize(private val classes: String) {
    Normal(""), Small("pagination-sm"), Large("pagination-lg");

    override fun toString(): String = classes
}

public class PaginationScope {
    @Composable
    public fun PageItem(active: Boolean = false, disabled: Boolean = false, content: ContentBuilder<HTMLLIElement>) {
        Li(attrs = {
            classes("page-item")
            if (active) {
                classes(BSClasses.active)
            }
            if (disabled) {
                classes(BSClasses.disabled)
            }
        }) {
            content()
        }
    }

    @Composable
    public fun PageLink(title: String, onClick: () -> Unit) {
        A(attrs = {
            classes("page-link")
            onClick {
                onClick()
            }
        }) {
            Text(title)
        }
    }
}
