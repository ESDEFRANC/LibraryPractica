package com.example.librarypractica

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User():Serializable {
    @SerializedName("username")
    var username:String = ""
    @SerializedName("password")
    var password:String = ""
    @SerializedName("favoriteBooks")
    var favoriteBooks:ArrayList<Book>? = null

    constructor(mail: String, password: String): this() {
        this.username = mail
        this.password = password
    }
}