package com.rootusergroup.roofify.ui.ExploreFavProperties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rootusergroup.roofify.MainApplication
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentFavoritesBinding
import com.rootusergroup.roofify.databinding.FragmentMyPropertiesBinding
import com.rootusergroup.roofify.ui.recyclerView.PostRecyclerViewAdapter
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel
import com.rootusergroup.roofify.ui.viewmodel.MainViewModelFactory


class MyPropertiesFragment : Fragment() {

    private var _binding: FragmentMyPropertiesBinding? = null
    private val binding get() = _binding!!

    private val mainFactory by lazy {
        val app = requireActivity().application as MainApplication
        MainViewModelFactory(app.myappRepository)
    }

    private val mainViewModel: MainViewModel by viewModels{
        mainFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyPropertiesBinding.inflate(inflater, container, false)

        binding.icBackArrow.setOnClickListener {
            findNavController().popBackStack(R.id.exploreFragment,false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val rvAdpater = PostRecyclerViewAdapter()

        binding.myPropertiesRecyclerView.apply {
            adapter = rvAdpater
            layoutManager = LinearLayoutManager(requireContext())
        }

        mainViewModel.posts.observe(viewLifecycleOwner) {
            if(it != null){
                rvAdpater.setData(it)
            }

        }
    }

}