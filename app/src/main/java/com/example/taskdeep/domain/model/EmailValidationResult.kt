package com.example.taskdeep.domain.model

sealed class EmailValidationResult {
    data class Found(val user: User) : EmailValidationResult()
    data object NotFound : EmailValidationResult()
    data class Error(val exception: Throwable) : EmailValidationResult()
}