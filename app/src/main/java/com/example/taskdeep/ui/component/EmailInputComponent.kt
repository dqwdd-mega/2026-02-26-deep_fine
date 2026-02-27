package com.example.taskdeep.ui.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskdeep.R
import com.example.taskdeep.ui.theme.ColorTokens.Black
import com.example.taskdeep.ui.theme.ColorTokens.Blue_2735AE
import com.example.taskdeep.ui.theme.ColorTokens.Grey_999999
import com.example.taskdeep.ui.theme.ColorTokens.Grey_DDDDDD
import com.example.taskdeep.ui.theme.ColorTokens.Red_EC3D2B
import com.example.taskdeep.ui.theme.TypoTokens.weight400size13_notoSansKr
import com.example.taskdeep.ui.theme.TypoTokens.weight400size18

@Composable
internal fun EmailInputComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    isFocused: Boolean = false,
    onFocusChange: (Boolean) -> Unit = {},
) {
    val emailPlaceholder = stringResource(R.string.email_placeholder)
    var wasEverFocused by remember { mutableStateOf(false) }

    val underlineColor: Color = when {
        isFocused -> Blue_2735AE
        value.isNotEmpty() -> Blue_2735AE
        wasEverFocused && value.isEmpty() -> Red_EC3D2B
        else -> Grey_DDDDDD
    }
    
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.email_label),
            style = weight400size13_notoSansKr,
            color = Black,
            lineHeight = 13.sp,
        )

        Spacer(modifier = Modifier.height(7.dp))

        BasicTextField(
            modifier = Modifier.onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    wasEverFocused = true
                }
                onFocusChange(focusState.isFocused)
            },
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = weight400size18.copy(
                color = Black,
                lineHeight = 18.sp,
            ),
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = emailPlaceholder,
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
    }
}


@Preview(showBackground = true)
@Composable
fun EmailInputComponentPreview() {
    EmailInputComponent()
}