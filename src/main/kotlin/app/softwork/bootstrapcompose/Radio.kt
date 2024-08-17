package app.softwork.bootstrapcompose

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import kotlin.uuid.*

@Composable
@OptIn(ExperimentalUuidApi::class)
public fun RadioGroup(content: @Composable RadioGroupScope.() -> Unit) {
    val groupName = remember { "_${Uuid.random()}" }
    val context = RadioGroupScope(groupName)
    context.content()
}

public class RadioGroupScope(private val name: String) {
    @Composable
    @OptIn(ExperimentalUuidApi::class)
    public fun Radio(
        label: String,
        checked: Boolean = false,
        disabled: Boolean = false,
        inline: Boolean = false,
        styling: (Styling.() -> Unit)? = null,
        onClick: (Boolean) -> Unit
    ) {
        val id = remember { "_${Uuid.random()}" }

        Style
        val classes = styling?.let {
            Styling().apply(it).generate()
        }

        Div(attrs = {
            classes(BSClasses.formCheck)
            if (inline) {
                classes(BSClasses.formCheckInline)
            }
            if (classes != null) {
                classes(classes = classes)
            }
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
