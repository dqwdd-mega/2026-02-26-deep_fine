package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.model.exception.AuthException
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String, name: String): Result<Unit> {
        return runCatching {
            val user = User(email = email, password = password, name = name)
            userRepository.registerUser(user)
        }
    }
}