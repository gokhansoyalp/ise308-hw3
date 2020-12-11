package ise308.soyalp.gokhan.notetogokhansoyalp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class ShowFilmDialog: DialogFragment() {
    private lateinit var film: Film
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(activity)
        val inflater=activity?.layoutInflater       // assigning values
        val dialogLayout=inflater?.inflate(R.layout.dialog_show_film, null)

        val textViewShowName=dialogLayout?.findViewById<TextView>(R.id.tv_show_name)
        val textViewShowGenre=dialogLayout?.findViewById<TextView>(R.id.tv_show_genre)
        val textViewShowImdb=dialogLayout?.findViewById<TextView>(R.id.tv_show_imdb)   // assigning variables from the xml file
        val textViewShowLang=dialogLayout?.findViewById<TextView>(R.id.tv_show_lang)
        val buttonShowDone=dialogLayout?.findViewById<Button>(R.id.btn_done)

        textViewShowName?.text=film.name
        textViewShowGenre?.text=film.genre
        textViewShowImdb?.text=film.imdb        // assigning taken data's from xml file to Film class variables
        textViewShowLang?.text=film.lang

        buttonShowDone?.setOnClickListener{
            dismiss()
        }                                   // it send dialog layout , showing for saved data's
        builder.setView(dialogLayout)
        return builder.create()
    }

    fun setFilm(film: Film){
        this.film=film

    }
}
