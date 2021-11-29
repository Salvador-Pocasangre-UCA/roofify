package com.rootusergroup.roofify.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TableUser")
data class User(
    @PrimaryKey (autoGenerate = true)
    val userID: Int,
    val name: String,
    val userName: String,
    val email: String,
    val password: String,
    val confirm: String
)