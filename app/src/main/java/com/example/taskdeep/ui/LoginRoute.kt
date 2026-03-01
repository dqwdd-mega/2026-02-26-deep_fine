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

    LoginSignupScreen(
        currentStep = state.currentStep,
        inputEmail = state.loginScreenState.inputEmail,
        inputPassword = state.loginScreenState.inputPassword,
        isEmailFocused = state.loginScreenState.isEmailFocused,
        isPasswordFocused = state.loginScreenState.isPasswordFocused,
        isEmailVerified = state.loginScreenState.isEmailVerified,
        showEmailValidation = state.loginScreenState.showEmailValidation,
        showPasswordValidation = state.loginScreenState.showPasswordValidation,
        inputName = state.signupNameState.inputName,
        isNameFocused = state.signupNameState.isNameFocused,
        showNameValidation = state.signupNameState.showNameValidation,
        signupPassword = state.signupPasswordState.inputPassword,
        isSignupPasswordFocused = state.signupPasswordState.isPasswordFocused,
        showSignupPasswordValidation = state.signupPasswordState.showPasswordValidation,
        onChangeEmail = { email ->
            viewModel.intent(LoginContract.Event.OnChangeEmail(email))
        },
        onChangePassword = { password ->
            viewModel.intent(LoginContract.Event.OnChangePassword(password))
        },
        onChangeName = { name ->
            viewModel.intent(LoginContract.Event.OnChangeName(name))
        },
        onChangeEmailFocus = { isFocused ->
            viewModel.intent(LoginContract.Event.OnChangeEmailFocus(isFocused))
        },
        onChangePasswordFocus = { isFocused ->
            viewModel.intent(LoginContract.Event.OnChangePasswordFocus(isFocused))
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


@Composable
fun LoginSignupScreen(
    currentStep: UserState,
    inputEmail: String,
    inputPassword: String,
    isEmailFocused: Boolean,
    isPasswordFocused: Boolean,
    isEmailVerified: Boolean,
    showEmailValidation: Boolean,
    showPasswordValidation: Boolean,
    inputName: String,
    isNameFocused: Boolean,
    showNameValidation: Boolean,
    signupPassword: String,
    isSignupPasswordFocused: Boolean,
    showSignupPasswordValidation: Boolean,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeName: (String) -> Unit,
    onChangeEmailFocus: (Boolean) -> Unit,
    onChangePasswordFocus: (Boolean) -> Unit,
    onChangeNameFocus: (Boolean) -> Unit,
    onClickLoginButton: () -> Unit,
    onClickBackButton: () -> Unit
) {
    when (currentStep) {
        UserState.LOGIN_EMAIL, UserState.LOGIN_PASSWORD -> {
            if (currentStep == UserState.LOGIN_PASSWORD) {
                BackHandler(onBack = onClickBackButton)
            }

            LoginScreen(
                showPasswordField = currentStep == UserState.LOGIN_PASSWORD,
                inputEmail = inputEmail,
                inputPassword = inputPassword,
                isEmailFocused = isEmailFocused,
                isPasswordFocused = isPasswordFocused,
                isEmailVerified = isEmailVerified,
                showEmailValidation = showEmailValidation,
                showPasswordValidation = showPasswordValidation,
                onChangeEmail = onChangeEmail,
                onChangePassword = onChangePassword,
                onChangeEmailFocus = onChangeEmailFocus,
                onChangePasswordFocus = onChangePasswordFocus,
                onClickLoginButton = onClickLoginButton
            )
        }

        UserState.LOGIN_SIGHUP -> {
            BackHandler(onBack = onClickBackButton)

            LoginSignupScreen(
                inputEmail = inputEmail,
                isEmailFocused = isEmailFocused,
                showEmailValidation = showEmailValidation,
                onChangeEmail = onChangeEmail,
                onChangeEmailFocus = onChangeEmailFocus,
                onClickLoginButton = onClickLoginButton,
                onClickBackButton = onClickBackButton
            )
        }

        UserState.SIGHUP_NAME -> {
            SignupNameScreen(
                inputName = inputName,
                isNameFocused = isNameFocused,
                showNameValidation = showNameValidation,
                onChangeName = onChangeName,
                onChangeNameFocus = onChangeNameFocus,
                onClickLoginButton = onClickLoginButton,
                onClickBackButton = onClickBackButton
            )
        }

        UserState.SIGNUP_PASSWORD -> {
            SignupPasswordScreen(
                inputPassword = signupPassword,
                isPasswordFocused = isSignupPasswordFocused,
                showPasswordValidation = showSignupPasswordValidation,
                onChangePassword = onChangePassword,
                onChangePasswordFocus = onChangePasswordFocus,
                onClickLoginButton = onClickLoginButton,
                onClickBackButton = onClickBackButton
            )
        }

        UserState.SIGNUP_COMPLETE -> {
            SignupCompleteScreen(
                userName = inputName,
                onClickLoginButton = onClickLoginButton,
                onClickBackButton = onClickBackButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginRoutePreview() {
    LoginSignupScreen()
}