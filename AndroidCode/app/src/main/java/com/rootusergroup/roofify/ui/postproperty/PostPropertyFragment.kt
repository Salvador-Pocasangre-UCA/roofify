package com.rootusergroup.roofify.ui.postproperty

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentPostPropertyBinding
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel


class PostPropertyFragment : Fragment() {

    private var _binding: FragmentPostPropertyBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostPropertyBinding.inflate(inflater, container, false)


        val items = listOf("Compra", "Alquiler")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_item, items)
        (binding.listCategory).setAdapter(adapter)

        binding.icBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.extendedFab.setOnClickListener {
            findNavController().navigate(R.id.paymentFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.cardView.setOnClickListener {
            //pickImageFromGallery()
        }

    }

    /*var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
           val data: Intent? = result.data
           binding.linearLayoutAddPhoto.visibility = View.GONE
           binding.photoImage.setImageURI(data?.data)
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }
    */
     */

}