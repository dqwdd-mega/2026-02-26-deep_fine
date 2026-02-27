package com.example.taskdeep.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskdeep.ui.component.EmailInputComponent
import com.example.taskdeep.ui.component.LoginButtonComponent
import com.example.taskdeep.ui.component.LoginTopCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is LoginContract.SideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LoginScreen(
        inputEmail = state.inputEmail,
        isEmailFocused = state.isEmailFocused,
        showEmailState = state.showEmailState,
        onChangeEmail = { email ->
            viewModel.intent(LoginContract.Event.OnChangeEmail(email))
        },
        onChangeEmailFocus = { isFocused ->
            viewModel.intent(LoginContract.Event.OnChangeEmailFocus(isFocused))
        },
        onClickLoginButton = {
            viewModel.intent(LoginContract.Event.OnClickLoginButton)
        }
    )
}

@Composable
fun LoginScreen(
    inputEmail: String = "",
    isEmailFocused: Boolean = false,
    showEmailState: Boolean = false,
    onChangeEmail: (String) -> Unit = {},
    onChangeEmailFocus: (Boolean) -> Unit = {},
    onClickLoginButton: () -> Unit = {},
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
            LoginTopCard()
            EmailInputComponent(
                value = inputEmail,
                onValueChange = onChangeEmail,
                isFocused = isEmailFocused,
                onFocusChange = onChangeEmailFocus,
                showEmailState = showEmailState
            )
            LoginButtonComponent(
                isInputEmailEmpty = inputEmail.isEmpty(),
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