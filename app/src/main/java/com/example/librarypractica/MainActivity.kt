package com.example.librarypractica

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class  MainActivity : AppCompatActivity(), LoginFragment.OnTextRegistredPressedListener, LoginFragment.OnButtonLoginPressedListener, FavoriteBooksList.OnBookClickedListener, RegisterFragment.OnRegistrationConfirmPressed, LoginFragment.OnGoogleSignInPressedListener{

    override fun onGooglePressed(client: GoogleSignInClient, user: User) {
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
