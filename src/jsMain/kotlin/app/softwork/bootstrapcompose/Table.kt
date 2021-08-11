package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*
import kotlin.math.*

public typealias CurrentPage<T> = Table.OffsetPagination.Page<T>

public object Table {
    public data class Column(
        val title: String,
        val scope: Scope?,
        val header: Header?,
        val cell: Cell,
        val footer: Footer?
    )

    public data class Cell internal constructor(public val color: Color? = null, val scope: Scope?) {
        internal lateinit var content: ContentBuilder<HTMLTableCellElement>

        public constructor(
            color: Color? = null,
            scope: Scope? = null,
            content: ContentBuilder<HTMLTableCellElement>
        ) : this(color = color, scope) {
            this.content = content
        }
    }

    public data class Footer internal constructor(public val color: Color? = null) {
        internal lateinit var content: @Composable ElementScope<HTMLTableCellElement>.(List<Cell>) -> Unit

        public constructor(
            color: Color? = null,
            content: @Composable ElementScope<HTMLTableCellElement>.(List<Cell>) -> Unit
        ) : this(color = color) {
            this.content = content
        }
    }

    public data class Header(
        val attrs: AttrBuilderContext<HTMLTableCellElement>? = null,
    ) {
        //would like to make this a constructor val...except for https://github.com/JetBrains/compose-jb/issues/746
        var content: ContentBuilder<HTMLTableCellElement>? = null

        public constructor(
            color: Color? = null,
        ) : this(attrs = { classes("table-$color") })

        public constructor(
            color: Color? = null,
            content: ContentBuilder<HTMLTableCellElement>? = null
        ) : this(attrs = { classes("table-$color") }) {
            this.content = content
        }

        public constructor(
            attrs: AttrBuilderContext<HTMLTableCellElement>? = null,
            content: ContentBuilder<HTMLTableCellElement>? = null
        ) : this(attrs) {
            this.content = content
        }
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
                    cell = Cell(cellColor, scope, cell)
                )
            )
        }
    }

    public class FixedHeaderProperty(
        public val size: CSSLengthOrPercentageValue,
        //This can conflict with Header.color, and if color classes are applied with Header, this will take precedence and force White
        //public val background: Color = Color.White
    )

    public class OffsetPagination<T>(
        public val entriesPerPageLimit: State<Int>,
        public val numberOfButtons: Int = 5,
        public val actionNavigateBack: ((CurrentPage<T>, Page<T>) -> Unit)? = null,
        public val actionNavigateForward: ((CurrentPage<T>, Page<T>) -> Unit)? = null,
    ) {

        public constructor(
            entriesPerPageLimit: State<Int>,
            actionNavigateBack: ((CurrentPage<T>, Page<T>) -> Unit)?,
            actionNavigateForward: ((CurrentPage<T>, Page<T>) -> Unit)?,
            control: @Composable ElementScope<HTMLDivElement>.(List<Page<T>>, Page<T>, (Int) -> Unit) -> Unit
        ) : this(
            entriesPerPageLimit = entriesPerPageLimit,
            actionNavigateBack = actionNavigateBack,
            actionNavigateForward = actionNavigateForward
        ) {
            this.control = control
        }

        public var control: @Composable ElementScope<HTMLDivElement>.(List<Page<T>>, Page<T>, (Int) -> Unit) -> Unit =
            { pages, currentPage, goTo ->
                Column(horizontalAlignment = HorizontalAlignment.Start) {
                    ButtonGroup {
                        Button(title = "<", disabled = currentPage.index == 0) {
                            val previousIndex = currentPage.index - 1
                            actionNavigateBack?.invoke(currentPage, pages[previousIndex])
                            goTo(previousIndex)
                        }

                        val buttons = tableCalcButtons(
                            index = currentPage.index,
                            pages = pages.size,
                            numberOfButtons = numberOfButtons
                        )

                        for (index in buttons) {
                            if (index == currentPage.index) {
                                Button(title = "$index", disabled = true) { }
                            } else {
                                Button(title = "$index") {
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

                        Button(title = ">", disabled = currentPage.index == pages.lastIndex) {
                            val nextIndex = currentPage.index + 1
                            actionNavigateForward?.invoke(currentPage, pages[nextIndex])
                            goTo(nextIndex)
                        }
                    }
                }
                if (entriesPerPageLimit is MutableState<Int>) {
                    val initLimit = remember { entriesPerPageLimit.value }
                    Column(horizontalAlignment = HorizontalAlignment.End, auto = true) {
                        DropDown("# ${entriesPerPageLimit.value}", size = ButtonSize.Small) {
                            List(4) {
                                initLimit * (it + 1)
                            }.forEach {
                                this.Button("$it") {
                                    entriesPerPageLimit.value = it
                                }
                            }
                        }
                    }
                }
            }

        public data class Page<T>(val index: Int, val items: List<T>, val numberOfPages: Int)
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
public fun <T> Table(
    data: List<T>,
    key: ((T) -> Any)? = null,
    color: Color? = null,
    striped: Boolean = false,
    hover: Boolean = false,
    borderless: Boolean = false,
    small: Boolean = false,
    fixedHeader: Table.FixedHeaderProperty? = null,
    caption: ContentBuilder<HTMLTableCaptionElement>? = null,
    captionTop: Boolean = false,
    pagination: Table.OffsetPagination<T>? = null,
    attrs: AttrBuilderContext<HTMLTableElement>? = null,
    map: Table.Builder.(Int, T) -> Unit
) {
    val headers = mutableMapOf<String, Table.Header?>()
    val _footers = mutableListOf<Table.Footer>()

    val pages = if (pagination != null) {
        val pages = data.chunked(pagination.entriesPerPageLimit.value)
        pages.mapIndexed { index, data ->
            Table.OffsetPagination.Page(index, data, pages.size)
        }
    } else listOf(Table.OffsetPagination.Page(0, data, 1))

    var currentIndex by remember { mutableStateOf(0) }
    val currentPage = pages[min(currentIndex, pages.lastIndex)]
    val baseIndex = currentPage.index * (pagination?.entriesPerPageLimit?.value ?: 0)

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
        if (striped) {
            classes("table-striped")
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
                headers.forEach { (title, header) ->
                    Th(attrs = {
                        scope(Scope.Col)
                        header?.attrs?.invoke(this)
                        if (fixedHeader != null) {
                            classes("sticky-top")
                            style {
                                top(fixedHeader.size)
                                property("z-index", "auto")
                            }
                        }
                    }) {
                        header?.content?.invoke(this)
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
    if (pagination != null) {
        Row {
            val control = pagination.control
            control(pages, currentPage) {
                currentIndex = it
            }
        }
    }
}
