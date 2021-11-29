package com.rootusergroup.roofify.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rootusergroup.roofify.data.entities.Post
import com.rootusergroup.roofify.data.entities.User

@Dao
interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdatePost(post: Post)

    @Query("SELECT * FROM TablePost")
    fun findAllPost(): LiveData<List<Post>>

    @Query("SELECT * FROM TablePost WHERE postID = :query")
    suspend fun searchPostByID(query: String): Post

    @Delete
    suspend fun deletePost(post: Post)
}