package app.softwork.bootstrapcompose

import org.jetbrains.compose.web.css.CSSLengthValue
import org.jetbrains.compose.web.css.px

public enum class Breakpoint(private val classInfix: String) {
    Small("sm"),
    Medium("md"),
    Large("lg"),
    ExtraLarge("xl"),
    ExtraExtraLarge("xxl");

    override fun toString(): String = classInfix
}

/**
 * Breakpoint thresholds that match Bootstrap defaults. If the defaults were overridden in the
 * css (e.g. using sass to generate a custom Bootstrap css), then this variable should be updated with a
 * new map that matches before using classes/functions that create
 * new styles in a media query. If this is not done, the breakpoints won't be consistent with modified
 * bootstrap css classes.
 */
public var breakpoints: Map<Breakpoint, CSSLengthValue> = mapOf(
    Breakpoint.Small to 576.px,
    Breakpoint.Medium to 768.px,
    Breakpoint.Large to 992.px,
    Breakpoint.ExtraLarge to 1200.px,
    Breakpoint.ExtraExtraLarge to 1400.px,
)