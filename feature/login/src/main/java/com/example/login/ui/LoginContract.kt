package com.example.login.ui

import com.example.login.base.BaseContract
import com.example.login.ui.model.UserState

object LoginContract {

    data class LoginState(
        val loading: Boolean = false,
        val currentStep: UserState = UserState.LOGIN_EMAIL,
        val loginScreenState: LoginScreenState = LoginScreenState(),
        val signupNameState: SignupNameState = SignupNameState(),
        val signupPasswordState: SignupPasswordState = SignupPasswordState()
    ) : BaseContract.UiState {

        data class LoginScreenState(
            val inputEmail: String = "",
            val isEmailFocused: Boolean = false,
            val isEmailVerified: Boolean = false,
            val showEmailValidation: Boolean = false,
            val inputPassword: String = "",
            val isPasswordFocused: Boolean = false,
            val showPasswordValidation: Boolean = false
        )

        data class SignupNameState(
            val inputName: String = "",
            val isNameFocused: Boolean = false,
            val showNameValidation: Boolean = false
        )

        data class SignupPasswordState(
            val inputPassword: String = "",
            val isPasswordFocused: Boolean = false,
            val showPasswordValidation: Boolean = false
        )
    }

    sealed interface Event : BaseContract.Event {
        data class OnChangeEmail(val email: String) : Event
        data class OnChangePassword(val password: String) : Event
        data class OnChangeName(val name: String) : Event
        data class OnChangeEmailFocus(val isFocused: Boolean) : Event
        data class OnChangePasswordFocus(val isFocused: Boolean) : Event
        data class OnChangeNameFocus(val isFocused: Boolean) : Event
        data object OnClickLoginButton : Event
        data object OnClickBackButton : Event
    }

    sealed interface SideEffect : BaseContract.SideEffect {
        data class ShowToast(val message: String) : SideEffect
    }
}
