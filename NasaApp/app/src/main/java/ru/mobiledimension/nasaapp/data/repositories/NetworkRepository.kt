package ru.mobiledimension.nasaapp.data.repositories

import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.data.network.NasaApi
import ru.mobiledimension.nasaapp.domain.boundaries.NetworkBoundary
import ru.mobiledimension.nasaapp.domain.dto.APOD
import java.text.SimpleDateFormat
import java.util.*

class NetworkRepository (val kodein: Kodein): NetworkBoundary {
    private val nasaApi: NasaApi by kodein.instance()
    private val apodMapper: ApodMapper by kodein.instance()

    override suspend fun getApod(date: Date): APOD? {
        return apodMapper.map(nasaApi.getAPOD(
            date = with(SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)) {
                format(date)
            })
        )
    }
}