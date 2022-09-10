package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*
import kotlin.math.*

public typealias CurrentPage<T> = Table.Pagination.Page<T>
public typealias PageControl<T> = @Composable ElementScope<HTMLDivElement>.(
    List<Table.Pagination.Page<T>>,
    CurrentPage<T>,
    (Int) -> Unit
) -> Unit

public object Table {
    public data class Column(
        val title: String,
        val scope: Scope?,
        val header: Header,
        val cell: Cell,
        val footer: Footer?
    )

    public data class Cell internal constructor(
        public val color: Color? = null,
        val scope: Scope?,
        val verticalAlignment: Layout.VerticalAlignment?,
        val content: ContentBuilder<HTMLTableCellElement>
    )

    public data class Footer internal constructor(
        public val color: Color? = null,
        val content: @Composable ElementScope<HTMLTableCellElement>.(List<Cell>) -> Unit
    )

    public data class Header(
        val attrs: AttrBuilderContext<HTMLTableCellElement>? = null,
        var content: ContentBuilder<HTMLTableCellElement>? = null
    ) {
        public constructor(
            color: Color,
        ) : this(attrs = { classes("table-$color") })

        public constructor(
            color: Color,
            content: ContentBuilder<HTMLTableCellElement>? = null
        ) : this(attrs = { classes("table-$color") }, content)
    }

    public data class Row(val cells: List<Cell>, public val key: Any?, val color: Color? = null)

    public class Builder internal constructor() {

        private val values = mutableListOf<Column>()

        public var rowColor: Color? = null

        internal fun build(): Pair<List<Column>, Color?> = values to rowColor

        public fun column(
            title: String,
            scope: Scope? = null,
            header: Header? = null,
            footer: Footer? = null,
            cellColor: Color? = null,
            verticalAlignment: Layout.VerticalAlignment? = null,
            cell: ContentBuilder<HTMLTableCellElement>
        ) {
            val titledHeader = when {
                header != null && header.content == null -> {
                    header.content = { Text(title) }
                    header
                }
                header == null -> {
                    Header(attrs = null) { Text(title) }
                }
                else -> header
            }

            values.add(
                Column(
                    title = title,
                    scope = scope,
                    header = titledHeader,
                    footer = footer,
                    cell = Cell(cellColor, scope, verticalAlignment, cell)
                )
            )
        }
    }

    public class FixedHeaderProperty(public val style: StyleScope.() -> Unit) {
        public constructor(topSize: CSSLengthOrPercentageValue, zIndex: ZIndex) : this({
            top(topSize)
            property("z-index", zIndex.unsafeCast<String>())
        })
    }

    public interface Pagination<T> {
        public enum class Position {
            Top, Bottom
        }

        public val position: Position?
        public val pages: State<List<Page<T>>>

        public val numberOfButtons: Int
        public val entriesPerPageLimit: State<Int>?
        public val actionNavigateBack: ((CurrentPage<T>, Page<T>) -> Unit)?
        public val actionNavigateForward: ((CurrentPage<T>, Page<T>) -> Unit)?

        public val startPageIndex: Int

        public data class Page<T>(val index: Int, val items: List<T>, val numberOfPages: Int)

        public val control: PageControl<T>

