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
            is LoginContract.Event.OnEmailChange -> {
                upDateInputEmail(email = event.email)
            }
        }
    }

    private fun upDateInputEmail(email: String) {
        reduce { copy(inputEmail = email) }
    }
}