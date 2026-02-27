package com.example.taskdeep.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
        onEmailChange = { email ->
            viewModel.intent(LoginContract.Event.OnEmailChange(email))
        }
    )
}

@Composable
fun LoginScreen(
    inputEmail: String = "",
    onEmailChange: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column {
            LoginTopCard()
            EmailInputComponent(
                value = inputEmail,
                onValueChange = onEmailChange
            )
            LoginButtonComponent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}