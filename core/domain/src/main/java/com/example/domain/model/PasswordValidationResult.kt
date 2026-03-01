package com.example.domain.model

sealed class PasswordValidationResult {
    data class Success(val user: User) : PasswordValidationResult()
    data object InvalidPassword : PasswordValidationResult()
    data class Error(val exception: Throwable) : PasswordValidationResult()
}