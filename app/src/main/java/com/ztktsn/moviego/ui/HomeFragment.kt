package com.ztktsn.moviego.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ztktsn.moviego.R
import com.ztktsn.moviego.ViewModel.MovieViewModel
import com.ztktsn.moviego.adapter.MovieAdapter
import com.ztktsn.moviego.databinding.FragmentHomeBinding
import com.ztktsn.moviego.model.MovieState
import com.ztktsn.moviego.network.ApiClient



class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter(
            onMovieClick = {
//                handleMovieClick(it)
            }
        )

        binding.movieRecycler.adapter = adapter
        viewModel.fetchOfferList()

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        viewModel.movielistState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MovieState.Success -> adapter?.submitList(state.items)
                is MovieState.Loading -> {
                    with(binding) {
                        progressBar.isVisible = state.isLoading
                        movieRecycler.isVisible = !state.isLoading
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

    // test perehoda
     private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // No need to load HomeFragment again if already on the home page
                    return@OnNavigationItemSelectedListener true
                }
                R.id.saved -> {
                    loadFragment(savedFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    loadFragment(accountFragment())
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        }

    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home, fragment)
            .addToBackStack(null)
            .commit()
    }

}