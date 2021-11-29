package com.rootusergroup.roofify.ui.postproperty

import android.content.Intent
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
import com.rootusergroup.roofify.MainActivity
import com.rootusergroup.roofify.MainApplication
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentPaymentBinding
import com.rootusergroup.roofify.databinding.FragmentPostPropertyBinding
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel
import com.rootusergroup.roofify.ui.viewmodel.MainViewModelFactory

class PaymentFragment : Fragment() {

    private var _binding:  FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
            .apply {
                viewModel = mainViewModel
                lifecycleOwner = viewLifecycleOwner
            }

        /*binding.extendedFab.setOnClickListener{
            requireContext().let {
                Toast.makeText(it, getString(R.string.property_created), Toast.LENGTH_SHORT).show()
            }
            findNavController().navigate(R.id.myPropertiesFragment)
        }*/

        mainViewModel.unsuccessPost.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Toast.makeText(activity, getString(error), Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.nextFragment.observe(viewLifecycleOwner){
            if (it == true){
                findNavController().navigate(R.id.myPropertiesFragment)
            }
        }

        binding.icBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }



}