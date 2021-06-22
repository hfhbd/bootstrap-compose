package app.softwork.bootstrapcompose

public object BSClasses {
    public const val active: String = "active"

    public val align: Align = Align
    public val display: Display = Display

    public const val collapse: String = "collapse"
    public const val navbar: String = "navbar"
    public const val navbarBrand: String = "navbar-brand"
    public const val navbarCollapse: String = "navbar-collapse"
    public const val navLink: String = "nav-link"
    public const val navbarNav: String = "navbar-nav"
    public const val navbarToggler: String = "navbar-toggler"
    public const val navbarTogglerIcon: String = "navbar-toggler-icon"
}

public object Align {
    public const val baseline: String = "align-baseline"
    public const val top: String = "align-top"
    public const val middle: String = "align-middle"
    public const val bottom: String = "align-bottom"
    public const val textTop: String = "align-text-top"
    public const val textBottom: String = "align-text-bottom"
}

public object Display {
    public val small: DisplayBreakpoint = DisplayBreakpoint(Breakpoint.Small)
    public val medium: DisplayBreakpoint = DisplayBreakpoint(Breakpoint.Medium)
    public val extraLarge: DisplayBreakpoint = DisplayBreakpoint(Breakpoint.ExtraLarge)
    public val extraExtraLarge: DisplayBreakpoint = DisplayBreakpoint(Breakpoint.ExtraExtraLarge)

    public const val none: String = "d-${DisplayValue.none}"
    public const val inline: String = "d-${DisplayValue.inline}"
    public const val inlineBlock: String = "d-${DisplayValue.inlineBlock}"
    public const val block: String = "d-${DisplayValue.block}"
    public const val grid: String = "d-${DisplayValue.grid}"
    public const val table: String = "d-${DisplayValue.table}"
    public const val tableCell: String = "d-${DisplayValue.tableCell}"
    public const val tableRow: String = "d-${DisplayValue.tableRow}"
    public const val flex: String = "d-${DisplayValue.flex}"
    public const val inlineFlex: String = "d-${DisplayValue.inlineFlex}"
}

public class DisplayBreakpoint(breakpoint: Breakpoint) {
    public val none: String = makeDisplayClass(breakpoint, DisplayValue.none)
    public val inline: String = makeDisplayClass(breakpoint, DisplayValue.inline)
    public val inlineBlock: String = makeDisplayClass(breakpoint, DisplayValue.inlineBlock)
    public val block: String = makeDisplayClass(breakpoint, DisplayValue.block)
    public val grid: String = makeDisplayClass(breakpoint, DisplayValue.grid)
    public val table: String = makeDisplayClass(breakpoint, DisplayValue.table)
    public val tableCell: String = makeDisplayClass(breakpoint, DisplayValue.tableCell)
    public val tableRow: String = makeDisplayClass(breakpoint, DisplayValue.tableRow)
    public val flex: String = makeDisplayClass(breakpoint, DisplayValue.flex)
    public val inlineFlex: String = makeDisplayClass(breakpoint, DisplayValue.inlineFlex)
}

private fun makeDisplayClass(breakpoint: Breakpoint, value: String): String {
    return "d-${breakpoint}-$value"
}

private object DisplayValue {
    const val none = "none"
    const val inline = "inline"
    const val inlineBlock = "inline-block"
    const val block = "block"
    const val grid = "grid"
    const val table = "table"
    const val tableCell = "table-cell"
    const val tableRow = "table-row"
    const val flex = "flex"
    const val inlineFlex = "inline-flex"
}