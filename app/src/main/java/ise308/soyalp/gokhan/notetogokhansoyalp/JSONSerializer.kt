package ise308.soyalp.gokhan.notetogokhansoyalp

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.lang.StringBuilder
import java.security.AccessControlContext

class JSONSerializer(private val filename: String, private val context: Context) {
    @Throws(IOException::class, JSONException::class)
    fun save(filmList: List<Film>) {
        val jsonArray = JSONArray()
        for (film in filmList) {
            jsonArray.put(film.convertToJSON())
        }
        var writer: Writer? = null       // it takes an array to saving json file. Some settings about output type
        try {                              // calling some functions from the main activity
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())
        } finally {
            if (writer != null) {
                writer.close()
            }
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Film> {
        val filmList = ArrayList<Film>()
        var reader: BufferedReader? = null

        try {
            val inFile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(inFile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }
            val jsonArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray

            for (i in 0 until jsonArray.length()) {
                filmList.add(Film(jsonArray.getJSONObject(i)))
            }
        } catch (e: FileNotFoundException) {
            //ignore
        } finally {
            reader!!.close()
        }
        return filmList
    }


}