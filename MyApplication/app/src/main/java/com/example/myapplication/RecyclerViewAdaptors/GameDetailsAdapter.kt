package com.example.myapplication.RecyclerViewAdaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ApiConnection.Game.Game
import com.example.myapplication.R

class GameDetailsAdapter(private val game: List<Game>):RecyclerView.Adapter<GameDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameDetailsViewHolder(layoutInflater.inflate(R.layout.item_game,parent,false))
    }

    override fun onBindViewHolder(holder: GameDetailsViewHolder, position: Int) {
        val item = game[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = game.size

}