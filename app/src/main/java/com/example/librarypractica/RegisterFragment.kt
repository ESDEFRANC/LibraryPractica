package com.example.librarypractica


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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

    var listUsers:ArrayList<User> = ArrayList()
    var thereIsData = false
    var user: User? = null

    interface OnRegistrationConfirmPressed {
        fun onRegistrationConfirmPressed(user:User)
    }

    private lateinit var buttonRegisteredListener: OnRegistrationConfirmPressed
    private var fieldsOk = false

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
        doAsync {
            val result = loadData()
            uiThread {
                listUsers = result as ArrayList<User>
            }
        }
        checkData()
            registerMain.setOnClickListener {
                fieldsOk = true
                if(userNameIsAlreadyUsed()){
                    nameMain.error = "Username not available."
                    fieldsOk = false
                }
                checkFields()
                if (fieldsOk) {

                    saveLocalData(nameMain.text.toString(), passwordMain.text.toString())
                    buttonRegisteredListener.onRegistrationConfirmPressed(user!!)
                }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun userNameIsAlreadyUsed() : Boolean {
        for (usersIT in listUsers) {
            if(usersIT.username == nameMain.text.toString()) {
                return true
            }
        }
        return false
    }

    private fun checkData() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains("users")) thereIsData = true
    }

    private fun saveLocalData(username:String,password:String){
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        user = User(username,password, ArrayList())
        listUsers.add(user!!)
        val gson = Gson()
        val json = gson.toJson(listUsers)

        preferences.edit().putString("users",json).apply()

    }

    private fun loadData(): List<User>{
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = preferences.getString("users", null)
        val usersType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(json, usersType)
    }

    private fun checkFields() {
        checkUserName()
        checkPassword()
        checkRepeatPassword()
    }


    private fun checkRepeatPassword() {
        if (passwordMain2.text.toString() != passwordMain.text.toString()) {
            passwordMain.error = "Passwords desn't match"
            fieldsOk = false
        }
    }

    private fun checkPassword(){
        val password = passwordMain.text.toString()
        if (password.isEmpty() || password.length < 8) {
            passwordMain.error = "The password cannot be empty or have a length of less then 8 characters"
            fieldsOk = false
        }
    }

    private fun checkUserName() {
        val username = nameMain.text.toString()
        if (!Pattern.compile("^[a-zA-Z0-9]+$").matcher(username).matches()) {
            nameMain.error = "Username not valid"
            fieldsOk = false
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        buttonRegisteredListener = activity as OnRegistrationConfirmPressed
    }




}
