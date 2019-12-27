package ru.mobiledimension.nasaapp.presentation

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import ru.mobiledimension.nasaapp.presentation.di.dataModule
import ru.mobiledimension.nasaapp.presentation.di.domainModule
import ru.mobiledimension.nasaapp.presentation.di.presentationModule

class NasaApplication: Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

    override val kodein: Kodein
        get() = Kodein {
            bind<Context>() with singleton {
                this@NasaApplication.applicationContext
            }
            import(dataModule)
            import(domainModule)
            import(presentationModule)
        }
}
