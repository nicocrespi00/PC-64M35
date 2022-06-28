package com.example.myapplication.Pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.DataBase.UserCRUD
import com.example.myapplication.DataBase.UserModel
import com.example.myapplication.R

class UserConfiguration : AppCompatActivity() {

    private var idActualUser: String? = null
    var usuario: UserModel? = null
    var crud: UserCRUD? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_usuario)

        val objetoIntent: Intent=intent
        idActualUser = objetoIntent.getStringExtra("UsuarioActual")

        this.CargarDatos()


    }

    private fun CargarDatos() {
        crud = UserCRUD(this)
        usuario = crud?.getUsuario(idActualUser)

        val id = findViewById<TextView>(R.id.editTextId)
        id.setText(usuario?.user_id ?: "")
        val name = findViewById<TextView>(R.id.editTextName)
        name.setText(usuario?.name ?: "")
        val user = findViewById<EditText>(R.id.editTextUser)
        user.setText(usuario?.user ?: "")
        val pass = findViewById<EditText>(R.id.editTextPass)
        pass.setText(usuario?.password ?: "")

        val actualizar = findViewById<Button>(R.id.btactualizar)
        actualizar.setOnClickListener {
            crud?.updateUsuario(user.text.toString(),pass.text.toString(),idActualUser)
        }
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