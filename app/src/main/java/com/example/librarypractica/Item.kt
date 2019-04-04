package com.example.librarypractica

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("id")
    @Expose
    var id:String ? = null
    @SerializedName("volumeInfo")
    @Expose
    var volumeInfo:Book ? = null
}