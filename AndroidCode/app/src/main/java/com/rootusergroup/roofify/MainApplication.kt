package com.rootusergroup.roofify

import android.app.Application
import com.rootusergroup.roofify.data.myAppDataBase
import com.rootusergroup.roofify.repository.MainRepository

class MainApplication : Application() {
    private val database by lazy {
        myAppDataBase.getDatabase(this)
    }

    val myappRepository: MainRepository by lazy {
        val userDao = database.userDao()
        val postDao = database.postDao()
        MainRepository(userDao, postDao)
    }
}