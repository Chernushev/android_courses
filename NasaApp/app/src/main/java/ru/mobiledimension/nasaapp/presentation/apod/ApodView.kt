package ru.mobiledimension.nasaapp.presentation.apod

import ru.mobiledimension.nasaapp.presentation.base.BaseView

interface ApodView: BaseView {
    fun showPhoto(url: String)
}