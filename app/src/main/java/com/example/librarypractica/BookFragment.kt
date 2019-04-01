package com.example.librarypractica

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_book.*


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
        val item = arguments!!.getParcelable<Book>("book")

        //cover.setImageResource(item.img)
        Title.text = item.title
        Author.text = item.authors.toString()
        Editorial.text = item.publisher
        synopsis.text = item.description
    }

    companion object {

        fun newInstance(book: Book): BookFragment{
            val fragmentDetails = BookFragment()
            val args = Bundle()

            args.putParcelable("book", book)
            fragmentDetails.arguments = args

            return fragmentDetails
        }
    }
}
