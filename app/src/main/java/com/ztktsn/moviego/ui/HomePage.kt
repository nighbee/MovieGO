package com.ztktsn.moviego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ztktsn.moviego.adapter.MovieAdapter
import com.ztktsn.moviego.model.Movie
import com.ztktsn.moviego.model.movieApi
//import kotlinx.android.synthetic.main.fragment_home_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePage: Fragment() {
    private lateinit var movieListRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchEditText()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(
            onMovieClick = {
                // Handle movie click
            }
        )
        movieListRecyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupSearchEditText() {
        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(v.text.toString())
                true
            } else false
        }
    }

    private fun performSearch(query: String) {
        movieApi.api.getMovieByName(query).enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    movieAdapter.submitList(response.body())
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}