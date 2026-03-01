package com.example.taskdeep.domain.model.exception

sealed class AuthException(
    override val message: String,
) : Exception(message) {
    
    class InvalidPasswordException(
        message: String = "잘못된 비밀번호입니다",
    ) : AuthException(message)
    
    class InvalidPasswordFormatException(
        message: String = "올바른 형식의 비밀번호를 입력해주세요",
    ) : AuthException(message)
}