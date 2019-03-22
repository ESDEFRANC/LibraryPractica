package com.example.librarypractica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.id.edit
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.support.v4.app.Fragment
import android.util.Log
import kotlinx.android.synthetic.main.fragment_register.*


class  MainActivity : AppCompatActivity(), LoginFragment.OnTextRegistredPressedListener, LoginFragment.OnButtonLoginPressedListener, FavoriteBooksList.OnProductClickedListener{


    override fun onLoginPressed() {
        val fragmentListBooks = FavoriteBooksList()
        supportFragmentManager.beginTransaction().
            replace(R.id.main_container, fragmentListBooks).
            commit()
    }


    override fun onRegistredPressed(username: String) {
        val fragmentRegister = RegisterFragment.newInstance(username)
        supportFragmentManager.beginTransaction().
            replace(R.id.main_container, fragmentRegister).
            addToBackStack(null).
            commit()

    }
     fun onRegistrationConfirmPressed() {


    }


    override fun onProductClicked(product: Book) {
        val fragmentBook = BookFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragmentBook).addToBackStack(null).commit()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentLogin = LoginFragment()
        supportFragmentManager.beginTransaction().
            replace(R.id.main_container,fragmentLogin).
            commit()
    }


}
