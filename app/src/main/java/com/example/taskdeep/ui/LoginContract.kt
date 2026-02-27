package com.example.taskdeep.ui

import com.example.taskdeep.base.BaseContract

object LoginContract {

    data class LoginState(
        val loading: Boolean = false,
        val inputEmail: String = "",
    ) : BaseContract.UiState

    sealed interface Event : BaseContract.Event {
        data class OnEmailChange(val email: String) : Event
    }

    sealed interface SideEffect : BaseContract.SideEffect {
        data class ShowToast(val message: String) : SideEffect
    }
}