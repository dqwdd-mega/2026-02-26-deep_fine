package com.example.login.ui.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.R
import com.example.login.ui.component.EmailInputComponent
import com.example.login.ui.component.LoginButtonComponent
import com.example.login.ui.component.LoginTopCard
import com.example.login.ui.component.PasswordInputComponent

@Composable
fun LoginScreen(
    showPasswordField: Boolean = false,
    inputEmail: String = "",
    inputPassword: String = "",
    isEmailFocused: Boolean = false,
    isPasswordFocused: Boolean = false,
    isEmailVerified: Boolean = false,
    showEmailValidation: Boolean = false,
    showPasswordValidation: Boolean = false,
    onChangeEmail: (String) -> Unit = {},
    onChangePassword: (String) -> Unit = {},
    onChangeEmailFocus: (Boolean) -> Unit = {},
    onChangePasswordFocus: (Boolean) -> Unit = {},
    onClickLoginButton: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    
    val buttonText = if (showPasswordField) {
        stringResource(R.string.login)
    } else {
        stringResource(R.string.login_signup)
    }
    
    val buttonEnabled = if (showPasswordField) {
        inputPassword.isNotEmpty()
    } else {
        inputEmail.isNotEmpty()
    }
    
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
            LoginTopCard()
            EmailInputComponent(
                value = inputEmail,
                onValueChange = onChangeEmail,
                isFocused = isEmailFocused,
                onFocusChange = onChangeEmailFocus,
                isEmailVerified = isEmailVerified,
                showEmailValidation = showEmailValidation
            )
            
            if (showPasswordField) {
                Spacer(modifier = Modifier.height(24.dp))
                
                PasswordInputComponent(
                    value = inputPassword,
                    onValueChange = onChangePassword,
                    isFocused = isPasswordFocused,
                    onFocusChange = onChangePasswordFocus,
                    showPasswordValidation = showPasswordValidation
                )
            }
            
            LoginButtonComponent(
                buttonText = buttonText,
                isEnabled = buttonEnabled,
                onClick = onClickLoginButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun LoginScreenWithPasswordPreview() {
    LoginScreen(showPasswordField = true)
}
