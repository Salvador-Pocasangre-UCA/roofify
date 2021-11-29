package com.rootusergroup.roofify.ui.loginSignup

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.rootusergroup.roofify.MainApplication
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentLoginBinding
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel
import com.rootusergroup.roofify.ui.viewmodel.MainViewModelFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = mainViewModel
                lifecycleOwner = viewLifecycleOwner
            }

        binding.loginSignupButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.unsuccessfulSignIn.observe(viewLifecycleOwner) { unsuccessSignIn ->
            if (unsuccessSignIn != null) {
                Toast.makeText(activity, getString(unsuccessSignIn), Toast.LENGTH_SHORT).show()
            }
        }

    }

}