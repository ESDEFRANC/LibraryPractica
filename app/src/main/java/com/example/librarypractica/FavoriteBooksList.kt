package com.example.librarypractica

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteBooksList : Fragment() {

    interface OnProductClickedListener {
        fun onProductClicked(product:Book)
    }

    var books:ArrayList<Book> = ArrayList()
    var list: RecyclerView? = null
    lateinit var listenerList:OnProductClickedListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        var  view = inflater.inflate(R.layout.fragment_favorite_books_list, container, false)
        setHasOptionsMenu(true)
        return view
    }


    /*override fun onCreateOptionsMenu(menu: Menu?, inflater:MenuInflater){
        inflater.inflate(R.menu.menu, menu)

    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureList()
        //setListener()
    }

    private fun setListener() {
        list?.onIte
        }
    //}

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        addProductstoList()
        listenerList = context as OnProductClickedListener

    }

    private fun addProductstoList() {
        books.add(Book(R.drawable.la_llamada_de_cthulu, "La llamada de Cthulu"))
        books.add(Book(R.drawable.tormenta_de_espadas, "Tormenta de Espadas"))
        books.add(Book(R.drawable.la_sangre_de_los_elfos, "La Sangre de los Elfos"))

    }

    fun configureList() {
        val adapter = AdapterCustomBooks(this.context, books)
        list = activity!!.findViewById(R.id.books_list)
        list?.setHasFixedSize(true)
        list?.layoutManager = GridLayoutManager(this.context,2)
        list?.adapter = adapter
        //adapter.notifyDataSetChanged()
    }
}

