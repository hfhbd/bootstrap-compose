package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import androidx.compose.web.attributes.*
import androidx.compose.web.elements.*


public data class Row(val color: Color?, val cells: List<Cell>) {
    public data class Cell(val color: Color?, val content: @Composable () -> Unit)

    public class Builder {
        private val values = mutableListOf<Pair<String, Cell>>()

        public var rowColor: Color? = null

        internal fun build() = values to rowColor

        @Composable
        public fun column(title: String, color: Color? = null, content: @Composable () -> Unit) {
            values.add(title to Cell(color, content))
        }
    }
}

@Composable
public fun <T> Table(
    data: List<T>,
    color: Color? = null,
    striped: Boolean = false,
    hover: Boolean = false,
    map: @Composable Row.Builder.(T) -> Unit
) {
    val headers = mutableListOf<String>()
    val rows = mutableListOf<Row>()

    data.forEach {
        val (rowValues, rowColor) = Row.Builder().apply { map(it) }.build()
        val cells = rowValues.map { (header, cellValue) ->
            if (header !in headers) {
                headers.add(header)
            }
            cellValue
        }
        val row = Row(rowColor, cells)
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
        classes {
            +"table"
            color?.let { +"table-$it" }
            if (hover) {
                +"table-hover"
            }
            if (striped) {
                +"table-striped"
            }
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
                Tr(attrs = {
                    classes {
                        row.color?.let { +"table-$it" }
                    }
                }) {
                    for (cell in row.cells) {
                        Td(attrs = {
                            classes {
                                cell.color?.let { +"table-$it" }
                            }
                        }) {
                            cell.content()
                        }
                    }
                }
            }
        }
    }
}
