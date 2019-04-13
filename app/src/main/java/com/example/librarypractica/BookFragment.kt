package com.example.librarypractica

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.template_recycler.view.*
import java.util.*


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

        fun newInstance(book: Item): BookFragment{
            val fragmentDetails = BookFragment()
            val args = Bundle()

            args.putParcelable("book", book)
            fragmentDetails.arguments = args

            return fragmentDetails
        }
    }
}
