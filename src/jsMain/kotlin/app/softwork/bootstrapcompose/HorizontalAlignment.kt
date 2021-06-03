package app.softwork.bootstrapcompose;

public enum class HorizontalAlignment(private val classInfix: String) {
    Start("start"),
    Center("center"),
    End("end"),
    Around("around"),
    Between("between"),
    Evenly("evenly");

    override fun toString(): String = "justify-content-$classInfix"
}