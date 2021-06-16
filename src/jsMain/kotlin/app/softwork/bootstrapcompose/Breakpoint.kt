package app.softwork.bootstrapcompose

public enum class Breakpoint(private val classInfix: String) {
    Small("sm"),
    Medium("md"),
    Large("lg"),
    ExtraLarge("xl"),
    ExtraExtraLarge("xxl");

    override fun toString(): String = classInfix
}
