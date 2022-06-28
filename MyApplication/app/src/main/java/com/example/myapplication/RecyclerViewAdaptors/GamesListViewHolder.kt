package com.example.myapplication.RecyclerViewAdaptors

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ApiConnection.Games.GamesItem
import com.example.myapplication.databinding.ItemCardBinding
import com.squareup.picasso.Picasso

class GamesListViewHolder(view: View, listener: GamesListAdapter.onItemClickListener): RecyclerView.ViewHolder(view) {

    private val binding = ItemCardBinding.bind(view)
    private var gameId = 0

    fun bind(game: GamesItem){
        binding.title.setText(game.title, TextView.BufferType.NORMAL)
        binding.platform.setText(game.platform, TextView.BufferType.NORMAL)
        binding.date.setText(game.releaseDate, TextView.BufferType.NORMAL)
        Picasso.get().load(game.thumbnail).into(binding.gameimage)
        gameId = game.id
    }

    init{
        view.setOnClickListener {
            listener.onItemClick(gameId)
        }
    }

}