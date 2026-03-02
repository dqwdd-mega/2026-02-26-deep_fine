package com.example.login.ui.screen

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
import com.example.login.R
import com.example.login.ui.component.EmailInputComponent
import com.example.login.ui.component.LoginButtonComponent
import com.example.login.ui.component.SighupTopCard
import com.example.login.ui.theme.ColorTokens.Blue_2735AE
import com.example.login.ui.theme.TypoTokens.weight500size15

@Composable
fun LoginSignupScreen(
    inputEmail: String = "",
    isEmailFocused: Boolean = false,
    showEmailValidation: Boolean = false,
    onChangeEmail: (String) -> Unit = {},
    onChangeEmailFocus: (Boolean) -> Unit = {},
    onClickLoginButton: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    
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
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            SighupTopCard()

            Spacer(modifier = Modifier.height(74.dp))

            EmailInputComponent(
                value = inputEmail,
                onValueChange = onChangeEmail,
                isFocused = isEmailFocused,
                onFocusChange = onChangeEmailFocus,
                isEmailVerified = false,
                showEmailValidation = showEmailValidation
            )
            
            Spacer(modifier = Modifier.height(6.dp))
            
            Text(
                text = stringResource(R.string.no_login_info),
                style = weight500size15,
                color = Blue_2735AE,
                lineHeight = 15.sp,
                letterSpacing = (-0.29).sp
            )
            
            LoginButtonComponent(
                buttonText = stringResource(R.string.next),
                isEnabled = true,
                onClick = onClickLoginButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginSignupScreenPreview() {
    LoginSignupScreen()
}
