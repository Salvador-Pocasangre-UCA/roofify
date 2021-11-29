package com.rootusergroup.roofify.ui.loginSignup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.rootusergroup.roofify.MainActivity
import com.rootusergroup.roofify.MainApplication
import com.rootusergroup.roofify.databinding.ActivityLoginAndSignupBinding
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel
import com.rootusergroup.roofify.ui.viewmodel.MainViewModelFactory

class LoginAndSignupActivity : AppCompatActivity() {

    private val userApp by lazy {
        application as MainApplication
    }

    private val factory: MainViewModelFactory by lazy {
        val repository = userApp.myappRepository
        MainViewModelFactory(repository)
    }

    private val mainViewModel: MainViewModel by viewModels {
        factory
    }

    private var _binding: ActivityLoginAndSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginAndSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.nextActivity.observe(this){
            if (it == true){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}