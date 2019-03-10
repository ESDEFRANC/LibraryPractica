package com.example.librarypractica


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterFragment : Fragment() {
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

    companion object {
        fun newInstance(username: String):RegisterFragment{
            val fragmentRegister = RegisterFragment()
            val args = Bundle()

            args.putSerializable("username", username)
            fragmentRegister.arguments = args

            return fragmentRegister
        }
    }


}
