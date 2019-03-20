package com.example.librarypractica


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.regex.Pattern


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterFragment : Fragment() {
    var listUers:ArrayList<User> = ArrayList()
    interface OnRegistrationConfirmPressed {
        fun onRegistrationConfirmPressed()

    }
    private lateinit var buttonRegisteredListener: OnRegistrationConfirmPressed
    private lateinit var username:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onStart() {
        super.onStart()
        updateText()
    }

    private fun updateText() {
        val usernameToRegister = arguments!!.getSerializable("username")
        nameMain.text = Editable.Factory.getInstance().newEditable(usernameToRegister.toString())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerMain.setOnClickListener(){
            if(checkPassword()){

            }
            saveLocalData(nameMain.text.toString(),passwordMain.text.toString())
        }

    }

    companion object {
        fun newInstance(username: String):RegisterFragment{
            val fragmentRegister = RegisterFragment()
            val args = Bundle()

            args.putSerializable("username", username)
            fragmentRegister.arguments = args

            return fragmentRegister
        }
    }

    private fun saveLocalData(email:String,password:String){
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        // save device token
        val user = User(email,password)
        listUers.add(user)
        val gson:Gson = Gson()
        val json = gson.toJson(listUers)

        preferences.edit().putString("Email",json).apply()
        Log.d("Emailverification",preferences.all.toString())
    }
    private fun checkPassword(password:String,passwordRepeat:String):Boolean{
        return password == passwordRepeat
    }
    private fun checkUserName(name: String): Boolean {
        val regex = "^[a-zA-Z0-9]+$"
        val p = Pattern.compile(regex)
        val nametrimed = name.trim()
        val m = p.matcher(nametrimed)
        val b = m.matches()
        return b
    }



}
