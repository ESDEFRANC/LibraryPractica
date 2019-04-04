package com.example.librarypractica

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment : Fragment(){

    private var booksRepository: BooksSearchedRepository? = null

    var query:String? = null
     var listSearchedBooks:List<Item> = ArrayList()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getSearchedBooks()

    }

    private fun getSearchedBooks() {
        booksRepository = BooksSearchedRepository.instance
        booksRepository!!.getBooks(query!!, object : OnGetSearchedBooksCallback {
            override fun onSuccess(books: List<Item>) {
                listSearchedBooks = books
            }

            override fun onError() {
                Toast.makeText(context!!, "No Internet Connection", Toast.LENGTH_LONG).show()
            }

        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setQuery()
    }

    private fun setQuery() {
        query = arguments!!.getString("query")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        fun newInstance(query: String?): SearchFragment{
            val fragmentSearch = SearchFragment()
            val args = Bundle()

            args.putString("query", query)
            fragmentSearch.arguments = args

            return fragmentSearch
        }
    }


}
