package ru.mobiledimension.nasaapp.presentation.gallery

import kotlinx.coroutines.runBlocking
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.domain.dto.APOD
import ru.mobiledimension.nasaapp.domain.interactors.ApodInteractor
import ru.mobiledimension.nasaapp.presentation.base.BasePresenter
import java.util.*

class GalleryPresenter(kodein: Kodein, private val galleryView: GalleryView): BasePresenter() {
    val apodInteractor: ApodInteractor by kodein.instance()
    private val range = 0..5
    private val listAPODs = mutableListOf<APOD>()

    fun requestApods() {
        val calendar = Calendar.getInstance()

        for (i in range) {
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            runBlocking {
                requestAPOD(calendar.time)
            }
        }
    }

    private suspend fun requestAPOD(date: Date) {
        val apod = apodInteractor.getApod(date = date)
        apod?.let { listAPODs.add(it) }
        if(listAPODs.size == 5) {
            listAPODs.sortedBy { it.date }
            galleryView.showApods(listAPODs)
        }
    }
}