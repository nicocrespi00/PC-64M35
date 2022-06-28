package com.example.myapplication.RecyclerViewAdaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ApiConnection.Games.GamesItem
import com.example.myapplication.R


class GamesListAdapter(private val games:List<GamesItem>) : RecyclerView.Adapter<GamesListViewHolder>()
{
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)

        return GamesListViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder: GamesListViewHolder, position: Int) {
        val item = games[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int =games.size
}