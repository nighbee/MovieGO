package com.ztktsn.moviego.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ztktsn.moviego.R
import com.ztktsn.moviego.databinding.ItemHorizontalRecyclerBinding
import com.ztktsn.moviego.model.movie


class MovieAdapter(
    private val onMovieClick: (movie) -> Unit
) : ListAdapter<movie, MovieAdapter.ViewHolder>(movieDiffUtil()) {

    companion object {
        private const val MOVIE_ADAPTER_TAG = "MovieAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(MOVIE_ADAPTER_TAG, "onCreateViewHolder")
        return ViewHolder(
            ItemHorizontalRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(MOVIE_ADAPTER_TAG, "onBindViewHolder: $position")
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemHorizontalRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: movie) {
            with(binding) {

                Glide
                    .with(root.context)
                    .load("https://image.tmdb.org/t/p/original" + movie.imageUrl)
                    .placeholder(R.drawable.icon_logo)
                    .into(movieImg)

                movieName.text = movie.title


                root.setOnClickListener {
                    onMovieClick(movie)
                }
            }
        }
    }
}