package com.example.myapplication.Pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.DataBase.UserCRUD
import com.example.myapplication.DataBase.UserModel
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    var usuarios:ArrayList<UserModel>? = null
    var crud: UserCRUD? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.login)
        val signup = findViewById<Button>(R.id.signup)

        val username = findViewById<EditText>(R.id.etuserlogin)
        val pass = findViewById<EditText>(R.id.etpasslogin)

        login.setOnClickListener {this.verifylogin(username.text.toString(), pass.text.toString())}
        signup.setOnClickListener { startActivity(Intent(applicationContext, NewUser::class.java)) }
    }

    private fun verifylogin(username: String, pass: String){

        crud = UserCRUD(this)
        usuarios = crud?.getUsuarios()
        var isUser = false

        if (username != "" && pass != ""){
            for (user in usuarios.orEmpty()){
                if(username == user.user && pass == user.password){
                isUser = true
                val intent = Intent(applicationContext, GamesList::class.java)
                intent.putExtra("UsuarioActual", user.user_id)
                startActivity(intent)
                }
            }
        }
        else{
            showError(username,pass)
            isUser = true
        }

        if (!isUser){
            showError(username,pass)
        }
    }

    private fun showError(username: String, pass: String){
        if(pass == "" && username != ""){
            Toast.makeText(this, "Campo Pass Obligatorio", Toast.LENGTH_SHORT).show()
        }
        else if(username == "" && pass != ""){
            Toast.makeText(this, "Campo User Obligatorio", Toast.LENGTH_SHORT).show()
        }
        else if(username == "" && pass == ""){
            Toast.makeText(this, "Campos Pass y User Obligatorios", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Usuario y/o Contraseña Incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}