        public fun defaultControl(): PageControl<T> = { pages, currentPage, goTo ->
            Column {
                Pagination(size = PaginationSize.Small) {
                    PageItem(disabled = currentPage.index == 0) {
                        PageLink("<") {
                            val previousIndex = currentPage.index - 1
                            actionNavigateBack?.invoke(currentPage, pages[previousIndex])
                            goTo(previousIndex)
                        }
                    }

                    val buttons = tableCalcButtons(
                        index = currentPage.index,
                        pages = pages.size,
                        numberOfButtons = numberOfButtons
                    )

                    for (index in buttons) {
                        if (index == currentPage.index) {
                            PageItem(active = true, disabled = false) {
                                PageLink("${index + 1}") { }
                            }
                        } else {
                            PageItem {
                                PageLink("${index + 1}") {
                                    if (index < currentPage.index) {
                                        actionNavigateBack?.invoke(currentPage, pages[index])
                                        goTo(index)
                                    } else {
                                        actionNavigateForward?.invoke(currentPage, pages[index])
                                        goTo(index)
                                    }
                                }
                            }
                        }
                    }
                    PageItem(disabled = currentPage.index == pages.lastIndex) {
                        PageLink(">") {
                            val nextIndex = currentPage.index + 1
                            actionNavigateForward?.invoke(currentPage, pages[nextIndex])
                            goTo(nextIndex)
                        }
                    }
                }
            }
            if (entriesPerPageLimit is MutableState<Int>) {
                val initLimit = remember { entriesPerPageLimit!!.value }
                Column(styling = {
                    Margins {
                        End {
                            size = SpacingSpecs.SpacingSize.Auto
                        }
                    }
                }, auto = true) {
                    DropDown("# ${entriesPerPageLimit!!.value}", size = ButtonSize.Small) {
                        List(4) {
                            initLimit * (it + 1)
                        }.forEach {
                            this.Button("$it") {
                                (entriesPerPageLimit as MutableState<Int>).value = it
                            }
                        }
                    }
                }
            }
        }
    }

    public class OffsetPagination<T>(
        data: List<T>,
        public override val entriesPerPageLimit: State<Int>?,
        public override val startPageIndex: Int = 0,
        public override val position: Pagination.Position? = Pagination.Position.Bottom,
        public override val numberOfButtons: Int = 5,
        public override val actionNavigateBack: ((CurrentPage<T>, Pagination.Page<T>) -> Unit)? = null,
        public override val actionNavigateForward: ((CurrentPage<T>, Pagination.Page<T>) -> Unit)? = null,
    ) : Pagination<T> {

        override val pages: State<List<Pagination.Page<T>>> =
            data.chunked(entriesPerPageLimit?.value ?: data.size).let {
                mutableStateOf(
                    it.mapIndexed { index, data ->
                        Pagination.Page(index, data, it.size)
                    }
                )
            }

        override var control: PageControl<T> = defaultControl()

        public constructor(
            data: List<T>,
            entriesPerPageLimit: State<Int>,
            startPageIndex: Int = 0,
            position: Pagination.Position = Pagination.Position.Bottom,
            numberOfButtons: Int = 5,
            actionNavigateBack: ((CurrentPage<T>, Pagination.Page<T>) -> Unit)? = null,
            actionNavigateForward: ((CurrentPage<T>, Pagination.Page<T>) -> Unit)? = null,
            control: PageControl<T>
        ) : this(
            data,
            entriesPerPageLimit,
            startPageIndex,
            position,
            numberOfButtons,
            actionNavigateBack,
            actionNavigateForward
        ) {
            this.control = control
        }
    }
}

internal fun tableCalcButtons(index: Int, pages: Int, numberOfButtons: Int): IntRange {
    if (pages <= numberOfButtons) {
        return 0 until pages
    }
    val nr = min(pages, numberOfButtons)
    val max = pages - 1
    return when (index) {
        0 -> 0 until nr
        (pages - 1) -> (max((pages - nr), 0)) until pages
        else -> {
            val half = nr / 2
            val lower = max(index - half, 0)
            val upper = (min(index + half, max))
            if (lower == 0) {
                0 until nr
            } else if (upper == max) {
                val newLower = pages - numberOfButtons
                newLower until pages
            } else lower..upper
        }
    }
}

@Composable
@NonRestartableComposable
public fun <T> Table(
    data: List<T>,
    key: ((T) -> Any)? = null,
    color: Color? = null,
    stripedRows: Boolean = false,
    stripedColumns: Boolean = false,
    hover: Boolean = false,
    borderless: Boolean = false,
    small: Boolean = false,
    fixedHeader: Table.FixedHeaderProperty? = null,
    caption: ContentBuilder<HTMLTableCaptionElement>? = null,
    captionTop: Boolean = false,
    attrs: AttrBuilderContext<HTMLTableElement>? = null,
    map: Table.Builder.(Int, T) -> Unit
) {
    Table(
        pagination = Table.OffsetPagination(data, null, position = null),
        key,
        color,
        stripedRows = stripedRows,
        stripedColumns = stripedColumns,
        hover,
        borderless,
        small,
        fixedHeader,
        caption,
        captionTop,
        attrs,
        map
    )
}

