@file:Suppress(
    "NAME_CONTAINS_ILLEGAL_CHARS",
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
    "NOTHING_TO_INLINE",
)

package app.softwork.bootstrapcompose

// language=JavaScript
@JsName("""(/*union*/{auto: 'auto'}/*union*/)""")
public sealed external interface ZIndex {
    public companion object {
        public val auto: ZIndex
    }
}

public inline fun ZIndex(value: Int): ZIndex =
    value.unsafeCast<ZIndex>()
