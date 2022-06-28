package com.example.myapplication.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context,
    UserContract.Companion.Entrada.NOMBRE_TABLA, null,
    UserContract.VERSION
) {

    companion object {
        val CREATE_USUARIOS_TABLA = "CREATE TABLE ${UserContract.Companion.Entrada.NOMBRE_TABLA} (${UserContract.Companion.Entrada.COLUMNA_ID} INTEGER PRIMARY KEY AutoIncrement, " +
                "${UserContract.Companion.Entrada.COLUMNA_NOMBRE} TEXT, ${UserContract.Companion.Entrada.COLUMNA_USUARIO} TEXT, ${UserContract.Companion.Entrada.COLUMNA_PASSWORD} TEXT )"

        val REMOVE_USUARIOS_TABLA = "DROP TABLE IF EXISTS ${UserContract.Companion.Entrada.NOMBRE_TABLA}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USUARIOS_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i2: Int) {
        db?.execSQL(REMOVE_USUARIOS_TABLA)
        onCreate(db)
    }
}
