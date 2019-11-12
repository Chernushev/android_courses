package ru.mobiledimension.nasaapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson

class NasaPref(context: Context) {
    private val pref = context.getSharedPreferences(namePref, MODE_PRIVATE)
    private val gson = Gson()

    fun saveAPOD(apod: APOD) {
        pref.edit()
            .putString(apod.date, gson.toJson(apod))
            .apply()
    }

    fun getAPOD(date: String): APOD {
        return gson.fromJson(pref.getString(date, String()), APOD::class.java)
    }

    fun saveAPODs(list: List<APOD>) {
        val editor = pref.edit()
        list.forEach {
            editor.putString(it.date, gson.toJson(it))
        }
        editor.apply()
    }

    fun clear() {
        pref.edit()
            .clear()
            .apply()
    }

    companion object {
        const val namePref = "NasaPref"
    }
}