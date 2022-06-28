package com.example.myapplication.DataBase

import android.provider.BaseColumns

class UserContract {

    companion object {

        const val VERSION = 1

        class Entrada: BaseColumns{

            companion object {
                val NOMBRE_TABLA = "usuarios"
                val COLUMNA_ID = "user_id"
                val COLUMNA_NOMBRE = "name"
                val COLUMNA_USUARIO = "user"
                val COLUMNA_PASSWORD = "password"
            }
        }
    }
}