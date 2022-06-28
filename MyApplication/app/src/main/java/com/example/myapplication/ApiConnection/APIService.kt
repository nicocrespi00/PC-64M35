package com.example.myapplication.ApiConnection

import com.example.myapplication.ApiConnection.Game.Game
import com.example.myapplication.ApiConnection.Games.Games
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

public interface APIService {
    @GET()
    suspend fun getGameById(@Url id: String):Response<Game>

    @GET(".")
    suspend fun getAllGames():Response<Games>
}