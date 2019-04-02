package com.example.librarypractica

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import kotlinx.android.synthetic.main.fragment_favorite_books_list.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteBooksList : Fragment() {

    interface OnBookClickedListener {
        fun onBookClicked(book:Book)
    }

    var books:ArrayList<Book> = ArrayList()
    var list: RecyclerView? = null
    lateinit var listenerList:OnBookClickedListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favorite_books_list, container, false)
        setHasOptionsMenu(true)
        return view
    }


    override fun onStart() {
        super.onStart()

        showDetails()
    }

    private fun showDetails() {
        val item = arguments!!.getParcelable<User>("user")
        textView.text = "Welcome ${item.username}"

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater:MenuInflater){
        inflater.inflate(R.menu.nav_menu, menu)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureList()
        //setListener()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //addProductstoList()
        listenerList = context as OnBookClickedListener

    }

    /*private fun addProductstoList() {
        books.add(Book("La llamada de Cthulu", R.drawable.la_llamada_de_cthulu, "H.P Lovecraft", "Gigamesh", "La llamada de Cthulhu. En el invierno de 1926 fallece el tío abuelo del narrador de este cuento de culto, profesor de lenguas semíticas en la universidad de Brown y autoridad en el campo de las inscripciones antiguas. La medicina no puede aclarar las circunstancias de esta muerte envuelta por el misterio" ))
        books.add(Book("Tormenta de Espadas", R.drawable.tormenta_de_espadas, "George R.R Martin", "Gigamesh", "Tormenta de espadas retoma la historia dónde acaba su predecesora Choque de reyes. Los Siete Reinos están inmersos en la llamada Guerra de los Cinco Reyes, con Robb Stark, Renly Baratheon, Joffrey Baratheon, y Stannis Baratheon luchando por afianzar sus coronas. El intento de Stannis Baratheon de tomar la ciudad de Desembarco del Rey fracasa debido a la nueva alianza entre la casa Lannister, la casa Tyrell y la casa Martell, aunque los ejércitos de esta última no toman partido en la lucha. Mientras, en el Muro, un gran ejército de salvajes liderados por Mance Rayder avanza hacia el sur, con las reducidas fuerzas de la Guardia de la Noche como única resistencia. En el lejano este, Daenerys Targaryen va a la Bahía de los Esclavos con la esperanza de encontrar y movilizar fuerzas suficientes para retomar el Trono de Hierro." ))
        books.add(Book("La Sangre de los Elfos", R.drawable.la_sangre_de_los_elfos, "Andrzej Sapkowski", "Independent Publishing House NOWA", "La sangre de los elfos (en polaco:Krew elfów) es la primera novela en La saga del brujo escrita por Andrzej Sapkowski. Es una secuela a los cuentos cortos recolectados en los libros El último deseo y La espada del destino y le sigue Tiempo de odio (Czas pogardy)." ))

    }*/

    fun configureList() {
        list = activity!!.findViewById(R.id.books_list)
        list?.setHasFixedSize(true)
        list?.layoutManager = GridLayoutManager(this.context,2)
        list?.adapter = AdapterCustomBooks(context, books) {
            listenerList.onBookClicked(it)
        }
        //adapter.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(user: User): FavoriteBooksList{
            val fragmentList = FavoriteBooksList()
            val args = Bundle()

            args.putParcelable("user", user)
            fragmentList.arguments = args

            return fragmentList
        }
    }
}

