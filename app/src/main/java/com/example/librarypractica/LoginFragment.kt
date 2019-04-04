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
    private var theGoogleAccountIsInDB = false
    private var account:GoogleSignInAccount? = null
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var loginRegister: OnButtonLoginPressedListener
    private lateinit var registerListener: OnTextRegistredPressedListener
    private lateinit var googleListener: OnGoogleSignInPressedListener
    private lateinit var gso:GoogleSignInOptions

    interface OnGoogleSignInPressedListener {
        fun onGooglePressed(client: GoogleSignInClient)
    }

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
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
            requestEmail().
            build()
        mGoogleSignInClient= GoogleSignIn.getClient(context!!, gso)
        Login.setOnClickListener {
            checkData()
            if (thereIsData) {
                loadData()
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

        sign_in_google_button.setOnClickListener {
            googleListener.onGooglePressed(mGoogleSignInClient)
            checkData()
            if(!theGoogleAccountIsInDB && thereIsData) {
                saveGoogleAccountData()
            }
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            Log.d("GoogleUser", preferences.all.toString())
        }

        backToRegister.setOnClickListener {
            registerListener.onRegistredPressed(Email.text.toString())
        }

    }



    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(activity)!= null) {
            account = GoogleSignIn.getLastSignedInAccount(activity)
            checkData()
            if (thereIsData) {
                loadData()
                checkIfGoogleAccountIsinDB()
            }
        }
    }

    private fun loadData() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = preferences.getString("users", null)
        val usersType = object : TypeToken<List<User>>() {}.type
        listUsers = gson.fromJson(json, usersType)
    }

    private fun checkUser(){
        for (userIt in listUsers){
            if(userIt.username == Email.text.toString() && userIt.password == Password.text.toString()){
                    isRegistered = true
                user = User(Email.text.toString(), Password.text.toString(), null)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loginRegister = activity as OnButtonLoginPressedListener
        registerListener = activity as OnTextRegistredPressedListener
        googleListener = activity as OnGoogleSignInPressedListener
    }

    private fun checkData() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains("users")) thereIsData = true
    }

    private fun saveGoogleAccountData() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        user = User(account!!.email!!, account!!.id!!, null)
        listUsers.add(user!!)
        val gson = Gson()
        val json = gson.toJson(listUsers)

        preferences.edit().putString("users",json).apply()
    }

    private fun checkIfGoogleAccountIsinDB() {
        for (user in listUsers) {
            if(user.username == account!!.email && user.password == account!!.id) {
                theGoogleAccountIsInDB = true
            }
        }
    }

}
