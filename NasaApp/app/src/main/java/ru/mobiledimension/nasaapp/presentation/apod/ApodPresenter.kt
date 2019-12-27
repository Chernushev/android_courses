package ru.mobiledimension.nasaapp.presentation.apod

import kotlinx.coroutines.runBlocking
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.domain.interactors.ApodInteractor
import ru.mobiledimension.nasaapp.presentation.base.BasePresenter
import java.util.*

class ApodPresenter(kodein: Kodein, private val apodView: ApodView): BasePresenter() {
    val apodInteractor: ApodInteractor by kodein.instance()

    fun requestApod(date: Date){
        runBlocking {
            val apod = apodInteractor.getApod(date)
            apod?.url?.let {
                apodView.showPhoto(it)
            }
        }
    }
}