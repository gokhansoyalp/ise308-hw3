package ise308.soyalp.gokhan.notetogokhansoyalp

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch

class SettingsActivity : AppCompatActivity() {

    private var showDividers: Boolean = true

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs=getSharedPreferences("Film Note", Context.MODE_PRIVATE)
        showDividers =prefs.getBoolean("dividers",true)
        val switch1= findViewById<Switch>(R.id.switch1) // if its unchecked during working program, it can save last state of program
        switch1.isChecked=showDividers                          // before closed program.

        switch1.setOnCheckedChangeListener { button, isChecked ->
            showDividers=isChecked
        }


    }

    override fun onPause() {
        super.onPause()
        val prefs=getSharedPreferences("Film Note", Context.MODE_PRIVATE)
        val editor = prefs.edit()   //
        editor.putBoolean("dividers", showDividers)
        editor.apply()
    }
}