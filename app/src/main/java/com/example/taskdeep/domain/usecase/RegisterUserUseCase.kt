package com.example.taskdeep.domain.usecase

import com.example.taskdeep.domain.model.User
import com.example.taskdeep.domain.repository.UserRepository
import javax.inject.Inject

/**
 * 사용자 회원가입 UseCase
 * 
 * 비밀번호 규칙:
 * 1. 최소 8자리 이상
 * 2. 영어 대문자/소문자/숫자/특수문자 3가지 이상 조합
 */
class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String, name: String): Result<Unit> {
        // 비밀번호 최소 길이 검증
        if (isInvalidPasswordLength(password)) {
            return Result.failure(Exception("올바른 형식의 비밀번호를 입력해주세요"))
        }

        // 비밀번호 조합 검증
        if (isInvalidPassword(password)) {
            return Result.failure(Exception("올바른 형식의 비밀번호를 입력해주세요"))
        }
        
        val user = User(email = email, password = password, name = name)
        return userRepository.registerUser(user)
    }


    /**
     * 비밀번호 길이가 유효하지 않은지 검증 (8자 미만)
     */
    private fun isInvalidPasswordLength(password: String): Boolean {
        return password.length < 8
    }

    /**
     * 비밀번호 조합이 유효하지 않은지 검증 (대문자/소문자/숫자/특수문자 중 3가지 미만)
     */
    private fun isInvalidPassword(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { it.isLetterOrDigit().not() }
        
        val conditionCount = listOf(hasUpperCase, hasLowerCase, hasDigit, hasSpecialChar).count { it }
        
        return conditionCount < 3
    }
}