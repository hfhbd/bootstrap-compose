package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*

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
    internal lateinit var content: ContentBuilder<HTMLTableCellElement>

    public constructor(color: Color? = null, content: ContentBuilder<HTMLTableCellElement>) : this(color = color) {
        this.content = content
    }
}

public data class Header(val color: Color? = null) {
    var content: ContentBuilder<HTMLDivElement>? = null

    public constructor(color: Color? = null, content: ContentBuilder<HTMLDivElement>? = null) : this(color) {
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
        values.add(
            Column(
                title = title,
                scope = scope,
                header = header,
                footer = footer,
                cell = Cell(
                    cellColor, scope, cell
                )
            )
        )
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
    fixedHeader: FixedHeaderProperty? = null,
    caption: ContentBuilder<HTMLTableCaptionElement>? = null,
    captionTop: Boolean = false,
    map: Builder.(Int, T) -> Unit
) {
    val headers = mutableMapOf<String, Header?>()
    val _footers = mutableListOf<Footer>()

    val rows = data.mapIndexed { index, it ->
        val (columns, rowColor) = Builder().apply {
            map(index, it)
        }.build()
        val cells = columns.map {
            headers[it.title] = it.header
            if (it.footer != null) {
                _footers.add(it.footer)
            }
            it.cell
        }
        Row(color = rowColor, cells = cells, key = key?.invoke(it))
    }
    check(rows.all { it.cells.size == headers.size })
    val footers = _footers.takeUnless { it.isEmpty() }
    if (footers != null) {
        check(rows.all { it.cells.size == footers.size })
    }
    Table(
        color = color,
        striped = striped,
        hover = hover,
        borderless = borderless,
        small = small,
        fixedHeader = fixedHeader,
        caption = caption,
        captionTop = captionTop,
        headers = headers.toList(),
        footers = footers,
        rows = rows
    )
}

public class FixedHeaderProperty(
    public val size: CSSLengthOrPercentageValue,
    public val background: Color = Color.White
)

@Composable
public fun Table(
    color: Color? = null,
    striped: Boolean = false,
    hover: Boolean = false,
    borderless: Boolean = false,
    small: Boolean = false,
    fixedHeader: FixedHeaderProperty? = null,
    caption: ContentBuilder<HTMLTableCaptionElement>? = null,
    captionTop: Boolean = false,
    headers: List<Pair<String, Header?>>,
    footers: List<Footer>? = null,
    rows: List<Row>,
) {
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
    }) {
        if (caption != null) {
            Caption(content = caption)
        }
        Thead {
            Tr {
                headers.forEach { (title, header) ->
                    Th(attrs = {
                        scope(Scope.Col)
                        val color = header?.color
                        if (color != null) {
                            classes("table-$color")
                        }
                        if (fixedHeader != null) {
                            style {
                                position(Position.Sticky)
                                top(fixedHeader.size)
                                if (color == null) {
                                    background(fixedHeader.background.toString())
                                }
                            }
                        }
                    }) {
                        val content = header?.content
                        if (content != null) {
                            Row {
                                Column { Text(title) }
                                Column(auto = true, content = content)
                            }
                        } else {
                            Text(title)
                        }
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
                    for (cell in footers) {
                        Td(attrs = {
                            cell.color?.let { classes("table-$it") }
                        }) {
                            cell.content(this)
                        }
                    }
                }
            }
        }
    }
}
