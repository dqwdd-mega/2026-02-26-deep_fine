package com.example.taskdeep.domain.usecase

import com.example.taskdeep.domain.model.EmailValidationResult
import com.example.taskdeep.domain.repository.UserRepository
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String): EmailValidationResult {
        return try {
            val user = userRepository.getUserByEmail(email)
            if (user != null) {
                EmailValidationResult.Found(user)
            } else {
                EmailValidationResult.NotFound
            }
        } catch (e: Exception) {
            EmailValidationResult.Error(e)
        }
    }
}