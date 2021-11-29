package com.rootusergroup.roofify.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rootusergroup.roofify.data.daos.PostDAO
import com.rootusergroup.roofify.data.daos.UserDAO
import com.rootusergroup.roofify.data.entities.Post
import com.rootusergroup.roofify.data.entities.User

@Database(entities = [User::class, Post::class],
    version = 4,
    exportSchema = true
)

abstract class myAppDataBase: RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun postDao(): PostDAO

    companion object {
        @Volatile
        private var INSTANCE: myAppDataBase? = null

        fun getDatabase(context: Context): myAppDataBase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    myAppDataBase::class.java, "roofifyDb"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}