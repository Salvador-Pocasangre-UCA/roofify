package com.rootusergroup.roofify.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "TablePost")
/*, foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = arrayOf("userID"), childColumns = arrayOf("ownerUser"), onDelete = ForeignKey.CASCADE)]*/

data class Post(
    @PrimaryKey(autoGenerate = true)
    val postID: Int,
    val title: String,
    val description: String,
    val image: Byte?,
    val price: Double,
    val category: String,
    val location: String,
    val rooms: Int,
    val bathrooms: Int,
    val capacity: Int,
    val commodities: String,
    val ownerUser: String
)