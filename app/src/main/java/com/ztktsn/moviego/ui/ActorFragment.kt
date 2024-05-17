package com.ztktsn.moviego.ui


import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ztktsn.moviego.R
import com.ztktsn.moviego.adapter.ActorAdapter
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ztktsn.moviego.ViewModel.ActorViewModel
import com.ztktsn.moviego.databinding.FragmentActorBinding
import com.ztktsn.moviego.model.ActorState
import com.ztktsn.moviego.network.ApiClient


class ActorFragment : Fragment() {
    private var adapter: ActorAdapter? = null
    private var _binding: FragmentActorBinding?=null
    private val binding get() = _binding!!



    private val viewModel: ActorViewModel by lazy {
        ViewModelProvider(
            this,
            ActorViewModel.Provider(ApiClient.instance)
        ).get<ActorViewModel>(ActorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actorRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = ActorAdapter(
            onActorClick = {
//                handleMovieClick(it)
            }
        )
        binding.actorRecycler.adapter = adapter
        viewModel.fetchActorList()

        val bottomNavigationView = binding?.bottomNavigation
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }

        viewModel.actorListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ActorState.Success -> adapter?.submitList(state.items)
                is ActorState.Loading -> {
                    with(binding) {
                        progressBar.isVisible = state.isLoading
                        actorRecycler.isVisible = !state.isLoading
                    }
                }

                is ActorState.Error -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle(R.string.error_title)
                        .setMessage(state.message ?: getString(R.string.error_message))
                        .show()
                }
            }
        }


    }


        private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.account -> {
                    // No need to load HomeFragment again if already on the home page
                    return@OnNavigationItemSelectedListener true
                }
                R.id.saved -> {
                    loadFragment(SavedFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        }
    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.account, fragment)
            .addToBackStack(null)
            .commit()
    }


}