package com.example.librarypractica

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class  MainActivity : AppCompatActivity(), LoginFragment.OnTextRegistredPressedListener, LoginFragment.OnButtonLoginPressedListener, FavoriteBooksList.OnBookClickedListener, RegisterFragment.OnRegistrationConfirmPressed, LoginFragment.OnGoogleSignInPressedListener{

    override fun onGooglePressed(client: GoogleSignInClient) {
        val signInIntent = client.signInIntent
        startActivityForResult(signInIntent, 1234)
    }

    override fun onRegistrationConfirmPressed() {
            tableMode()
    }

    override fun onLoginPressed() {
            tableMode()
    }


    override fun onRegistredPressed(username: String) {
        val fragmentRegister = RegisterFragment.newInstance(username)
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentRegister).
            commit()

    }


    override fun onBookClicked(book: Book) {
        /*val fragmentBook = BookFragment.newInstance(book)
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentBook).
            addToBackStack(null).
            commit()*/

        if (findViewById<View>(R.id.detail_book) != null) {
            val bookFragment = BookFragment.newInstance(book)
            supportFragmentManager.beginTransaction().
                replace(R.id.main_container, bookFragment).
                commit()
        }
        /*if (isLargeScreen()) {
            val textViewFragment = supportFragmentManager.findFragmentByTag("textViewFragment") as BookFragment?
        } else {
            val f = BookFragment.newInstance(book)
            supportFragmentManager.beginTransaction().replace(R.id.main_container, f).addToBackStack(null).commit()
        }*/
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentLogin = LoginFragment()
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container,fragmentLogin).
            commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234) {
                tableMode()
        }
        
    }
    private fun isLargeScreen(): Boolean {
        return resources.getBoolean(R.bool.large)
    }
    private fun tableMode(){
        if(isLargeScreen()) {
            val listFragment = FavoriteBooksList()
            val bookFragment = BookFragment()
            supportFragmentManager.beginTransaction().
                add(R.id.favorite_list_books, listFragment).
                add(R.id.detail_book, bookFragment, "textViewFragment").
                commit()
        } else {
            val fragmentbooks = FavoriteBooksList()
            supportFragmentManager.
                beginTransaction().
                replace(R.id.main_container, fragmentbooks).
                commit()
        }/*
        val fragmentComponent = FavoriteBooksList()
        if(findViewById<View>(R.id.main_container) != null) {
            supportFragmentManager.
                beginTransaction().
                replace(R.id.main_container, fragmentComponent).
                commit()
        }else {
            supportFragmentManager.
                beginTransaction().
                replace(R.id.favorite_list_books, fragmentComponent).
                commit()
        }*/
    }


}
