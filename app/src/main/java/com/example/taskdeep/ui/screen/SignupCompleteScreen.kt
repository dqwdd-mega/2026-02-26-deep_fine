package com.example.taskdeep.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskdeep.R
import com.example.taskdeep.ui.component.LoginButtonComponent
import com.example.taskdeep.ui.component.TopCardWithBack
import com.example.taskdeep.ui.theme.ColorTokens.Grey_999999
import com.example.taskdeep.ui.theme.TypoTokens.weight400size15

@Composable
fun SignupCompleteScreen(
    onClickLoginButton: () -> Unit = {},
    onClickBackButton: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column {
            TopCardWithBack(
                title = stringResource(R.string.login_signup),
                description = "",
                onBackClick = onClickBackButton
            )
            
            Text(
                text = "회원가입이 완료되었습니다!",
                style = weight400size15,
                color = Grey_999999,
                lineHeight = 24.sp,
                letterSpacing = (-0.15).sp
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            LoginButtonComponent(
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