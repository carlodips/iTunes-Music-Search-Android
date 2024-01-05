package dev.carlodips.itunesmusicsearch.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    textValue: MutableState<String>,
    label: String,
    errorMessage: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Sentences,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    ),
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = false
) {
    val textFieldValue = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(textValue.value))
    }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = textFieldValue.value,
        label = { Text(text = label) },
        onValueChange = {
            textValue.value = it.text
            textFieldValue.value = it
            onValueChange(textValue.value)
        },
        readOnly = readOnly,
        isError = !errorMessage.isNullOrEmpty(),
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        /*colors = TextFieldDefaults.outlinedTextFieldColors(
            //textColor = Color.White,
            //backgroundColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.tertiary,
            focusedLabelColor = MaterialTheme.colorScheme.tertiary
        )*/
    )

    if (!errorMessage.isNullOrEmpty()) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(
                start = 12.dp,
                top = 4.dp
            )
        )
    }
}
