package app.softwork.bootstrapcompose

public interface ZIndex {
    public companion object {
        public inline val auto: ZIndex get() = ZIndex("auto")
    }
}

public inline fun ZIndex(value: String): ZIndex = value.unsafeCast<ZIndex>()
public inline fun ZIndex(value: Int): ZIndex = ZIndex(value = value.toString())
