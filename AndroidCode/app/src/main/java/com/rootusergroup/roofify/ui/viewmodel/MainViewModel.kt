package com.rootusergroup.roofify.ui.viewmodel

import android.content.res.Resources
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.data.entities.Post
import com.rootusergroup.roofify.data.entities.User
import com.rootusergroup.roofify.repository.MainRepository
import kotlinx.coroutines.launch


class MainViewModel(private val myrepository: MainRepository) : ViewModel() {

    val posts = myrepository.findAllPost()

    val nameRegisterInput = MutableLiveData("")
    val userNameRegisterInput = MutableLiveData("")
    val userEmailRegisterInput = MutableLiveData("")
    val passwordRegisterInput = MutableLiveData("")
    val repeatPasswordRegisterInput = MutableLiveData("")

    val userInput = MutableLiveData("")
    val passwordInput = MutableLiveData("")
    private var _error = MutableLiveData<Int?>(null)
    val error: LiveData<Int?> get() = _error
    private var _successfulSignup = MutableLiveData<Int?>(null)
    val successSignUp: LiveData<Int?> get() = _successfulSignup
    private var _unsuccessfulSignin = MutableLiveData<Int?>(null)
    val unsuccessfulSignIn: LiveData<Int?> get() = _unsuccessfulSignin
    private var _unsuccessful_post = MutableLiveData<Int?>(null)
    val unsuccessPost: LiveData<Int?> get() = _unsuccessful_post
    private var _nextActivity = MutableLiveData(false)
    val nextActivity: LiveData<Boolean> get() = _nextActivity
    private var _nextFragment = MutableLiveData(false)
    val nextFragment: LiveData<Boolean> get() = _nextFragment

    val title = MutableLiveData("")
    val price = MutableLiveData("")
    val category = MutableLiveData("")
    val image = MutableLiveData<Resources>()
    val location = MutableLiveData("")
    val description = MutableLiveData("")
    val rooms = MutableLiveData("")
    val bathrooms = MutableLiveData("")
    val people = MutableLiveData("")
    val kitchen = MutableLiveData(false)
    val garage = MutableLiveData(false)
    val pool = MutableLiveData(false)
    val closets = MutableLiveData(false)
   // val commodities = mutableListOf<MutableLiveData<Boolean>>()
     //   .addAll(listOf(kitchen, garage, pool, closets))


    fun onLogin() {
        viewModelScope.launch {
            if (userInput.value.isNullOrEmpty() || passwordInput.value.isNullOrEmpty()) {
                _unsuccessfulSignin.value = R.string.signin_empty_inputs_warning
                Log.d("prueba next", _nextActivity.value.toString())
            } else {
                val userInfo = myrepository.searchUserByUserName(userInput.value!!)

                if (userInfo != null) {
                    if (passwordInput.value == userInfo.password) {
                        _nextActivity.value = true

                    } else {
                        _unsuccessfulSignin.value = R.string.signin_unsuccessful
                    }
                } else {
                    _unsuccessfulSignin.value = R.string.signin_unsuccessful_notFoundUser
                }
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            if (nameRegisterInput.value.isNullOrEmpty() || userNameRegisterInput.value.isNullOrEmpty()
                || userEmailRegisterInput.value.isNullOrEmpty() || passwordRegisterInput.value.isNullOrEmpty()
                || repeatPasswordRegisterInput.value.isNullOrEmpty()
            ) {
                _error.value = R.string.signup_warning
            } else if (passwordRegisterInput.value != repeatPasswordRegisterInput.value) {
                _error.value = R.string.signup_warning_mismatch_pass
            } else {
                val registerNewUser = User(
                    0,
                    nameRegisterInput.value!!,
                    userNameRegisterInput.value!!,
                    userEmailRegisterInput.value!!,
                    passwordRegisterInput.value!!,
                    repeatPasswordRegisterInput.value!!
                )
                myrepository.insertOrUpdateUser(registerNewUser)
                _successfulSignup.value = R.string.signup_successful
            }
        }
    }
    /*fun createPost(){
        if(title.value.isNullOrEmpty() || price.value.isNullOrEmpty()
            || category.value.isNullOrEmpty() || location.value.isNullOrEmpty()
            || description.value.isNullOrEmpty() || rooms.value.isNullOrEmpty()
            || bathrooms.value.isNullOrEmpty() || people.value.isNullOrEmpty()){

            _unsuccessful_post.value = R.string.post_unsuccessful

        } else {
            _nextFragment.value = true
        }
    }*/

    fun postProperty() {
        Log.d("prueba", image.value.toString())

        viewModelScope.launch {
            if(title.value.isNullOrEmpty() || price.value.isNullOrEmpty()
                || category.value.isNullOrEmpty() || location.value.isNullOrEmpty()
                || description.value.isNullOrEmpty() || rooms.value.isNullOrEmpty()
                || bathrooms.value.isNullOrEmpty() || people.value.isNullOrEmpty()){

                _unsuccessful_post.value = R.string.post_unsuccessful

            }else{
                val newPost= Post(0, title.value!!, description.value!!,
                    null, price.value!!.toDouble(), category.value!!, location.value!!
                    , rooms.value!!.toInt(), bathrooms.value!!.toInt(), people.value!!.toInt()
                    , "Piscina, Cocina", "ferFernandez10")

                myrepository.insertOrUpdatePost(newPost)
                _nextFragment.value = true

            }

        }



    }

}