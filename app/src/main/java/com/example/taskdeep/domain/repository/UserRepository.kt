package com.example.taskdeep.domain.repository

import com.example.taskdeep.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User): Result<Unit>
    suspend fun validateEmail(email: String): Result<Unit>
    suspend fun validatePassword(email: String, password: String): Result<User>
}