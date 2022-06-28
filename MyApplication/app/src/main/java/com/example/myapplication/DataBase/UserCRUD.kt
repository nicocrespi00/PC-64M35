package com.example.myapplication.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class UserCRUD(context: Context) {

    private var helper: UserSQLiteOpenHelper? = null

    init {
        helper = UserSQLiteOpenHelper(context)
    }

    fun nuevoUsuario(name:String? , user: String, pass: String) {

        val db:SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()
        values.put(UserContract.Companion.Entrada.COLUMNA_NOMBRE, name)
        values.put(UserContract.Companion.Entrada.COLUMNA_PASSWORD, pass)
        values.put(UserContract.Companion.Entrada.COLUMNA_USUARIO, user)

        db.insert(UserContract.Companion.Entrada.NOMBRE_TABLA, null, values)

        db.close()
    }

    fun getUsuarios():ArrayList<UserModel> {

        val users:ArrayList<UserModel> = ArrayList()

        val db:SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(
            UserContract.Companion.Entrada.COLUMNA_ID,
            UserContract.Companion.Entrada.COLUMNA_NOMBRE,
            UserContract.Companion.Entrada.COLUMNA_USUARIO,
            UserContract.Companion.Entrada.COLUMNA_PASSWORD
        )

        val c:Cursor = db.query(
            UserContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )

        while (c.moveToNext()) {
            users.add(
                UserModel(
                c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_NOMBRE)),
                c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_USUARIO)),
                c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_PASSWORD))
                )
            )
        }
        db.close()

        return users
    }

    fun getUsuario(id: String?): UserModel {

        var user: UserModel? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(
            UserContract.Companion.Entrada.COLUMNA_ID,
            UserContract.Companion.Entrada.COLUMNA_NOMBRE,
            UserContract.Companion.Entrada.COLUMNA_USUARIO,
            UserContract.Companion.Entrada.COLUMNA_PASSWORD
        )

        val c:Cursor = db.query(
            UserContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            " user_id = $id",
            null,
            null,
            null,
            null
        )

        while (c.moveToNext()) {
            user = UserModel(c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_NOMBRE)),
                c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_USUARIO)),
                c.getString(c.getColumnIndexOrThrow(UserContract.Companion.Entrada.COLUMNA_PASSWORD))
            )
        }
        c.close()

        return user!!
    }

    fun updateUsuario(user: String, pass: String, id:String?) {

        val db: SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()
        values.put(UserContract.Companion.Entrada.COLUMNA_PASSWORD, pass)
        values.put(UserContract.Companion.Entrada.COLUMNA_USUARIO, user)

        db.update(UserContract.Companion.Entrada.NOMBRE_TABLA, values, "user_id = ?",
            arrayOf(id)
        )

        db.close()
    }
}