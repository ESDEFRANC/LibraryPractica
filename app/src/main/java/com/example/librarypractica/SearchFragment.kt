package com.example.librarypractica

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_search.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFragment : Fragment(){

    interface OnSearchedBookClickedListener {
        fun onSearchedBookClicked(book:Item, user: User)
    }

    private var booksRepository: BooksSearchedRepository? = null
    private var adapterCustom: AdapterCustomBooks? = null
    lateinit var listenerList: OnSearchedBookClickedListener

    var query:String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureList()
        getSearchedBooks()
    }

    private fun configureList() {
        val user = arguments!!.getParcelable<User>("user")
        searched_books_list.setHasFixedSize(true)
        if(isLargeScreen() && !isPortrait()){
            searched_books_list.layoutManager = GridLayoutManager(this.context,4)

        }else if(isLargeScreen() && isPortrait()){
            searched_books_list.layoutManager = GridLayoutManager(this.context,3)
        }else{
            searched_books_list.layoutManager = GridLayoutManager(this.context,2)
        }
        adapterCustom = AdapterCustomBooks(this.context!!, emptyList()){
            listenerList.onSearchedBookClicked(it, user)}
        searched_books_list.adapter = adapterCustom

    }

    private fun getSearchedBooks() {
        booksRepository = BooksSearchedRepository.instance
        booksRepository!!.getBooks(query!!, object : OnGetSearchedBooksCallback {
            override fun onSuccess(books: List<Item>) {
                adapterCustom!!.setBooks(books)
                adapterCustom!!.notifyDataSetChanged()
                books_progressBar.visibility = View.GONE
            }

            override fun onError() = Toast.makeText(context!!, getString(R.string.NoInternet), Toast.LENGTH_LONG).show()

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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listenerList = context as OnSearchedBookClickedListener
    }

    companion object {
        fun newInstance(query: String?, user: User): SearchFragment{
            val fragmentSearch = SearchFragment()
            val args = Bundle()

            args.putString("query", query)
            args.putParcelable("user", user)
            fragmentSearch.arguments = args

            return fragmentSearch
        }
    }

    private fun isLargeScreen(): Boolean {
        return resources.getBoolean(R.bool.large)
    }

    private fun isPortrait(): Boolean {
        return resources.getBoolean(R.bool.portrait)
    }




}
