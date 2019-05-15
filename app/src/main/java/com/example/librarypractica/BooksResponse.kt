package com.example.librarypractica

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * This class retrieves information from the google api books
 * @author Pol Piedra & Adrià Altemir.
 * @constructor Not private constructor used in Retrofit.
 */


class BooksResponse {
    @SerializedName("items")
    @Expose
            /**
             * Retrieves api results items.
             */
    var books: List<Item>? = null
}