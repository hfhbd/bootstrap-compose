package app.softwork.bootstrapcompose

@OptIn(ExperimentalStdlibApi::class)
@EagerInitialization
internal val loadCss: Unit = run {
    require("bootstrap/dist/css/bootstrap.min.css")
    Unit
}

internal external fun require(module: String): dynamic
