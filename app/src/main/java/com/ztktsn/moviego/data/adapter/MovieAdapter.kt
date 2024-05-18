package com.ztktsn.moviego.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ztktsn.moviego.R
import com.ztktsn.moviego.databinding.ItemMovieBinding
import com.ztktsn.moviego.data.model.Movie

class MovieAdapter(
    private val onMovieClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffUtil()) {

    companion object {
        private const val MOVIE_ADAPTER_TAG = "MovieAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(MOVIE_ADAPTER_TAG, "onCreateViewHolder")
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(MOVIE_ADAPTER_TAG, "onBindViewHolder: $position")
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {

                Glide
                    .with(root.context)
                    .load("https://image.tmdb.org/t/p/original" + movie.imageUrl)
                    .placeholder(R.drawable.icon_logo)
                    .into(movieImage)

                movieTitle.text = movie.title
                movieDescription.text = movie.shortDescription
                movieRating.text = movie.rating.toString()
                movieGenre.text = movie.genre.first().value

                root.setOnClickListener {
                    onMovieClick(movie)
                }
            }
        }
    }
}