package com.example.taskdeep.data.local.datasource

import com.example.taskdeep.domain.model.User

interface UserLocalDataSource {
    suspend fun getUserByEmail(email: String): User?
    suspend fun saveUser(user: User)
    suspend fun isUserExists(email: String): Boolean
    suspend fun validateUser(email: String, password: String): Boolean
}