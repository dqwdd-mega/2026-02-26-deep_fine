package com.example.login.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.ColorTokens.Black
import com.example.login.ui.theme.ColorTokens.Blue_2735AE
import com.example.login.ui.theme.ColorTokens.Grey_999999
import com.example.login.ui.theme.ColorTokens.Grey_DDDDDD
import com.example.login.ui.theme.ColorTokens.Red_EC3D2B
import com.example.login.ui.theme.ColorTokens.Red_FF5252
import com.example.login.ui.theme.TypoTokens.weight400size12
import com.example.login.ui.theme.TypoTokens.weight400size13_notoSansKr
import com.example.login.ui.theme.TypoTokens.weight400size18

@Composable
internal fun TextInputFieldComponent(
    modifier: Modifier = Modifier,
    label: String = "이메일",
    placeholder: String = "이메일 주소를 입력하세요.",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isFocused: Boolean = false,
    onFocusChange: (Boolean) -> Unit = {},
    showValidationState: Boolean = false,
    isValidationSuccess: Boolean = false,
    validationMessage: String = "이메일 형식이 올바르지 않습니다.",
    isPassword: Boolean = false,
) {
    var wasEverFocused by remember { mutableStateOf(false) }

    val underlineColor: Color = when {
        showValidationState && isValidationSuccess -> Blue_2735AE
        showValidationState && isFocused.not() -> Red_EC3D2B
        showValidationState && isFocused -> Red_EC3D2B
        isFocused -> Blue_2735AE
        value.isNotEmpty() -> Blue_2735AE
        wasEverFocused && value.isEmpty() -> Red_EC3D2B
        else -> Grey_DDDDDD
    }
    
    Column(modifier = modifier) {
        Text(
            text = label,
            style = weight400size13_notoSansKr,
            color = Black,
            lineHeight = 13.sp,
        )

        Spacer(modifier = Modifier.height(7.dp))

        BasicTextField(
            modifier = Modifier.fillMaxWidth().onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    wasEverFocused = true
                }
                onFocusChange(focusState.isFocused)
            },
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            textStyle = weight400size18.copy(
                color = Black,
                lineHeight = 18.sp,
            ),
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = weight400size18,
                            color = Grey_999999,
                            lineHeight = 18.sp,
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = underlineColor)
        )

        Spacer(modifier = Modifier.height(6.dp))

        if (showValidationState) {
            Text(
                text = validationMessage,
                style = weight400size12,
                color = if (isValidationSuccess) Blue_2735AE else Red_FF5252,
                lineHeight = 15.sp,
                letterSpacing = (-0.29).sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputFieldComponentPreview() {
    TextInputFieldComponent()
}

@Preview(showBackground = true)
@Composable
fun TextInputFieldComponentPreview_ShowValidationState() {
    TextInputFieldComponent(
        showValidationState = true
    )
}
