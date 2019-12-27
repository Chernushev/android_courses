package ru.mobiledimension.nasaapp.presentation.di

import androidx.room.Room
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import ru.mobiledimension.nasaapp.data.db.room.ApodDao
import ru.mobiledimension.nasaapp.data.db.room.MIGRATION_1_2
import ru.mobiledimension.nasaapp.data.db.room.NasaDatabase
import ru.mobiledimension.nasaapp.data.db.sqlite.DbHelper
import ru.mobiledimension.nasaapp.data.network.NasaApi
import ru.mobiledimension.nasaapp.data.network.NetworkService
import ru.mobiledimension.nasaapp.data.pref.NasaPref
import ru.mobiledimension.nasaapp.data.repositories.ApodMapper
import ru.mobiledimension.nasaapp.data.repositories.NetworkRepository
import ru.mobiledimension.nasaapp.data.repositories.StorageRepository
import ru.mobiledimension.nasaapp.domain.boundaries.NetworkBoundary
import ru.mobiledimension.nasaapp.domain.boundaries.StorageBoundary
import ru.mobiledimension.nasaapp.domain.interactors.ApodInteractor
import ru.mobiledimension.nasaapp.presentation.apod.ApodPresenter
import ru.mobiledimension.nasaapp.presentation.apod.ApodView
import ru.mobiledimension.nasaapp.presentation.gallery.GalleryPresenter
import ru.mobiledimension.nasaapp.presentation.gallery.GalleryView

val roomModule = Kodein.Module("room") {
    bind<NasaDatabase>() with singleton {
        Room.databaseBuilder(
            instance(),
            NasaDatabase::class.java,
            NasaDatabase::class.java.simpleName
        )
            .addMigrations(MIGRATION_1_2)
            .allowMainThreadQueries()
            .build()
    }

    bind<ApodDao>() with provider { instance<NasaDatabase>().apodDao() }
}

val dataModule = Kodein.Module("data") {
    import(roomModule)
    bind<DbHelper>() with singleton { DbHelper(instance()) }
    bind<NasaApi>() with singleton { instance<NetworkService>().getNasaApi() }
    bind<NetworkService>() with singleton { NetworkService() }
    bind<NasaPref>() with singleton { NasaPref(instance()) }
}

val domainModule = Kodein.Module("domain") {
    bind<ApodInteractor>() with singleton { ApodInteractor(kodein) }
    bind<StorageBoundary>() with singleton { StorageRepository(kodein) }
    bind<NetworkBoundary>() with singleton { NetworkRepository(kodein) }
    bind<ApodMapper>() with singleton { ApodMapper() }
}

val presentationModule = Kodein.Module("presentation") {
    bind<ApodPresenter>() with factory { view: ApodView -> ApodPresenter(kodein, view) }
    bind<GalleryPresenter>() with factory { view: GalleryView -> GalleryPresenter(kodein, view) }
}