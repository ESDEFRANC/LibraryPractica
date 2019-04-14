package com.example.librarypractica

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.fragment_favorite_books_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteBooksList : Fragment() {

    interface OnBookClickedListener {
        fun onBookClicked(book:Item, user:User)
    }

    lateinit var listenerBookList:OnBookClickedListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favorite_books_list, container, false)
        setHasOptionsMenu(true)
        return view
    }


    override fun onStart() {
        super.onStart()

        showDetails()
    }

    private fun showDetails() {
        val item = arguments!!.getParcelable<User>("user")
        textView.text = "Welcome ${item.username}"

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater:MenuInflater){
        inflater.inflate(R.menu.nav_menu, menu)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureList()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listenerBookList = context as OnBookClickedListener

    }


    fun configureList() {
        val user = arguments!!.getParcelable<User>("user")
        books_list.setHasFixedSize(true)
        if(isLargeScreen() && !isPortrait()){
            books_list.layoutManager = GridLayoutManager(this.context,4)

        }else if(isLargeScreen() && isPortrait()){
            books_list.layoutManager = GridLayoutManager(this.context,3)
        }else{
            books_list.layoutManager = GridLayoutManager(this.context,2)
        }
        val adapterCustom = AdapterCustomBooks(this.context!!, user.favoriteBooks){
            listenerBookList.onBookClicked(it, user)}
        books_list.adapter = adapterCustom

    }

    private fun isLargeScreen(): Boolean {
        return resources.getBoolean(R.bool.large)

    }

    private fun isPortrait(): Boolean {
        return resources.getBoolean(R.bool.portrait)
    }

    companion object {

        fun newInstance(user: User): FavoriteBooksList{
            val fragmentList = FavoriteBooksList()
            val args = Bundle()

            args.putParcelable("user", user)
            fragmentList.arguments = args

            return fragmentList
        }
    }
}

