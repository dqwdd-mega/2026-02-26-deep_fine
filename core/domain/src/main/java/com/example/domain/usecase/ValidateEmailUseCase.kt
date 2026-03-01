package com.example.domain.usecase

import com.example.domain.model.EmailValidationResult
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String): EmailValidationResult {
        return try {
            val user = userRepository.getUserByEmail(email)
            if (user != null) {
                EmailValidationResult.Success(user)
            } else {
                EmailValidationResult.NotFound
            }
        } catch (e: Exception) {
            EmailValidationResult.Error(e)
        }
    }
}