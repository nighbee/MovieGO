package com.ztktsn.moviego.adapter


import androidx.recyclerview.widget.DiffUtil
import com.ztktsn.moviego.model.movie

// In progress

class movieDiffUtil : DiffUtil.ItemCallback<movie>() {
    override fun areItemsTheSame(oldItem: movie, newItem: movie ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: movie, newItem: movie ): Boolean {
        return oldItem == newItem
    }

}