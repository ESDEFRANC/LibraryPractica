package com.example.librarypractica

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_book.*
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BookFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BookFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BookFragment : Fragment() {

    var listUsers:ArrayList<User> = ArrayList()
    var user:User? = null
    var item:Item? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onStart() {
        super.onStart()
        showDetails()
        configureToogleButton()
    }

    private fun configureToogleButton() {
        user = arguments!!.getParcelable("user")
        item = arguments!!.getParcelable("book")
        swapItem()
        val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator
        if (item!!.isFav) {
            button_favorite.isChecked = true
        }
        button_favorite.setOnCheckedChangeListener { compoundButton, isChecked ->
            compoundButton?.startAnimation(scaleAnimation)
            if (isChecked){
                user!!.favoriteBooks.add(item!!)
                loadData()
                saveLocalData()
                Log.d("booksAdded", listUsers.toString())
                item!!.isFav = true
            }else {
                user!!.favoriteBooks.remove(item!!)
            }
        }
    }

    private fun swapItem() {
        for (itemIT in user!!.favoriteBooks) {
            if(itemIT.id == item!!.id) {
                item = itemIT
            }
        }
    }


    private fun saveLocalData(){
        var index = -1
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        for (userIT in listUsers) {
            if(userIT.username == user!!.username) {
                index = listUsers.indexOf(userIT)
            }
        }
        listUsers[index] = user!!

        val gson = Gson()
        val json = gson.toJson(listUsers)

        preferences.edit().putString("users",json).apply()

    }

    private fun loadData() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = preferences.getString("users", null)
        val usersType = object : TypeToken<List<User>>() {}.type
        listUsers = gson.fromJson(json, usersType)
        Log.d("UsernameVerification", preferences.all.toString())
    }

    private fun showDetails() {
       val item = arguments!!.getParcelable<Item>("book")

        Picasso.get().load(item.volumeInfo?.imageLinks?.thumbnail).placeholder(R.drawable.ic_launcher_foreground).into(cover)
        Title.text = item.volumeInfo?.title
        Author.text = Arrays.toString(item.volumeInfo?.authors)
        Editorial.text = item.volumeInfo?.publisher
        synopsis.text = item.volumeInfo?.description
    }

    companion object {

        fun newInstance(book: Item, user:User): BookFragment{
            val fragmentDetails = BookFragment()
            val args = Bundle()

            args.putParcelable("book", book)
            args.putParcelable("user", user)
            fragmentDetails.arguments = args

            return fragmentDetails
        }
    }
}
