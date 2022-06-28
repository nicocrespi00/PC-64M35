package com.example.myapplication.Pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.DataBase.UserCRUD
import com.example.myapplication.R

class NewUser : AppCompatActivity(){

    var crud: UserCRUD? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        val register = findViewById<Button>(R.id.btadd)

        val newname = findViewById<EditText>(R.id.etname)
        val newuser = findViewById<EditText>(R.id.etuser)
        val newpass = findViewById<EditText>(R.id.etpass)

        register.setOnClickListener {this.verifySignUp(newname.text.toString(),
            newuser.text.toString(), newpass.text.toString())}

    }

    private fun verifySignUp(newname: String, newuser: String, newpass: String){
        crud = UserCRUD(this)

        if (newuser != "" && newpass != "" && newname != "") {
            crud?.nuevoUsuario(newname, newuser, newpass)
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        else{
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
        }

    }

}