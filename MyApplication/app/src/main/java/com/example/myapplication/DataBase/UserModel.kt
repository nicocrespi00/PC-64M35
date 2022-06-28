package com.example.myapplication.DataBase

class UserModel constructor(user_id: String, name: String, user: String, password: String) {
    var user_id: String? = null
    var name: String? = null
    var user: String? = null
    var password: String? = null

    init {
        this.user_id = user_id
        this.name = name
        this.user = user
        this.password = password
    }


}