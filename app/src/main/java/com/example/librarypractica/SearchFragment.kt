package com.example.librarypractica


import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.Loader
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_search.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment : Fragment(), LoaderManager.LoaderCallbacks<List<Book>>{

    val BASE_URL = "https://www.googleapis.com/books/v1/volumes"
    var query:String? = null
    var listSearchedBooks:ArrayList<Book>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        books_progressBar.isIndeterminate = true
        books_progressBar.visibility = View.VISIBLE

        checkInternetConnection()

        if (savedInstanceState == null || !savedInstanceState.containsKey("booksList")) {
            listSearchedBooks = ArrayList()
        }else {
            listSearchedBooks!!.addAll(savedInstanceState.getParcelableArrayList("bookList"))
            //adapter.notifyDataSetChanged()
        }
    }

    private fun checkInternetConnection() {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo == null) {
            Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<List<Book>> {
        val baseUri = Uri.parse(BASE_URL)
        val uriBuilder = baseUri.buildUpon()

        uriBuilder.appendQueryParameter("q", query)

        return BooksLoader(context!!, uriBuilder.toString())
    }

    override fun onLoadFinished(loader: Loader<List<Book>>, list: List<Book>?) {
        books_progressBar.visibility = View.GONE
        if(list != null && list.isEmpty()) {
            prepareBooks(list as ArrayList<Book>)
        }else {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_LONG).show()
        }
    }

    private fun prepareBooks(listSearchedBooks: ArrayList<Book>) {
        listSearchedBooks.addAll(listSearchedBooks)
        //adapter.notifyDataSetChanged()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("bookList", listSearchedBooks)
        super.onSaveInstanceState(outState)
    }

    override fun onLoaderReset(loader: Loader<List<Book>>) {
        listSearchedBooks!!.clear()
        //adapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
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
