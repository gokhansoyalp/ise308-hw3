package ise308.soyalp.gokhan.notetogokhansoyalp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class NewFilmDialog :DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater=activity?.layoutInflater    // assigning values
        val newFilmDialog=inflater?.inflate(R.layout.dialog_new_film, null)

        val editTextName=newFilmDialog?.findViewById<EditText>(R.id.et_name)
        val editTextGenre=newFilmDialog?.findViewById<EditText>(R.id.et_genre) // assigning variables from the xml file
        val editTextImdb= newFilmDialog?.findViewById<EditText>(R.id.et_imdb)
        val editTextLang=newFilmDialog?.findViewById<EditText>(R.id.et_lang)

        val buttonOk=newFilmDialog?.findViewById<Button>(R.id.btn_ok)
        val buttonCancel=newFilmDialog?.findViewById<Button>(R.id.btn_cancel)

        builder.setView(newFilmDialog)

        buttonCancel?.setOnClickListener {
            dismiss()  // click cancel button, leave this screen
        }

        buttonOk?.setOnClickListener{
            val newFilm =Film()

                newFilm.name=editTextName?.text.toString()
                newFilm.genre=editTextGenre?.text.toString()  // taking data's and converting string after this process saving Film class variables
                newFilm.imdb=editTextImdb?.text.toString()
                newFilm.lang=editTextLang?.text.toString()


            val callingActivity=activity as MainActivity
            callingActivity.createNewFilm(newFilm)  // calling create new film function it takes newFilm parameter that has type of Film class
            dismiss()
        }
        return builder.create()
    }
}
