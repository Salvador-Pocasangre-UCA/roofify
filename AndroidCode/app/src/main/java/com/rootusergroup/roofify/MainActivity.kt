package com.rootusergroup.roofify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rootusergroup.roofify.databinding.ActivityMainBinding
import com.rootusergroup.roofify.ui.loginSignup.LoginAndSignupActivity
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel
import com.rootusergroup.roofify.ui.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

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

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewmodel = mainViewModel
            lifecycleOwner = this@MainActivity
        }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)

        setupNav()
    }

    private fun setupNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragmentContainerView)
        val container = binding.navigationBarContainer

        bottomNavigationView.setupWithNavController(navController)

        binding.mainFab.setOnClickListener {
            findNavController(R.id.fragmentContainerView).navigate(R.id.postPropertyFragment)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.exploreFragment -> container.visibility = View.VISIBLE
                R.id.favoritesFragment -> container.visibility = View.VISIBLE
                R.id.fragment_Map -> container.visibility = View.VISIBLE
                R.id.fragment_General_Chat -> container.visibility = View.VISIBLE
                else -> container.visibility = View.GONE
            }
        }

    }

}