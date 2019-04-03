package com.example.librarypractica

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

class NetworkUtils {
    companion object {
        private var json: String? = null

        fun fetchBooksData(url: String): ArrayList<Book>? {

            var books: ArrayList<Book>? = null

            try {
                Thread.sleep(1000)
                val urlCreated: URL = createURL(url)!!
                json = makeHttpRequest(urlCreated)

                books = extractJSON(json)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return books

        }

        private fun extractJSON(json: String?): ArrayList<Book>? {
            var title = "title"
            var author = "author"
            var imageUrl = "imageUrl"
            var publisher = "publisher"
            var description = "description"

            val booksList = ArrayList<Book>()

            val jsonObject = JSONObject(json)
            val items = jsonObject.getJSONArray("items")
            for (i in 0..(items.length() - 1)) {
                val jsonObject1 = items.getJSONObject(i)
                val volumeInfo = jsonObject1.getJSONObject("volumeInfo");
                title = volumeInfo.getString("title");
                if (volumeInfo.has("publisher")) {
                    publisher = volumeInfo.getString("publisher");
                }
                if (volumeInfo.has("authors")) {
                    var authors = volumeInfo.getJSONArray("authors")
                    author = authors.getString(0);
                }
                if (volumeInfo.has("description")) {
                    description = volumeInfo.getString("description")
                }
                    if (volumeInfo.has("imageLinks")) {
                        val imageLinks = volumeInfo.getJSONObject("imageLinks");
                        imageUrl = imageLinks.getString("smallThumbnail");
                    }
                    val bookItem = Book(title, author, publisher, description, imageUrl)
                    booksList.add(bookItem)

                }
                return booksList
            }


        private fun makeHttpRequest(url: URL): String? {
            val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream: InputStream
            var json: String? = null
            httpURLConnection.readTimeout = 10000
            httpURLConnection.connectTimeout = 15000
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()

            if (httpURLConnection.responseCode == 200) {
                inputStream = httpURLConnection.inputStream
                json = streamToJson(inputStream)
            }

            return json
        }

        private fun streamToJson(inputStream: InputStream?): String? {
            val output = StringBuilder()
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
                val bufferedReader = BufferedReader(inputStreamReader)
                var line = bufferedReader.readLine()
                while (line != null) {
                    output.append(line)
                    line = bufferedReader.readLine()
                }
            }
            return output.toString()
        }

        private fun createURL(url: String): URL? {
            var urlToCreate: URL? = null
            try {
                urlToCreate = URL(url)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return urlToCreate
        }

    }


}
