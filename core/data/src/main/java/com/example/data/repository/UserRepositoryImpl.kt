package com.example.data.repository

import com.example.data.local.datasource.UserLocalDataSource
import com.example.domain.model.User
import com.example.domain.model.exception.AuthException
import com.example.domain.model.exception.DatabaseException
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun registerUser(user: User) {
        try {
            localDataSource.saveUser(user)
        } catch (e: Exception) {
            throw DatabaseException.InsertException(cause = e)
        }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return try {
            localDataSource.getUserByEmail(email)
        } catch (e: Exception) {
            throw DatabaseException.QueryException(cause = e)
        }
    }

    override suspend fun validatePassword(email: String, password: String): User {
        try {
            val user = localDataSource.getUserByEmail(email)
                ?: throw AuthException.InvalidPasswordException()

            if (user.password != password) {
                throw AuthException.InvalidPasswordException()
            }

            return user
        } catch (e: AuthException) {
            throw e
        } catch (e: Exception) {
            throw DatabaseException.QueryException(cause = e)
        }
    }
}