package com.example.librarypractica
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleAPI {
    @GET("volumes/")
    fun getSearchedBooks(
        @Query("q") query:String
    ): Call<BooksResponse>
}