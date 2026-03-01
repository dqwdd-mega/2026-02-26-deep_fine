package com.example.taskdeep.domain.usecase

import com.example.taskdeep.domain.model.User
import com.example.taskdeep.domain.repository.UserRepository
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return userRepository.validatePassword(email, password)
    }
}