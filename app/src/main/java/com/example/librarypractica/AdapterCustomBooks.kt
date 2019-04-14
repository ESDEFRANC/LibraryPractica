package com.example.librarypractica

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.template_recycler.view.*

class AdapterCustomBooks(mContext: Context?, books:List<Item>, val listener: (Item) -> Unit) : RecyclerView.Adapter<AdapterCustomBooks.ViewHolder>() {


    private var mContext: Context? = null
    private var books:List<Item> = ArrayList()

    init {
        this.mContext = mContext
        this.books = books
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

    fun setBooks(books: List<Item>) {
        this.books = books
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item:Item, listener: (Item) -> Unit) = with(itemView) {
            Picasso.get().load(item.volumeInfo?.imageLinks?.thumbnail).placeholder(R.drawable.ic_launcher_foreground).into(bookCover)
            bookTitle!!.text = item.volumeInfo?.title
            setOnClickListener{
                listener(item)
            }
        }
    }
}