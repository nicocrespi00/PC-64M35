package com.example.myapplication.RecyclerViewAdaptors

import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ApiConnection.Game.Game
import com.example.myapplication.databinding.ItemGameBinding
import com.squareup.picasso.Picasso


class GameDetailsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemGameBinding.bind(view)

    fun bind(game: Game){
        binding.title.setText(game.title, TextView.BufferType.NORMAL)
        binding.platform.setText(game.platform, TextView.BufferType.NORMAL)
        binding.date.setText(game.releaseDate, TextView.BufferType.NORMAL)
        binding.genre.setText(game.genre, TextView.BufferType.NORMAL)
        binding.description.movementMethod = ScrollingMovementMethod()
        binding.developer.setText(game.developer, TextView.BufferType.NORMAL)
        binding.description.setText(game.description, TextView.BufferType.NORMAL)
        Picasso.get().load(game.thumbnail).into(binding.gameimage)
    }


}