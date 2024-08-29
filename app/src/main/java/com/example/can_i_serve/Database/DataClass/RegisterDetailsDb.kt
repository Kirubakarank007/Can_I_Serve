package com.example.can_i_serve.Database.DataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register")
data class RegisterDetailsDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String?,
    val age: Int?,
    val location: String?,
    val email: String?,
    val phone: String?,
    val role: Int?,
    val countryCode:String?,
    var password:String?
)
