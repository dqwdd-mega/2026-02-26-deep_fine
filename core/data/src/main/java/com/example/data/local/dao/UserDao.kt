package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.UserEntity

@Dao
interface UserDao {
    
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    suspend fun isUserExists(email: String): Boolean
    
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email AND password = :password)")
    suspend fun validateUser(email: String, password: String): Boolean
}