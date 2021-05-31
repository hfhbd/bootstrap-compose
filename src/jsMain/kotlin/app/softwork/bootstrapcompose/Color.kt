package app.softwork.bootstrapcompose

public enum class Color(private val value: String) {
    Primary("primary"),
    Secondary("secondary"),
    Success("success"),
    Info("info"),
    Warning("warning"),
    Danger("danger"),
    Light("light"),
    Dark("dark");

    override fun toString(): String = value
}