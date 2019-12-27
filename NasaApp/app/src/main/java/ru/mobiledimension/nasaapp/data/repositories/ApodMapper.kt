package ru.mobiledimension.nasaapp.data.repositories

import ru.mobiledimension.nasaapp.data.db.room.ApodEntity
import ru.mobiledimension.nasaapp.domain.dto.APOD

class ApodMapper {
    fun map(apodEntity: ApodEntity) = APOD(
        apodEntity.date,
        apodEntity.copyright ?: "",
        apodEntity.explanation,
        apodEntity.hdurl ?: "",
        apodEntity.media_type,
        apodEntity.service_version,
        apodEntity.title,
        apodEntity.url
    )

    fun map(apod: APOD) = ApodEntity(
        apod.date,
        apod.copyright,
        apod.explanation,
        apod.hdurl,
        apod.media_type,
        apod.service_version,
        apod.title,
        apod.url,
        null
    )
}
