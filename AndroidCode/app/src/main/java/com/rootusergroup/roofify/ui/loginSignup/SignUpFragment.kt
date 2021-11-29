package com.rootusergroup.roofify.ui.loginSignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.rootusergroup.roofify.MainApplication
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentLoginBinding
import com.rootusergroup.roofify.databinding.FragmentSignUpBinding
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel
import com.rootusergroup.roofify.ui.viewmodel.MainViewModelFactory

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = mainViewModel
                lifecycleOwner = viewLifecycleOwner
            }

        binding.signupLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Toast.makeText(activity, getString(error), Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.successSignUp.observe(viewLifecycleOwner) { successSignUp ->
            if (successSignUp != null) {
                Toast.makeText(activity, getString(successSignUp), Toast.LENGTH_SHORT).show()
            }
        }

    }
}