package com.example.librarypractica

import com.google.gson.GsonBuilder
import okhttp3.*



class FetchBooks {
    val BASE_URL = "https://www.googleapis.com/books/v1/volumes?q="

    fun fetchJSON(query: String?):Volumes{

        val request = Request.
            Builder().
            url(BASE_URL + query).
            build()

        val client = OkHttpClient()
        val response: Response = client.newCall(request).execute()
        val body = response.body()?.string()
        val gson = GsonBuilder().create()

        return gson.fromJson(body, Volumes::class.java)
    }
}