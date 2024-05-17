package com.ztktsn.moviego.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.ztktsn.moviego.R
import com.ztktsn.moviego.databinding.ItemActorBinding
import com.ztktsn.moviego.model.Actor
import com.ztktsn.moviego.model.Movie


class ActorAdapter(
    private val onActorClick: (Movie) -> Unit
) : ListAdapter<Actor, ActorAdapter.ViewHolder>(ActorDiffUtil()) {


    companion object {
        private const val ACTOR_ADAPTER_TAG = "ActorTag"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(ACTOR_ADAPTER_TAG, "onCreateViewHolder")
        return ViewHolder(
            ItemActorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(ACTOR_ADAPTER_TAG, "onBindViewHolder: $position")
        holder.bind(getItem(position))
    }


    inner class ViewHolder(
        private val binding: ItemActorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            with(binding) {

                Glide
                    .with(root.context)
                    .load("https://image.tmdb.org/t/p/original" + actor.imageUrl)
                    .placeholder(R.drawable.icon_logo)
                    .into(actorImage)

               actorName.text= actor.name
//
//                root.setOnClickListener {
//                    onActorClick(actor)
//                }
            }
        }
    }
}