package com.example.taskdeep.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskdeep.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val email: String,
    val password: String,
    val name: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun toDomain(): User {
        return User(
            email = email,
            password = password,
            name = name
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                email = user.email,
                password = user.password,
                name = user.name
            )
        }
    }
}