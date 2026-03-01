package com.example.taskdeep.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskdeep.R

@Composable
internal fun NameInputComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isFocused: Boolean = false,
    onFocusChange: (Boolean) -> Unit = {},
    showNameState: Boolean = false,
) {
    TextInputFieldComponent(
        modifier = modifier,
        label = stringResource(R.string.name_label),
        placeholder = stringResource(R.string.name_placeholder),
        value = value,
        onValueChange = onValueChange,
        isFocused = isFocused,
        onFocusChange = onFocusChange,
        showErrorState = showNameState,
        errorMessage = stringResource(R.string.name_format_error),
        isPassword = false
    )
}

@Preview(showBackground = true)
@Composable
fun NameInputComponentPreview() {
    NameInputComponent()
}