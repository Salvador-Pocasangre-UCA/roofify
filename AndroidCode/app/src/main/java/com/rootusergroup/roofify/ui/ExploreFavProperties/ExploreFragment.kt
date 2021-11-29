package com.rootusergroup.roofify.ui.ExploreFavProperties

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.rootusergroup.roofify.MainApplication
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentExploreBinding
import com.rootusergroup.roofify.ui.loginSignup.LoginAndSignupActivity
import com.rootusergroup.roofify.ui.recyclerView.PostRecyclerViewAdapter
import com.rootusergroup.roofify.ui.viewmodel.MainViewModel
import com.rootusergroup.roofify.ui.viewmodel.MainViewModelFactory


class ExploreFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener{

    private val mainFactory by lazy {
        val app = requireActivity().application as MainApplication
        MainViewModelFactory(app.myappRepository)
    }

    private val mainViewModel: MainViewModel by viewModels{
        mainFactory
    }

    private var _binding:  FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentExploreBinding.inflate(inflater, container, false)

       binding.hamburguerImage.setOnClickListener {
            if(!binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }else {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

           val size: Int = binding.navigationView.menu.size()
           for (i in 0 until size) {
               binding.navigationView.menu.getItem(i).isChecked = false
           }
        }
        binding.navigationView.setNavigationItemSelectedListener(this)

        binding.filterImage.setOnClickListener {
            findNavController().navigate(R.id.searchFiltersFragment)
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = mainViewModel
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_menu_item ->  {
                findNavController().navigate(R.id.fragment_UserProfile)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.myproperties_menu_item ->  {
                findNavController().navigate(R.id.myPropertiesFragment)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.logout_menu_item ->  {
                requireContext().let {
                    it.startActivity(Intent(it, LoginAndSignupActivity::class.java))
                }
                activity?.finish()
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        return true
    }
}