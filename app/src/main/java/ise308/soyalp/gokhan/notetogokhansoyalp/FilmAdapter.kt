package ise308.soyalp.gokhan.notetogokhansoyalp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(private val filmList: ArrayList<Film>, private val mainActivity: MainActivity ) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    inner class FilmViewHolder(filmItem: View) :RecyclerView.ViewHolder(filmItem), View.OnClickListener  {
        var tvName=filmItem.findViewById<TextView>(R.id.tv_name)
        var tvGenre=filmItem.findViewById<TextView>(R.id.tv_genre)   // assigning text views from the xml file
        var tvImdb=filmItem.findViewById<TextView>(R.id.tv_imdb)
        var tvLang=filmItem.findViewById<TextView>(R.id.tv_lang)

        init {
            filmItem.isClickable
            filmItem.setOnClickListener(this)       // making the array elements clickable.
        }
        override fun onClick(v: View?) {
            mainActivity.showNote(adapterPosition)          //taking adapter position and showing note which is matching position
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val filmItemInflater=LayoutInflater.from(parent.context)
        val filmView=filmItemInflater.inflate(R.layout.film_item,parent,false)
        return  FilmViewHolder(filmView)      // this function create view holder. Data is loaded and screen printed.
    }

    override fun getItemCount()=filmList.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val filmToDisplay=filmList[position]
        holder.tvName.text=filmToDisplay.name
        holder.tvGenre.text=filmToDisplay.genre             // assigning array list data to matching text views for showing data's
        holder.tvImdb.text=filmToDisplay.imdb
        holder.tvLang.text=filmToDisplay.lang
    }
}