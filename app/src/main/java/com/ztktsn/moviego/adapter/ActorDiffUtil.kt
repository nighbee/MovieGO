package com.ztktsn.moviego.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ztktsn.moviego.model.Actor
import com.ztktsn.moviego.model.Movie

class ActorDiffUtil : DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }

}