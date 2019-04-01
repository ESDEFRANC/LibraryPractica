package com.example.librarypractica

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.template_recycler.view.*

class AdapterCustomBooks(mContext: Context?, movies:List<Book>, val listener: (Book) -> Unit) : RecyclerView.Adapter<AdapterCustomBooks.ViewHolder>() {


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
        holder.bind(books[position],listener)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item:Book, listener: (Book) -> Unit) = with(itemView) {
           // bookCover!!.setImageResource(item.img)
            bookTitle!!.text = item.title
            setOnClickListener{
                listener(item)
            }
        }
    }
}