package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import kotlinx.uuid.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*

@Composable
public fun RadioGroup(content: @Composable RadioGroupScope.() -> Unit) {
    val groupName = remember { "_${UUID()}" }
    val context = RadioGroupScope(groupName)
    context.content()
}


public class RadioGroupScope(private val name: String) {
    @Composable
    public fun Radio(
        label: String,
        checked: Boolean = false,
        disabled: Boolean = false,
        inline: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        onClick: (Boolean) -> Unit
    ) {
        val id = remember { "_${UUID()}" }

        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        } ?: arrayOf()

        Div(attrs = {
            classes(BSClasses.formCheck)
            if (inline) {
                classes(BSClasses.formCheckInline)
            }
            classes(*classes)
        }) {
            Input(attrs = {
                classes(BSClasses.formCheckInput)
                id(id)
                checked(checked)

                onInput { event ->
                    onClick(event.value)
                }
                if (disabled) {
                    disabled()
                }
                name(name)
            }, type = InputType.Radio)
            Label(attrs = {
                classes(BSClasses.formCheckLabel)
                forId(id)
            }) {
                Text(label)
            }
        }
    }
}
