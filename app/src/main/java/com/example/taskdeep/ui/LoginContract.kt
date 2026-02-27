package com.example.taskdeep.ui

import com.example.taskdeep.base.BaseContract

object LoginContract {

    data class LoginState(
        val loading: Boolean = false,
        val inputEmail: String = "",
        val isEmailFocused: Boolean = false,
    ) : BaseContract.UiState

    sealed interface Event : BaseContract.Event {
        data class OnEmailChange(val email: String) : Event
        data class OnEmailFocusChange(val isFocused: Boolean) : Event
    }

    sealed interface SideEffect : BaseContract.SideEffect {
        data class ShowToast(val message: String) : SideEffect
    }
}