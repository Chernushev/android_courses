package ru.mobiledimension.nasaapp.presentation.gallery

import ru.mobiledimension.nasaapp.domain.dto.APOD
import ru.mobiledimension.nasaapp.presentation.base.BaseView

interface GalleryView: BaseView {
    fun showApods(apods: List<APOD>)
}