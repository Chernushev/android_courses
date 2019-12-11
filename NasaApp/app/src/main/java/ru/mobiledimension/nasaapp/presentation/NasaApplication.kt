package ru.mobiledimension.nasaapp.presentation

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.facebook.stetho.Stetho
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.mobiledimension.nasaapp.data.db.room.MIGRATION_1_2
import ru.mobiledimension.nasaapp.data.db.room.NasaDatabase

class NasaApplication: Application(), KodeinAware {

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

    override val kodein: Kodein
        get() = Kodein {
            bind<Context>("text") with singleton {
                this@NasaApplication.applicationContext
            }
            bind<Context>() with provider {
                this@NasaApplication.applicationContext
            }

            bind<Context>() with factory { arg: Int ->
                this@NasaApplication.applicationContext
            }
            import(presentationModule)
        }

    val activityModule = Kodein {
        extend(kodein = kodein)
    }
}

val presentationModule = Kodein.Module("presentation") {

}
