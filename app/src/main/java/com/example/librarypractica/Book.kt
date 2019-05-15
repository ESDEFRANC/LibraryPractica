package com.example.librarypractica

import com.google.gson.annotations.SerializedName

/**
 * This class retrieves information from the google api books
 * @author Pol Piedra & Adrià Altemir.
 * @constructor Not private constructor used in Retrofit.
 */

class Book {
    @SerializedName("title")
            /**
             * Retrieves book's api title
             */
    var title:String? = null
    @SerializedName("authors")
            /**
             * Retrieves book's api authors
             */
    var authors:Array<String>? = null
    @SerializedName("publisher")
            /**
             * Retrieves book's api publisher
             */
    var publisher:String? = null
    @SerializedName("description")
            /**
             * Retrieves book's api description
             */
    var description:String? = null
    @SerializedName("imageLinks")
            /**
             * Retrieves book's api image links
             */
    var imageLinks:ImageLinks? = null
}