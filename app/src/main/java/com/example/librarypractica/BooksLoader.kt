package com.example.librarypractica

import android.content.Context
import android.support.v4.content.AsyncTaskLoader


class BooksLoader(context: Context, url:String?): AsyncTaskLoader<List<Book>>(context) {

    var url:String? = null

    companion object {
        var arrayList:ArrayList<Book>? = null
    }
    init {
        this.url = url
    }


    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): List<Book>? {
        if  (url == null) {
            return null
        }
        arrayList = NetworkUtils.fetchBooksData(url!!)
        return arrayList

    }
}