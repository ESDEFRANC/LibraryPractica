package com.example.librarypractica

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksSearchedRepository private constructor(private val api:GoogleAPI){

    fun getBooks(query: String, callback: OnGetSearchedBooksCallback) {
        api.getSearchedBooks(query).enqueue(object: Callback<BooksResponse> {

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    val booksResponse = response.body()
                    if (booksResponse?.books != null) {
                        callback.onSuccess((booksResponse.books as ArrayList<Item>))
                    }else {
                        callback.onError()
                    }
                }else {
                    callback.onError()
                }
            }

        })
    }

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
        private var repository:BooksSearchedRepository? = null

        val instance :BooksSearchedRepository
        get() {
            if (repository == null) {
                val retrofit = Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.
                        create()).
                    build()

                repository = BooksSearchedRepository(retrofit.create(GoogleAPI::class.java))
            }
            return repository as BooksSearchedRepository
        }
    }
}