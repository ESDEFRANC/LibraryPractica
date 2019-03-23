package com.example.librarypractica


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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

    interface OnGoogleSignInPressedListener {
        fun onGooglePressed(client: GoogleSignInClient)
    }

    interface OnButtonLoginPressedListener {
        fun onLoginPressed()
    }

    interface OnTextRegistredPressedListener {
        fun onRegistredPressed(username:String)

    }

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var loginRegister: OnButtonLoginPressedListener
    private lateinit var registerListener: OnTextRegistredPressedListener
    private lateinit var googleListener: OnGoogleSignInPressedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
            requestEmail().
            build()
        mGoogleSignInClient= GoogleSignIn.getClient(context!!, gso)
        Login.setOnClickListener {
            loginRegister.onLoginPressed()
        }

        sign_in_google_button.setOnClickListener {
            googleListener.onGooglePressed(mGoogleSignInClient)
        }

        backToRegister.setOnClickListener {
            registerListener.onRegistredPressed(Email.text.toString())
        }

    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(context!!)!= null) {
            sign_in_google_button.visibility = View.INVISIBLE
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loginRegister = activity as OnButtonLoginPressedListener
        registerListener = activity as OnTextRegistredPressedListener
        googleListener = activity as OnGoogleSignInPressedListener
    }

}
