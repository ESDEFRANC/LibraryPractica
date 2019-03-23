package com.example.librarypractica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class  MainActivity : AppCompatActivity(), LoginFragment.OnTextRegistredPressedListener, LoginFragment.OnButtonLoginPressedListener, FavoriteBooksList.OnBookClickedListener, RegisterFragment.OnRegistrationConfirmPressed{

    override fun onRegistrationConfirmPressed() {
        val fragmentBooks = FavoriteBooksList()
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentBooks).
            commit()
    }


    override fun onLoginPressed() {
        val fragmentListBooks = FavoriteBooksList()
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



    override fun onBookClicked(book: Book) {
        val fragmentBook = BookFragment.newInstance(book)
        supportFragmentManager.
            beginTransaction().
            replace(R.id.main_container, fragmentBook).
            addToBackStack(null).
            commit()
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


}
