package com.example.taskdeep.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskdeep.R

@Composable
internal fun EmailInputComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    isFocused: Boolean = false,
    isEmailVerified: Boolean = false,
    showEmailValidation: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
) {
    TextInputFieldComponent(
        modifier = modifier,
        label = stringResource(R.string.email_label),
        placeholder = stringResource(R.string.email_placeholder),
        value = value,
        onValueChange = onValueChange,
        isFocused = isFocused,
        onFocusChange = onFocusChange,
        showValidationState = showEmailValidation,
        isValidationSuccess = isEmailVerified,
        validationMessage = if (isEmailVerified) {
            "이메일이 확인되었습니다. :)"
        } else {
            stringResource(R.string.email_format_error)
        },
        isPassword = false
    )
}

@Preview(showBackground = true)
@Composable
fun EmailInputComponentPreview() {
    EmailInputComponent()
}