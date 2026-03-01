package com.example.taskdeep.domain.repository

import com.example.taskdeep.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User)
    suspend fun getUserByEmail(email: String): User?
    suspend fun validatePassword(email: String, password: String): User
}