package app.softwork.bootstrapcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.uuid.UUID
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text

@Composable
public fun RadioGroup(content: @Composable RadioGroupScope.() -> Unit) {
    val groupName = remember { "_${UUID()}" }
    val context = RadioGroupScope(groupName)
    content.invoke(context)
}


public class RadioGroupScope(private val name: String) {
    @Composable
    public fun Radio(
        label: String,
        checked: Boolean = false,
        disabled: Boolean = false,
        inline: Boolean = false,
        onClick: (Boolean) -> Unit
    ) {
        val id = remember { "_${UUID()}" }

        Div(attrs = {
            classes(BSClasses.formCheck)
            if (inline) {
                classes(BSClasses.formCheckInline)
            }
        }) {
            Input(attrs = {
                classes(BSClasses.formCheckInput)
                id(id)
                if (checked) {
                    checked()
                }
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