package com.example.myapplication.Pages

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ApiConnection.APIService
import com.example.myapplication.ApiConnection.Game.Game
import com.example.myapplication.R
import com.example.myapplication.RecyclerViewAdaptors.GameDetailsAdapter
import com.example.myapplication.databinding.ContentPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameDetails() : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ContentPageBinding
    private lateinit var adapter: GameDetailsAdapter
    private var idActualUser: String? = null
    private val gameToShow = mutableListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svGames.setOnQueryTextListener(this)

        initRecyclerView()

        val objetoIntent: Intent=intent
        idActualUser = objetoIntent.getStringExtra("UsuarioActual")
        var idToSearch = objetoIntent.getIntExtra("id", 0)
        searchGame(idToSearch)
    }

    private fun initRecyclerView() {
        adapter = GameDetailsAdapter(gameToShow)
        binding.listGames.layoutManager = LinearLayoutManager(this)
        binding.listGames.adapter = adapter
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchGame(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getGameById("game?id=$id")
            val game = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    var gameinfo = mutableListOf<Game>()
                    if (game != null) {
                        gameinfo.add(game)
                    }
                    gameToShow.clear()
                    gameToShow.addAll(gameinfo)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            if(query.toInt() > 0){
                val queryId: Int = query.toInt()
                searchGame(queryId)
            }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.content_page_drawer, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_item_inicio -> startActivity(Intent(this, GamesList::class.java).putExtra("UsuarioActual", idActualUser))
            R.id.nav_item_configuracionUser -> startActivity(Intent(this, UserConfiguration::class.java).putExtra("UsuarioActual", idActualUser))
            R.id.nav_item_exit -> startActivity(Intent(this, MainActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

}