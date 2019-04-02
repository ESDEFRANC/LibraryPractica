package com.example.librarypractica

import com.google.gson.annotations.SerializedName

class Volumes(volumeInfo: List<Book>) {
    @SerializedName("items")
    var volumeInfo:List<Book>? = null

    init {
        this.volumeInfo = volumeInfo
    }
}