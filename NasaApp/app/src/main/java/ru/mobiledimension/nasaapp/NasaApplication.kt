package ru.mobiledimension.nasaapp

import android.app.Application
import com.facebook.stetho.Stetho

class NasaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
