package com.example.librarypractica

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BooksResponse {
    @SerializedName("items")
    @Expose
    var books: List<Item>? = null
}