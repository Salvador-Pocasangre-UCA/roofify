package com.rootusergroup.roofify.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rootusergroup.roofify.data.entities.User

@Dao
interface UserDAO {
    //Insertar o actualizar usuario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User)

    //Obtener todos los usuarios
    @Query("SELECT * FROM TableUser")
    fun findAllUsers(): LiveData<List<User>>

    //Obtener un usuario
    @Query("SELECT * FROM TableUser WHERE userName = :query")
    suspend fun searchUserByUserName(query: String): User

    @Query("SELECT * FROM TableUser WHERE userID = :query")
    suspend fun searchUserByUserID(query: String): User

    //Eliminar usuario
    @Delete
    suspend fun deleteUser(user: User)
}