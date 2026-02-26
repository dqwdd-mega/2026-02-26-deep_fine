package com.example.taskdeep.ui

import com.example.taskdeep.base.BaseContract

object LoginContract {

    data class LoginState(
        val loading: Boolean = false,
    ) : BaseContract.UiState

    sealed interface Event : BaseContract.Event

    sealed interface SideEffect : BaseContract.SideEffect {
        data class ShowToast(val message: String) : SideEffect
    }
}