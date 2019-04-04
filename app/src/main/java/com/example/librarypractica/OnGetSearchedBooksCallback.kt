package com.example.librarypractica

interface OnGetSearchedBooksCallback {
    fun onSuccess(books: List<Item>)

    fun onError()
}