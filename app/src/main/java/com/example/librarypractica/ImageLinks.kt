package com.example.librarypractica

import com.google.gson.annotations.SerializedName

/**
 * This class retrieves information from the google api books
 * @author Pol Piedra & Adrià Altemir.
 * @constructor Not private constructor used in Retrofit.
 */

class ImageLinks {
    @SerializedName("thumbnail")
            /**
             * Retrieves book's api thumbnail
             */
    var thumbnail:String? = null
}
