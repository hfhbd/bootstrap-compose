package app.softwork.bootstrapcompose

public enum class Color(private val value: String) {
    Primary("primary"),
    Secondary("secondary"),
    Success("success"),
    Info("info"),
    Warning("warning"),
    Danger("danger"),
    Light("light"),
    Dark("dark"),
    Body("body"),
    Muted("muted"),
    White("white"),
    Black50("black-50"),
    White50("white-50"),
    Transparent("transparent");

    override fun toString(): String = value

    public fun background(): String = "bg-$value"
    public fun text(): String = "text-$value"
}
