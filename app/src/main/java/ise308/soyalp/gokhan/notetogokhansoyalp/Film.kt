package ise308.soyalp.gokhan.notetogokhansoyalp

import org.json.JSONException
import org.json.JSONObject


private val JSON_NAME="name"
private val JSON_GENRE="genre"          // variables for JSON File
private val JSON_IMDB="imdb"
private val JSON_LANG="lang"
//(var name:String, var genre:String, var imdb: String, var lang:String)

class Film{
    var name: String?=null
    var genre: String?=null         // class variables
    var imdb: String?=null
    var lang: String?=null



@Throws(JSONException::class)
    constructor(jsonObject: JSONObject){
        name=jsonObject.getString(JSON_NAME)
        genre=jsonObject.getString(JSON_GENRE)      //here is the secondary constructor, it takes json object, and assign to json info's
        imdb=jsonObject.getString(JSON_IMDB)        // in local variables.
        lang=jsonObject.getString(JSON_LANG)

    }
    constructor()           // here is primary constructor

    @Throws(JSONException::class)
    fun  convertToJSON(): JSONObject{
        val jsonObject=JSONObject()
        jsonObject.put(JSON_NAME,name)
        jsonObject.put(JSON_GENRE,genre)    // this function used to convert data to json file
        jsonObject.put(JSON_IMDB,imdb)
        jsonObject.put(JSON_LANG,lang)
        return jsonObject
    }
}