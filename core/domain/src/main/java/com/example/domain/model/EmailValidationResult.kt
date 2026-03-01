package com.example.domain.model

sealed class EmailValidationResult {
    data class Success(val user: User) : EmailValidationResult()
    data object NotFound : EmailValidationResult()
    data class Error(val exception: Throwable) : EmailValidationResult()
}