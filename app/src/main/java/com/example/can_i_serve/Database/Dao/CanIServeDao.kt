package com.example.can_i_serve.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.can_i_serve.Database.DataClass.RegisterDetailsDb

@Dao
interface CanIServeDao {
    @Insert
     fun insert(user: RegisterDetailsDb)

    @Update
    suspend fun update(user: RegisterDetailsDb)

    @Delete
    suspend fun delete(user: RegisterDetailsDb)

    @Query("SELECT * FROM Register WHERE id = :userId")
    suspend fun getUserById(userId: Int): RegisterDetailsDb?

    @Query("SELECT * FROM Register")
     fun getAllUsers(): List<RegisterDetailsDb>

    @Query("SELECT * FROM Register WHERE phone = :phoneNumber")
     fun getUserByPhoneNumber(phoneNumber: String): RegisterDetailsDb?

}