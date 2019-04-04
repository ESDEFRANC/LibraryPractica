package com.example.librarypractica

import com.google.gson.annotations.SerializedName

class Book {

    @SerializedName("title")
    var title:String? = null
    @SerializedName("authors")
    var authors:Array<String>? = null
    @SerializedName("publisher")
    var publisher:String? = null
    @SerializedName("description")
    var description:String? = null
    @SerializedName("imageLinks")
    var imageLinks:ImageLinks? = null
}