package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

public data class Row(val color: Color?, val cells: List<Cell>, val key: Any?) {
    public class Cell(public val color: Color?) {
        public lateinit var content: @Composable () -> Unit
    }

    public class Builder {
        private val values = mutableListOf<Pair<String, Cell>>()

        public var rowColor: Color? = null

        internal fun build() = values to rowColor

        @Composable
        public fun column(title: String, color: Color? = null, block: @Composable () -> Unit) {
            values.add(title to Cell(color).apply { content = block })
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
    map: @Composable Row.Builder.(Int, T) -> Unit
) {
    val headers = mutableListOf<String>()
    val rows = mutableListOf<Row>()

    data.forEachIndexed { index, it ->
        val (rowValues, rowColor) = Row.Builder().apply {
            map(index, it)
        }.build()
        val cells = rowValues.map { (header, cellValue) ->
            if (header !in headers) {
                headers.add(header)
            }
            cellValue
        }
        val row = Row(rowColor, cells, key = key?.invoke(it))
        rows.add(row)
    }
    Table(
        color = color,
        striped = striped,
        hover = hover,
        headers = headers,
        rows = rows
    )
}

@Composable
public fun Table(
    color: Color? = null,
    striped: Boolean = false,
    hover: Boolean = false,
    headers: List<String>,
    rows: List<Row>
) {
    Table(attrs = {
        classes("table")
        color?.let { classes("table-$it") }
        if (hover) {
            classes("table-hover")
        }
        if (striped) {
            classes("table-striped")
        }
    }) {
        Thead {
            Tr {
                for (header in headers) {
                    Th(attrs = {
                        scope(Scope.Col)
                    }) {
                        Text(header)
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
                            }) {
                                cell.content()
                            }
                        }
                    }
                }
            }
        }
    }
}
