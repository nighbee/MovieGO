package com.ztktsn.moviego.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ztktsn.moviego.R
import com.ztktsn.moviego.ViewModel.MovieViewModel
import com.ztktsn.moviego.adapter.MovieAdapter
import com.ztktsn.moviego.databinding.FragmentHomeBinding
import com.ztktsn.moviego.model.Movie
import com.ztktsn.moviego.model.MovieState
import com.ztktsn.moviego.network.ApiClient
import retrofit2.Callback
import android.content.Intent
import retrofit2.Call
import retrofit2.Response

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [savedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class savedFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
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

        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(v.text.toString())
                true
            } else false
        }

    }


    private fun performSearch(query: String) {
        ApiClient.instance.
        getMovieByName(query).enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    adapter?.submitList(response.body())
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Handle failure
            }
        })
    }

}