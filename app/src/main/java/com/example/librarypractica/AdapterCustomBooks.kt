package com.example.librarypractica

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.template_recycler.view.*

/**
 * This class defines the data model for an specific RecyclerView
 * @author Pol Piedra & Adrià Altemir.
 * @param  mContext to work.
 * @param books list of the elements desired.
 * @param listener catch event in RecyclerView.
 * @constructor Creates a RecyclerViewAdapter Object.
 */

class AdapterCustomBooks(mContext: Context?, books:List<Item>, val listener: (Item) -> Unit) : RecyclerView.Adapter<AdapterCustomBooks.ViewHolder>() {

    /**
     * Context needed to work
     */
    private var mContext: Context? = null
    /**
     * Model design for the RecyclerView
     */
    private var books:List<Item> = ArrayList()

    init {
        this.mContext = mContext
        this.books = books
    }

    /**
     * Creates a ViewHolder to work with elements of the view
     *@return the object ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = (LayoutInflater.from(parent.context).inflate(R.layout.template_recycler, parent, false))
        return ViewHolder(view)
    }

    /**
     * Give us the count of the items in RecyclerView
     *@return the size of the items list
     */

    override fun getItemCount(): Int {
        return books.size
    }

    /**
     * Binds the ViewHolder to the elements of the list template
     *@param holder object ViewHolder of the view
     *@param position row of the list view
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books[position],listener)


    }
    /**
     * Set the elements for the list.
     *@param books list of objects item
     */

    fun setBooks(books: List<Item>) {
        this.books = books
    }

    /**
     * This class defines the object ViewHolder.
     * @author Pol Piedra & Adrià Altemir.
     * @param itemView The view element of the list template.
     * @constructor Creates a RecyclerViewAdapter Object.
     */

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Bind the object and the listener of the view.
         * @param item Object of the list items.
         * @param listener catch the specific event in the view element.
         */
        fun bind(item:Item, listener: (Item) -> Unit) = with(itemView) {
            Picasso.get().load(item.volumeInfo?.imageLinks?.thumbnail).placeholder(R.drawable.ic_launcher_foreground).into(bookCover)
            bookTitle!!.text = item.volumeInfo?.title
            setOnClickListener{
                listener(item)
            }
        }
    }
}