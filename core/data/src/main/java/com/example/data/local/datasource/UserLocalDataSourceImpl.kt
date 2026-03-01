package com.example.data.local.datasource

import com.example.data.local.dao.UserDao
import com.example.data.local.entity.UserEntity
import com.example.domain.model.User
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)?.toDomain()
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(UserEntity.fromDomain(user))
    }

    override suspend fun isUserExists(email: String): Boolean {
        return userDao.isUserExists(email)
    }

    override suspend fun validateUser(email: String, password: String): Boolean {
        return userDao.validateUser(email, password)
    }
}