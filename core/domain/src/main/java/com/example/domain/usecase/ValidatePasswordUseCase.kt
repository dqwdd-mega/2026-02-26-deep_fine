package com.example.domain.usecase

import com.example.domain.model.PasswordValidationResult
import com.example.domain.model.exception.AuthException
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): PasswordValidationResult {
        return try {
            val user = userRepository.validatePassword(email, password)
            PasswordValidationResult.Success(user)
        } catch (_: AuthException.InvalidPasswordException) {
            PasswordValidationResult.InvalidPassword
        } catch (e: Exception) {
            PasswordValidationResult.Error(e)
        }
    }
}