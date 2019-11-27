package ru.mobiledimension.nasaapp

import android.app.Application
import androidx.room.Room
import com.facebook.stetho.Stetho

class NasaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        database = Room.databaseBuilder(
                this.applicationContext,
                NasaDatabase::class.java,
                NasaDatabase::class.java.simpleName
            )
            .addMigrations(MIGRATION_1_2)
            .allowMainThreadQueries() // необходимо, чтобы разрешить ходить в базу на мейне, нехорошо, но нам пока это не критично
            .build()
    }

    companion object {
        private lateinit var database: NasaDatabase

        fun getDatabase() = database
    }
}
