package com.rootusergroup.roofify.repository

import com.rootusergroup.roofify.data.daos.PostDAO
import com.rootusergroup.roofify.data.daos.UserDAO
import com.rootusergroup.roofify.data.entities.Post
import com.rootusergroup.roofify.data.entities.User

class MainRepository(private val userDao : UserDAO, private val postDao: PostDAO) {
    suspend fun insertOrUpdateUser(user: User) = userDao.insertOrUpdateUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    suspend fun searchUserByID(user: String) = userDao.searchUserByUserID(user)
    suspend fun searchUserByUserName(user: String) = userDao.searchUserByUserName(user)
    fun findAllUsers() = userDao.findAllUsers()

    suspend fun insertOrUpdatePost(post: Post) = postDao.insertOrUpdatePost(post)
    suspend fun deletePost(post: Post) = postDao.deletePost(post)
    suspend fun searchPostByID(post: String) = postDao.searchPostByID(post)
    fun findAllPost() = postDao.findAllPost()

}