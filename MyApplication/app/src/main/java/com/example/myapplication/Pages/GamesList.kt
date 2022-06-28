package com.example.myapplication.Pages

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ApiConnection.APIService
import com.example.myapplication.ApiConnection.Games.GamesItem
import com.example.myapplication.R
import com.example.myapplication.RecyclerViewAdaptors.GamesListAdapter
import com.example.myapplication.databinding.ContentPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GamesList : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ContentPageBinding
    private lateinit var adapter: GamesListAdapter
    private val gamesList = mutableListOf<GamesItem>()
    private var idActualUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svGames.setOnQueryTextListener(this)

        initRecyclerView()

        val objetoIntent: Intent=intent
        idActualUser = objetoIntent.getStringExtra("UsuarioActual")
        searchAllGames()
    }

    private fun initRecyclerView() {
        adapter = GamesListAdapter(gamesList)
        binding.listGames.layoutManager = LinearLayoutManager(this)
        binding.listGames.adapter = adapter
        adapter.setOnItemClickListener(object : GamesListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                onQueryTextSubmit((position).toString())
            }
        })
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/games/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchAllGames(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAllGames()
            val games = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    val gamesinfo :List<GamesItem> = games ?: emptyList()
                    gamesList.clear()
                    gamesList.addAll(gamesinfo)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            if(query.toInt() > 0){
                val queryId: Int = query.toInt()
                startActivity(Intent(this, GameDetails::class.java).putExtra("UsuarioActual", idActualUser).putExtra("id", queryId))
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