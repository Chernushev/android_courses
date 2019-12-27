package ru.mobiledimension.nasaapp.domain.interactors

import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.domain.boundaries.NetworkBoundary
import ru.mobiledimension.nasaapp.domain.boundaries.StorageBoundary
import ru.mobiledimension.nasaapp.domain.dto.APOD
import java.util.*

class ApodInteractor(val kodein: Kodein) {
    private val  networkBoundary: NetworkBoundary by kodein.instance()
    private val  storageBoundary: StorageBoundary by kodein.instance()

    suspend fun getApod(date: Date = Date()): APOD? {
        val apodEntity = storageBoundary.getApod(date) ?: networkBoundary.getApod(date)
        apodEntity?.let {
            storageBoundary.saveApod(it)
        }
        return apodEntity
    }
}