package com.example.librarypractica

import java.util.*

class Book() {
    var cover:Int = 0
    var title:String = " "
    var authors = arrayOf<String>()
    lateinit var releaseDate: Date
    var synopsis:String = " "


    constructor(cover: Int, title: String) : this() {
        this.cover = cover
        this.title = title
    }
}