@Composable
public fun <T> Table(
    pagination: Table.Pagination<T>,
    key: ((T) -> Any)? = null,
    color: Color? = null,
    stripedRows: Boolean = false,
    stripedColumns: Boolean = false,
    hover: Boolean = false,
    borderless: Boolean = false,
    small: Boolean = false,
    fixedHeader: Table.FixedHeaderProperty? = null,
    caption: ContentBuilder<HTMLTableCaptionElement>? = null,
    captionTop: Boolean = false,
    attrs: AttrBuilderContext<HTMLTableElement>? = null,
    map: Table.Builder.(Int, T) -> Unit
) {
    Style
    val headers = mutableMapOf<String, Table.Header>()
    val _footers = mutableListOf<Table.Footer>()

    val pages by pagination.pages

    var currentIndex by remember { mutableStateOf(pagination.startPageIndex) }
    val currentPage = pages[min(currentIndex, pages.lastIndex)]
    val baseIndex = currentPage.index * (pagination.entriesPerPageLimit?.value ?: 0)

    val rows = currentPage.items.mapIndexed { itemIndexOfPage, item ->
        val (columns, rowColor) = Table.Builder().apply {
            val index = baseIndex + itemIndexOfPage
            map(index, item)
        }.build()
        val cells = columns.map {
            headers[it.title] = it.header
            if (it.footer != null) {
                _footers.add(it.footer)
            }
            it.cell
        }
        Table.Row(color = rowColor, cells = cells, key = key?.invoke(item))
    }
    check(rows.all { it.cells.size == headers.size })
    val footers = _footers.takeUnless { it.isEmpty() }
    if (footers != null) {
        check(rows.all { it.cells.size == footers.size })
    }

    if (pagination.position == Table.Pagination.Position.Top) {
        Row {
            val control = pagination.control
            control(pages, currentPage) {
                currentIndex = it
            }
        }
    }

    Table(attrs = {
        classes("table")
        if (captionTop) {
            classes("caption-top")
        }
        if (small) {
            classes("table-sm")
        }
        color?.let { classes("table-$it") }
        if (hover) {
            classes("table-hover")
        }
        if (stripedRows) {
            classes("table-striped")
        }
        if (stripedColumns) {
            classes("table-striped-columns")
        }
        if (borderless) {
            classes("table-borderless")
        }
        attrs?.invoke(this)
    }) {
        if (caption != null) {
            Caption(content = caption)
        }
        Thead {
            Tr {
                headers.forEach { (_, header) ->
                    Th(attrs = {
                        scope(Scope.Col)
                        header.attrs?.invoke(this)
                        if (fixedHeader != null) {
                            classes("sticky-top")
                            style {
                                fixedHeader.style(this)
                            }
                        }
                    }) {
                        header.content?.invoke(this)
                    }
                }
            }
        }
        Tbody {
            for (row in rows) {
                key(row.key) {
                    Tr(attrs = {
                        row.color?.let { classes("table-$it") }
                    }) {
                        for (cell in row.cells) {
                            Td(attrs = {
                                cell.color?.let { classes("table-$it") }
                                cell.scope?.let { scope(it) }
                                cell.verticalAlignment?.let { classes(it.toString()) }
                            }) {
                                cell.content(this)
                            }
                        }
                    }
                }
            }
        }
        if (footers != null) {
            Tfoot {
                Tr {
                    footers.forEachIndexed { index, cell ->
                        Td(attrs = {
                            cell.color?.let { classes("table-$it") }
                        }) {
                            cell.content(this, rows[index].cells)
                        }
                    }
                }
            }
        }
    }
    if (pagination.position == Table.Pagination.Position.Bottom) {
        Row {
            val control = pagination.control
            control(pages, currentPage) {
                currentIndex = it
            }
        }
    }
}
