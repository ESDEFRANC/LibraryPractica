package com.example.librarypractica

class User() {
    var email:String = ""
    var password:String = ""
    var list:ArrayList<Book>? = null

    constructor(mail: String, password: String): this() {
        this.email = mail
        this.password = password
    }
}