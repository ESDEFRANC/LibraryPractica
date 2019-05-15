package com.example.librarypractica
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface allow us to access to the diferent url's that an api can have.
 * @author Pol Piedra & Adrià Altemir.
 */

interface GoogleAPI {
    /**
     * Get tag is provided by retrofit. It allow us to make a get request to an api.
     */
    @GET("volumes/")
            /**
             * Give us the count of the items in RecyclerView
             * @param query set the query parameter that the user has to write. Book titles in this case.
             * @return an interface Call provided by Retrofit that managed the responses giving an object model.
             */
    fun getSearchedBooks(
        @Query("q") query:String
    ): Call<BooksResponse>
}