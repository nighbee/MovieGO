package com.ztktsn.moviego.data.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ztktsn.moviego.data.model.Actor

class ActorDiffUtil : DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }

}