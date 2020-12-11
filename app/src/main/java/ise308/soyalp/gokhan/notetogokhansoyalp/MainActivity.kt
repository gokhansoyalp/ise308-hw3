package ise308.soyalp.gokhan.notetogokhansoyalp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    //private lateinit var filmList: ArrayList<Film>
    private lateinit var cBtn: Button
    private var filmList : ArrayList<Film>?=null
    private var jsonsSerializer : JSONSerializer?=null  // here are our variables
    private var showDividers: Boolean = false
    private var rvFilms: RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        setContentView(R.layout.activity_main)

        val actionBar=supportActionBar
        actionBar!!.title=resources.getString(R.string.app_name)

        cBtn=findViewById(R.id.cBtn)
        cBtn.setOnClickListener {  // this onClickListener overriding for "change languages" button
            showChangeLang()
        }

        initializeFilms()

        jsonsSerializer= JSONSerializer("NoteToGokhanSoyalp", applicationContext)
        try {
            filmList=jsonsSerializer!!.load()
        }catch (e: Exception){                              // it load film information from JSON file and write in filmList Array
            filmList= ArrayList()
            Log.e("asd","Error loading films..")
        }

        rvFilms=findViewById<View>(R.id.rv_films) as RecyclerView  // create and definition RecyclerView
        val fabNewFilm=findViewById<FloatingActionButton>(R.id.fab_new_film)   //definition and decleration of floating action button
        val filmAdapter=FilmAdapter(filmList!!, this)  // assignment from filmList to Adapter
        val layoutManager=LinearLayoutManager(this)
        rvFilms!!.layoutManager=layoutManager
        rvFilms!!.itemAnimator= DefaultItemAnimator()   //configurations for RecyclerView
        rvFilms!!.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        rvFilms!!.adapter=filmAdapter


        fabNewFilm.setOnClickListener {
            val newFilmDialog= NewFilmDialog()
            newFilmDialog.show(supportFragmentManager, "123")           // overriding floating action button its create,
                                                                            // new dialog
        }


    }

    private fun showChangeLang() {
        val listItems= arrayOf("English","Turkish","Spanish")
        val cBuilder=AlertDialog.Builder(this)
        cBuilder.setTitle(R.string.choose_lang)
        cBuilder.setSingleChoiceItems(listItems,-1){dialog, which ->
            if (which==0){
                setLocate("en")
                recreate()
            }
            else if (which==1){                     // this function using for change language, there is an arraylist it include,
                setLocate("tr")                     // Languages. It using alert dialog for showing and choosing languages
                                                    // setLocate function takes a string values that using finding string.xml
                recreate()
            }
            else if (which==2){
                setLocate("es")
                recreate()
            }
            dialog.dismiss()
        }
        val cDialog=cBuilder.create()
        cDialog.show()
    }

    private fun setLocate(Lang: String){
        val locale=Locale(Lang)
        Locale.setDefault(locale)              // setLocate block takes "Lang" string type. Lang is the shortened version of languages
        val config=Configuration()              // for ex, "Turkish" -> "tr"
        config.locale=locale
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val editor=getSharedPreferences("Settings",Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()
    }

    private fun loadLocate(){
        val sharedPreferences=getSharedPreferences("Settings",Activity.MODE_PRIVATE)
        val language=sharedPreferences.getString("My_Lang","")
        if (language != null) {             // after setting locate language, it load and set sharedPreferences
            setLocate(language)
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("Film Note", Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean("dividers", true)
        if (showDividers) {                         // this function using get shared preferences
            rvFilms!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        } else {
            if (rvFilms!!.itemDecorationCount > 0)
                rvFilms!!.removeItemDecorationAt(0)
        }

    }

    private fun initializeFilms() {    // here this function using for adding notes manually
        filmList=ArrayList<Film>()
        //filmList!!.add(Film("The Godfather", "Crime/Drama","9,2","English"))
        //filmList!!.add(Film("The Dark Knight",   "Action/Crime/Drama", "9,0",   "English"))
        //filmList!!.add(Film("12 Angry Men",  "Crime/Drama", "9,0", "English"))
        //filmList!!.add(Film("Interstellar",  "Adventure/Sci-Fi", "8,6",  "English"))
        //filmList!!.add(Film("Avengers: Endgame",  "Adventure", "8,4",  "English"))
        //filmList!!.add(Film( "DAĞ 2",  "Action/War", "8,8",  "Turkish"))
        //filmList!!.add(Film( "Ayla",  "Biography/Drama/History", "8,4",  "Turkish"))
        //filmList!!.add(Film( "Babam ve Oğlum",  "Family","8,3",  "Turkish"))
        //filmList!!.add(Film("Pardon", "Comedy", "8,2",  "Turkish"))
        //filmList!!.add(Film("G.O.R.A",  "Adventure/Comedy/Sci-Fi", "8,0",  "Turkish"))
        //filmList!!.add(Film("The Godfather", "Crime/Drama", "9,2", "English"))
        //filmList!!.add(Film(  "Schindler's List",   "Biography/History", "8,9",   "German"))


    }



    fun createNewFilm(newFilm: Film ){
        filmList!!.add(newFilm)         // it add coming parameter to film List array
    }

    fun showNote(adapterPosition: Int) {
     val showFilmDialog=ShowFilmDialog()
        showFilmDialog.setFilm(filmList!![adapterPosition])         // this function using for showing notes in the list
        showFilmDialog.show(supportFragmentManager,"124")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)        // this function override on create option menu, declaration for created type of menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        val b = when (id){          // overriding on option item selected function. Its taking item ids and creating intent type of
            R.id.settings -> {               // SettingActivity class.
                val intentToSettings =Intent(this, SettingsActivity::class.java)
                startActivity(intentToSettings)
                true        // starting SettingActivity class
            }
        else -> super.onOptionsItemSelected(item)
        }
        return b
    }

    private fun saveNotes(){
        try {
            jsonsSerializer!!.save(this.filmList!!)
        }catch (e:Exception){
            Log.e("asf","Error loading films..")  // This function saving Notes in the array list as a Json File
        }
    }

    override fun onPause() {
        super.onPause()         // overriding on pause function, its calling save notes function for saving json last states of the program
        saveNotes()
    }
}
