package com.example.taskdeep.ui

import com.example.taskdeep.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : BaseViewModel<LoginContract.LoginState, LoginContract.Event, LoginContract.SideEffect>() {

    override val _state = MutableStateFlow(LoginContract.LoginState())

    override suspend fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnChangeEmail -> handleOnEmailChange(event.email)
            is LoginContract.Event.OnChangeEmailFocus -> updateEmailFocused(event.isFocused)
            is LoginContract.Event.OnClickLoginButton -> handleLoginButtonClick()
        }
    }

    private fun updateInputEmail(email: String) {
        reduce { copy(inputEmail = email) }
    }

    private fun updateShowEmailState(showEmailState: Boolean) {
        reduce { copy(showEmailState = showEmailState) }
    }

    private fun updateIsEmailFocused(isEmailFocused: Boolean) {
        reduce { copy(isEmailFocused = isEmailFocused) }
    }

    private fun handleOnEmailChange(email: String) {
        updateInputEmail(email = email)
        val showEmailState = state.value.showEmailState

        if (showEmailState) {
            val hasNoTextBeforeAt = email.substringBefore("@", "").isEmpty()
            val shouldClearError = hasNoTextBeforeAt || isValidEmail(email)

            if (shouldClearError) {
                updateShowEmailState(showEmailState = false)
            }
        }
    }

    private fun updateEmailFocused(isFocused: Boolean) {
        updateIsEmailFocused(isFocused)

        // 포커스 아웃이 아니거나 에러 상태가 아니면 종료
        if (isFocused || state.value.showEmailState.not()) return

        // 에러 해제 조건 체크
        val currentEmail = state.value.inputEmail
        val beforeAtPart = currentEmail.substringBefore("@", "")
        val shouldClearError = beforeAtPart.isEmpty() || isValidEmail(currentEmail)

        if (shouldClearError) {
            updateShowEmailState(showEmailState = false)
        }
    }

    private fun handleLoginButtonClick() {
        val inputEmail = state.value.inputEmail
        val isEmailInvalid = isValidEmail(inputEmail).not()

        if (isEmailInvalid) {
            updateShowEmailState(showEmailState = true)
        } else {
            updateShowEmailState(showEmailState = false)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") < email.length - 1
    }
}