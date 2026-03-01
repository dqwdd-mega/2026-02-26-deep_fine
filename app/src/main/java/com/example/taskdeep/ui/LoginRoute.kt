package com.example.taskdeep.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskdeep.ui.model.UserState
import com.example.taskdeep.ui.screen.LoginScreen
import com.example.taskdeep.ui.screen.LoginSignupScreen
import com.example.taskdeep.ui.screen.SignupCompleteScreen
import com.example.taskdeep.ui.screen.SignupNameScreen
import com.example.taskdeep.ui.screen.SignupPasswordScreen
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

    when (state.currentStep) {
        UserState.LOGIN_EMAIL, UserState.LOGIN_PASSWORD -> {
            if (state.currentStep == UserState.LOGIN_PASSWORD) {
                BackHandler {
                    viewModel.intent(LoginContract.Event.OnClickBackButton)
                }
            }
            
            LoginScreen(
                showPasswordField = state.currentStep == UserState.LOGIN_PASSWORD,
                inputEmail = state.loginScreenState.inputEmail,
                inputPassword = state.loginScreenState.inputPassword,
                isEmailFocused = state.loginScreenState.isEmailFocused,
                isPasswordFocused = state.loginScreenState.isPasswordFocused,
                isEmailVerified = state.loginScreenState.isEmailVerified,
                showEmailValidation = state.loginScreenState.showEmailValidation,
                showPasswordValidation = state.loginScreenState.showPasswordValidation,
                onChangeEmail = { email ->
                    viewModel.intent(LoginContract.Event.OnChangeEmail(email))
                },
                onChangePassword = { password ->
                    viewModel.intent(LoginContract.Event.OnChangePassword(password))
                },
                onChangeEmailFocus = { isFocused ->
                    viewModel.intent(LoginContract.Event.OnChangeEmailFocus(isFocused))
                },
                onChangePasswordFocus = { isFocused ->
                    viewModel.intent(LoginContract.Event.OnChangePasswordFocus(isFocused))
                },
                onClickLoginButton = {
                    viewModel.intent(LoginContract.Event.OnClickLoginButton)
                }
            )
        }
        
        UserState.LOGIN_SIGHUP -> {
            LoginSignupScreen(
                inputEmail = state.loginScreenState.inputEmail,
                isEmailFocused = state.loginScreenState.isEmailFocused,
                showEmailValidation = state.loginScreenState.showEmailValidation,
                onChangeEmail = { email ->
                    viewModel.intent(LoginContract.Event.OnChangeEmail(email))
                },
                onChangeEmailFocus = { isFocused ->
                    viewModel.intent(LoginContract.Event.OnChangeEmailFocus(isFocused))
                },
                onClickLoginButton = {
                    viewModel.intent(LoginContract.Event.OnClickLoginButton)
                },
                onClickBackButton = {
                    viewModel.intent(LoginContract.Event.OnClickBackButton)
                }
            )
        }
        
        UserState.SIGHUP_NAME -> {
            SignupNameScreen(
                inputName = state.signupNameState.inputName,
                isNameFocused = state.signupNameState.isNameFocused,
                showNameValidation = state.signupNameState.showNameValidation,
                onChangeName = { name ->
                    viewModel.intent(LoginContract.Event.OnChangeName(name))
                },
                onChangeNameFocus = { isFocused ->
                    viewModel.intent(LoginContract.Event.OnChangeNameFocus(isFocused))
                },
                onClickLoginButton = {
                    viewModel.intent(LoginContract.Event.OnClickLoginButton)
                },
                onClickBackButton = {
                    viewModel.intent(LoginContract.Event.OnClickBackButton)
                }
            )
        }
        
        UserState.SIGNUP_PASSWORD -> {
            SignupPasswordScreen(
                inputPassword = state.signupPasswordState.inputPassword,
                isPasswordFocused = state.signupPasswordState.isPasswordFocused,
                showPasswordValidation = state.signupPasswordState.showPasswordValidation,
                onChangePassword = { password ->
                    viewModel.intent(LoginContract.Event.OnChangePassword(password))
                },
                onChangePasswordFocus = { isFocused ->
                    viewModel.intent(LoginContract.Event.OnChangePasswordFocus(isFocused))
                },
                onClickLoginButton = {
                    viewModel.intent(LoginContract.Event.OnClickLoginButton)
                },
                onClickBackButton = {
                    viewModel.intent(LoginContract.Event.OnClickBackButton)
                }
            )
        }
        
        UserState.SIGNUP_COMPLETE -> {
            SignupCompleteScreen(
                onClickLoginButton = {
                    viewModel.intent(LoginContract.Event.OnClickLoginButton)
                },
                onClickBackButton = {
                    viewModel.intent(LoginContract.Event.OnClickBackButton)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginRoutePreview() {
    LoginScreen()
}