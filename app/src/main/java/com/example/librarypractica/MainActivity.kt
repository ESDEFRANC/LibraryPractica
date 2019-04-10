package com.example.librarypractica

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.MenuItem
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class  MainActivity : AppCompatActivity(), LoginFragment.OnTextRegistredPressedListener, LoginFragment.OnButtonLoginPressedListener, /*FavoriteBooksList.OnBookClickedListener*/RegisterFragment.OnRegistrationConfirmPressed, LoginFragment.OnGoogleSignInPressedListener{


    var booksSearched: ArrayList<Book>? = null

    val BASE_URL = "https://www.googleapis.com/books/v1/volumes?q="

    override fun onGooglePressed(client: GoogleSignInClient) {
        val signInIntent = client.signInIntent
        startActivityForResult(signInIntent, 1234)
    }

    override fun onRegistrationConfirmPressed(user:User) {
        val fragmentBooks = FavoriteBooksList.newInstance(user)
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentBooks).
            commit()
    }

    override fun onLoginPressed(user:User) {
        val fragmentListBooks = FavoriteBooksList.newInstance(user)
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentListBooks).
            commit()
    }

    override fun onRegistredPressed(username: String) {
        val fragmentRegister = RegisterFragment.newInstance(username)
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentRegister).
            addToBackStack(null).
            commit()


    }


    /*override fun onBookClicked(book: Item) {
        val fragmentBook = BookFragment.newInstance(book)
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentBook).
            addToBackStack(null).
            commit()
    }*/

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.search -> {
                val searchView = item.actionView as SearchView

                searchView.queryHint = "Search a book..."

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(p0: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {

                        //val booksLoader = BooksLoader(this@MainActivity, BASE_URL + query)
                        //booksSearched = booksLoader.loadInBackground() as ArrayList<Book>
                        val searchFragment = SearchFragment.newInstance(query)
                        supportFragmentManager.
                            beginTransaction().
                            replace(R.id.main_container, searchFragment).
                            addToBackStack(null).
                            commit()
                        return true
                    }

                })

            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentLogin = LoginFragment()
            supportFragmentManager.beginTransaction().add(R.id.main_container, fragmentLogin).commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234) {
            val fragmentbooks = FavoriteBooksList()
            supportFragmentManager.
                beginTransaction().
                replace(R.id.main_container, fragmentbooks).
                commit()
        }

    }
    private fun isLargeScreen(): Boolean {
        return resources.getBoolean(R.bool.large)
    }


}
