package ru.mobiledimension.nasaapp.data.repositories

import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.data.db.room.ApodDao
import ru.mobiledimension.nasaapp.data.pref.NasaPref
import ru.mobiledimension.nasaapp.domain.boundaries.StorageBoundary
import ru.mobiledimension.nasaapp.domain.dto.APOD
import java.text.SimpleDateFormat
import java.util.*

class StorageRepository(val kodein: Kodein): StorageBoundary {
    private val apodDao: ApodDao by kodein.instance()
    private val apodMapper: ApodMapper by kodein.instance()
    private val nasaPref: NasaPref by kodein.instance()
    private val dateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    }

    override suspend fun getApod(date: Date): APOD? =
        apodDao.getByDate(dateFormat.format(date))?.let{ apodMapper.map(it) }


    override suspend fun saveApod(apod: APOD) =
        apodDao.insert(apodMapper.map(apod))
}