package com.ztktsn.moviego.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ztktsn.moviego.R
import com.ztktsn.moviego.presentation.ViewModel.MovieViewModel
import com.ztktsn.moviego.data.adapter.MovieAdapter
import com.ztktsn.moviego.data.model.MovieState
import com.ztktsn.moviego.data.network.ApiClient
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ztktsn.moviego.databinding.FragmentSavedBinding


class SavedFragment : Fragment() {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchEditText: EditText
    private var adapter: MovieAdapter? = null

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(
            this,
            MovieViewModel.Provider(ApiClient.instance)
        ).get<MovieViewModel>(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter(
            onMovieClick = {
//                handleMovieClick(it)
            }
        )
        binding.latestRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.latestRecycler.adapter = adapter

        viewModel.fetchLatestList()

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)


        viewModel.movielistState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MovieState.Success -> adapter?.submitList(state.items)
                is MovieState.Loading -> {
                    with(binding) {
                        progressBar.isVisible = state.isLoading
                        latestRecycler.isVisible = !state.isLoading
                    }
                }

                is MovieState.Error -> {
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
                R.id.saved -> {
                    // No need to load HomeFragment again if already on the home page
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    loadFragment(ActorFragment())
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        }

    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.saved, fragment)
            .addToBackStack(null)
            .commit()
    }
}