package com.ztktsn.moviego.adapter


import androidx.recyclerview.widget.DiffUtil
import com.ztktsn.moviego.model.Movie

// In progress

class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie ): Boolean {
        return oldItem == newItem
    }

}