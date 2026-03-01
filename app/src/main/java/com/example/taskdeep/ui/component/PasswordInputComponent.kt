package com.example.taskdeep.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskdeep.R

@Composable
internal fun PasswordInputComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isFocused: Boolean = false,
    onFocusChange: (Boolean) -> Unit = {},
    showPasswordValidation: Boolean = false,
) {
    TextInputFieldComponent(
        modifier = modifier,
        label = stringResource(R.string.password_label),
        placeholder = stringResource(R.string.password_placeholder),
        value = value,
        onValueChange = onValueChange,
        isFocused = isFocused,
        onFocusChange = onFocusChange,
        showValidationState = showPasswordValidation,
        isValidationSuccess = false,
        validationMessage = stringResource(R.string.password_format_error),
        isPassword = true
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputComponentPreview() {
    PasswordInputComponent()
}