package com.example.taskdeep.ui

import com.example.domain.model.EmailValidationResult
import com.example.domain.model.PasswordValidationResult
import com.example.domain.usecase.RegisterUserUseCase
import com.example.domain.usecase.ValidateEmailUseCase
import com.example.domain.usecase.ValidatePasswordUseCase
import com.example.taskdeep.base.BaseViewModel
import com.example.taskdeep.ui.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
) : BaseViewModel<LoginContract.LoginState, LoginContract.Event, LoginContract.SideEffect>() {

    override val _state = MutableStateFlow(LoginContract.LoginState())

    override suspend fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnChangeEmail -> handleOnEmailChange(event.email)
            is LoginContract.Event.OnChangePassword -> handleOnPasswordChange(event.password)
            is LoginContract.Event.OnChangeName -> handleOnNameChange(event.name)
            is LoginContract.Event.OnChangeEmailFocus -> updateEmailFocused(event.isFocused)
            is LoginContract.Event.OnChangePasswordFocus -> updatePasswordFocused(event.isFocused)
            is LoginContract.Event.OnChangeNameFocus -> updateNameFocused(event.isFocused)
            is LoginContract.Event.OnClickLoginButton -> handleLoginButtonClick()
            is LoginContract.Event.OnClickBackButton -> handleBackButtonClick()
        }
    }

    private fun updateCurrentStep(currentStep: UserState) {
        reduce { copy(currentStep = currentStep) }
    }

    private fun updateLoginScreenState(update: LoginContract.LoginState.LoginScreenState.() -> LoginContract.LoginState.LoginScreenState) {
        reduce { copy(loginScreenState = loginScreenState.update()) }
    }

    private fun updateSignupNameState(update: LoginContract.LoginState.SignupNameState.() -> LoginContract.LoginState.SignupNameState) {
        reduce { copy(signupNameState = signupNameState.update()) }
    }

    private fun updateSignupPasswordState(update: LoginContract.LoginState.SignupPasswordState.() -> LoginContract.LoginState.SignupPasswordState) {
        reduce { copy(signupPasswordState = signupPasswordState.update()) }
    }

    private fun resetAllStates() {
        reduce {
            copy(
                currentStep = UserState.LOGIN_EMAIL,
                loginScreenState = LoginContract.LoginState.LoginScreenState(),
                signupNameState = LoginContract.LoginState.SignupNameState(),
                signupPasswordState = LoginContract.LoginState.SignupPasswordState()
            )
        }
    }

    private fun handleOnEmailChange(email: String) {
        val currentState = state.value.currentStep
        
        if (currentState == UserState.LOGIN_PASSWORD || currentState == UserState.LOGIN_SIGHUP) {
            reduce {
                copy(
                    currentStep = UserState.LOGIN_EMAIL,
                    loginScreenState = loginScreenState.copy(
                        inputEmail = email,
                        inputPassword = "",
                        isEmailVerified = false,
                        showPasswordValidation = false
                    )
                )
            }
        } else {
            updateLoginScreenState { copy(inputEmail = email, isEmailVerified = false) }
        }

        val showEmailValidation = state.value.loginScreenState.showEmailValidation
        if (showEmailValidation) {
            val hasNoTextBeforeAt = email.substringBefore("@", "").isEmpty()
            val shouldClearError = hasNoTextBeforeAt || isValidEmail(email)

            if (shouldClearError) {
                updateLoginScreenState { copy(showEmailValidation = false) }
            }
        }
    }

    private fun handleOnPasswordChange(password: String) {
        val currentState = state.value.currentStep
        
        if (currentState == UserState.LOGIN_PASSWORD) {
            updateLoginScreenState { copy(inputPassword = password) }
            
            val showPasswordValidation = state.value.loginScreenState.showPasswordValidation
            if (showPasswordValidation) {
                updateLoginScreenState { copy(showPasswordValidation = false) }
            }
        } else if (currentState == UserState.SIGNUP_PASSWORD) {
            updateSignupPasswordState { copy(inputPassword = password) }
            
            val showPasswordValidation = state.value.signupPasswordState.showPasswordValidation
            if (showPasswordValidation) {
                updateSignupPasswordState { copy(showPasswordValidation = false) }
            }
        }
    }

    private fun handleOnNameChange(name: String) {
        updateSignupNameState { copy(inputName = name) }
        
        val showNameValidation = state.value.signupNameState.showNameValidation
        if (showNameValidation) {
            updateSignupNameState { copy(showNameValidation = false) }
        }
    }

    private fun updateEmailFocused(isFocused: Boolean) {
        updateLoginScreenState { copy(isEmailFocused = isFocused) }

        if (isFocused || state.value.loginScreenState.showEmailValidation.not()) return

        val currentState = state.value.currentStep
        if (currentState == UserState.LOGIN_PASSWORD && state.value.loginScreenState.isEmailVerified) {
            return
        }

        val currentEmail = state.value.loginScreenState.inputEmail
        val beforeAtPart = currentEmail.substringBefore("@", "")
        val shouldClearError = beforeAtPart.isEmpty() || isValidEmail(currentEmail)

        if (shouldClearError) {
            updateLoginScreenState { copy(showEmailValidation = false) }
        }
    }

    private fun updatePasswordFocused(isFocused: Boolean) {
        val currentState = state.value.currentStep
        
        if (currentState == UserState.LOGIN_PASSWORD) {
            updateLoginScreenState { copy(isPasswordFocused = isFocused) }
        } else if (currentState == UserState.SIGNUP_PASSWORD) {
            updateSignupPasswordState { copy(isPasswordFocused = isFocused) }
        }
    }

    private fun updateNameFocused(isFocused: Boolean) {
        updateSignupNameState { copy(isNameFocused = isFocused) }
    }

    private suspend fun handleLoginButtonClick() {
        val currentState = state.value.currentStep
        
        when (currentState) {
            UserState.LOGIN_EMAIL -> handleLoginEmailState()
            UserState.LOGIN_PASSWORD -> handleLoginPasswordState()
            UserState.LOGIN_SIGHUP -> handleLoginSignupState()
            UserState.SIGHUP_NAME -> handleSignupNameState()
            UserState.SIGNUP_PASSWORD -> handleSignupPasswordState()
            UserState.SIGNUP_COMPLETE -> handleSignupCompleteState()
        }
    }

    private suspend fun handleLoginEmailState() {
        val inputEmail = state.value.loginScreenState.inputEmail
        val isEmailInvalid = isValidEmail(inputEmail).not()

        if (isEmailInvalid) {
            updateLoginScreenState { copy(showEmailValidation = true, isEmailVerified = false) }
            return
        }

        when (validateEmailUseCase(inputEmail)) {
            is EmailValidationResult.Found -> {
                updateLoginScreenState { copy(isEmailVerified = true, showEmailValidation = true) }
                updateCurrentStep(UserState.LOGIN_PASSWORD)
            }
            is EmailValidationResult.NotFound -> {
                updateCurrentStep(UserState.LOGIN_SIGHUP)
            }
            is EmailValidationResult.Error -> {
                postSideEffect(LoginContract.SideEffect.ShowToast("오류가 발생했습니다."))
            }
        }
    }

    private suspend fun handleLoginPasswordState() {
        val inputEmail = state.value.loginScreenState.inputEmail
        val inputPassword = state.value.loginScreenState.inputPassword

        when (validatePasswordUseCase(inputEmail, inputPassword)) {
            is PasswordValidationResult.Success -> {
                postSideEffect(LoginContract.SideEffect.ShowToast("로그인 성공!"))
            }
            is PasswordValidationResult.InvalidPassword -> {
                postSideEffect(LoginContract.SideEffect.ShowToast("잘못된 비밀번호입니다"))
            }
            is PasswordValidationResult.Error -> {
                postSideEffect(LoginContract.SideEffect.ShowToast("오류가 발생했습니다."))
            }
        }
    }

    private fun handleLoginSignupState() {
        updateCurrentStep(UserState.SIGHUP_NAME)
    }

    private fun handleSignupNameState() {
        val inputName = state.value.signupNameState.inputName
        if (inputName.isEmpty()) {
            updateSignupNameState { copy(showNameValidation = true) }
            return
        }
        updateCurrentStep(UserState.SIGNUP_PASSWORD)
    }

    private fun handleSignupPasswordState() {
        val inputPassword = state.value.signupPasswordState.inputPassword
        if (inputPassword.isEmpty()) {
            updateSignupPasswordState { copy(showPasswordValidation = true) }
            return
        }
        updateCurrentStep(UserState.SIGNUP_COMPLETE)
    }

    private suspend fun handleSignupCompleteState() {
        val inputEmail = state.value.loginScreenState.inputEmail
        val inputPassword = state.value.signupPasswordState.inputPassword
        val inputName = state.value.signupNameState.inputName

        val result = registerUserUseCase(inputEmail, inputPassword, inputName)
        
        result.onSuccess {
            resetAllStates()
        }.onFailure {
            postSideEffect(LoginContract.SideEffect.ShowToast("올바른 형식의 비밀번호를 입력해주세요"))
        }
    }

    private fun handleBackButtonClick() {
        val currentState = state.value.currentStep
        
        when (currentState) {
            UserState.LOGIN_PASSWORD -> {
                reduce {
                    copy(
                        currentStep = UserState.LOGIN_EMAIL,
                        loginScreenState = loginScreenState.copy(
                            inputPassword = "",
                            isPasswordFocused = false,
                            showPasswordValidation = false,
                            showEmailValidation = false
                        )
                    )
                }
            }
            UserState.LOGIN_SIGHUP -> {
                updateCurrentStep(UserState.LOGIN_EMAIL)
            }
            UserState.SIGHUP_NAME -> {
                updateCurrentStep(UserState.LOGIN_SIGHUP)
            }
            UserState.SIGNUP_PASSWORD -> {
                updateCurrentStep(UserState.SIGHUP_NAME)
            }
            UserState.SIGNUP_COMPLETE -> {
                updateCurrentStep(UserState.SIGNUP_PASSWORD)
            }
            else -> {}
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") < email.length - 1
    }
}