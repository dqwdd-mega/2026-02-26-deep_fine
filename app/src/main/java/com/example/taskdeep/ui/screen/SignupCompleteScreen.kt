package com.example.taskdeep.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskdeep.R
import com.example.taskdeep.ui.component.LoginButtonComponent
import com.example.taskdeep.ui.component.TopCardWithBack
import com.example.taskdeep.ui.theme.ColorTokens.Black
import com.example.taskdeep.ui.theme.ColorTokens.Blue_2735AE

@Composable
fun SignupCompleteScreen(
    userName: String = "사용자",
    onClickLoginButton: () -> Unit = {},
    onClickBackButton: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    
    val titleText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Blue_2735AE)) {
            append("${userName}님, ")
        }
        withStyle(style = SpanStyle(color = Black)) {
            append("\n가입이 완료됐어요👋🏻")
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column {
            TopCardWithBack(
                titleAnnotated = titleText,
                description = "가입하신 회원 정보로 다시 로그인해주세요.",
                progress = 1f,
                onBackClick = onClickBackButton
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            LoginButtonComponent(
                modifier = Modifier.padding(horizontal = 24.dp),
                buttonText = stringResource(R.string.login),
                isEnabled = true,
                onClick = onClickLoginButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupCompleteScreenPreview() {
    SignupCompleteScreen()
}