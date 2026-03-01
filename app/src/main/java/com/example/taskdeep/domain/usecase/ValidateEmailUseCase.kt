package com.example.taskdeep.domain.usecase

import com.example.taskdeep.domain.repository.UserRepository
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String): Result<Unit> {
        return userRepository.validateEmail(email)
    }
}