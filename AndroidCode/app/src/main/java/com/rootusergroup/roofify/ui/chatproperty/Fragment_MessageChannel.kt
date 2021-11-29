package com.rootusergroup.roofify.ui.chatproperty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentFavoritesBinding
import com.rootusergroup.roofify.databinding.FragmentMessageChannelBinding

class Fragment_MessageChannel : Fragment() {

    private var _binding: FragmentMessageChannelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMessageChannelBinding.inflate(inflater, container, false)

        binding.icBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}