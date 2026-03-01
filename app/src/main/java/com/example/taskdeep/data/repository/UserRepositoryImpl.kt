package com.example.taskdeep.data.repository

import com.example.taskdeep.data.local.datasource.UserLocalDataSource
import com.example.taskdeep.domain.model.User
import com.example.taskdeep.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun registerUser(user: User): Result<Unit> {
        return try {
            localDataSource.saveUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun validateEmail(email: String): Result<Unit> {
        return try {
            val isExists = localDataSource.isUserExists(email)
            if (isExists) {
                Result.success(Unit)
            } else {
                Result.failure(Exception())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun validatePassword(email: String, password: String): Result<User> {
        return try {
            val user = localDataSource.getUserByEmail(email)

            if (user?.password == password) {
                Result.success(user)
            } else {
                Result.failure(Exception())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}