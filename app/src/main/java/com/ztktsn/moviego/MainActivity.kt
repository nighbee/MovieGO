package com.ztktsn.moviego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ztktsn.moviego.adapter.movieAdapter
import com.ztktsn.moviego.model.movie
import com.ztktsn.moviego.model.movieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var movieListRecyclerView: RecyclerView
    private lateinit var movieAdapter: movieAdapter
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieAdapter = movieAdapter(
            onMovieClick = {

            }
        )
        searchEditText = findViewById(R.id.search)
        movieListRecyclerView = findViewById(R.id.movieRecycler)
        movieListRecyclerView.adapter = movieAdapter
        movieListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(v.text.toString())
                true
            } else false
        }

    }

    private fun performSearch(query: String) {
        movieApi.api.getMovieByName(query).enqueue(object : Callback<List<movie>> {
            override fun onResponse(call: Call<List<movie>>, response: Response<List<movie>>) {
                if (response.isSuccessful) {
                    movieAdapter.submitList(response.body())
                }
            }

            override fun onFailure(call: Call<List<movie>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
