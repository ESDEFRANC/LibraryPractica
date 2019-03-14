package com.example.librarypractica

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AdapterCustomBooks(mContext: Context?, movies:List<Book>) : RecyclerView.Adapter<AdapterCustomBooks.ViewHolder>() {

    private var mContext: Context? = null
    private var books: List<Book> = ArrayList()

    init {
        this.books = movies
        this.mContext = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = (LayoutInflater.from(parent.context).inflate(R.layout.template_recycler, parent, false))
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books[position])

    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var bookCover: ImageView? = null
        var bookTitle: TextView? = null
        init {
            bookCover = root.findViewById(R.id.bookCover)
            bookTitle = root.findViewById(R.id.bookTitle)
        }

        fun bind(book:Book) {
            bookCover!!.setImageResource(book.cover)
            bookTitle!!.text = book.title
        }
    }
}