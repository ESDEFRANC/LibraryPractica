package com.example.librarypractica


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    var listUsers:ArrayList<User> = ArrayList()
    var user: User? = null
    var isRegistered = false
    var thereIsData = false
    private lateinit var loginRegister: OnButtonLoginPressedListener
    private lateinit var registerListener: OnTextRegistredPressedListener


    interface OnButtonLoginPressedListener {
        fun onLoginPressed(user: User)
    }

    interface OnTextRegistredPressedListener {
        fun onRegistredPressed(username:String)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        doAsync {
            val result = loadData()
            uiThread {
                listUsers = result as ArrayList<User>
            }
        }
        Login.setOnClickListener {
            checkData()
            if (thereIsData) {
                checkUser()
                if (isRegistered) {
                    loginRegister.onLoginPressed(user!!)
                } else {
                    Email.error = "Wrong Email or Password"
                }
            }else {
                Toast.makeText(context, "You have to be registered first", Toast.LENGTH_LONG).show()
            }

        }

        backToRegister.setOnClickListener {
            registerListener.onRegistredPressed(Email.text.toString())
        }

    }


    private fun loadData() :List<User>{
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = preferences.getString("users", null)
        val usersType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(json, usersType)
    }

    private fun checkUser(){
        for (userIt in listUsers){
            if(userIt.username == Email.text.toString() && userIt.password == Password.text.toString()){
                    isRegistered = true
                this.user = userIt
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loginRegister = activity as OnButtonLoginPressedListener
        registerListener = activity as OnTextRegistredPressedListener
    }

    private fun checkData() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains("users")) thereIsData = true
    }

}
