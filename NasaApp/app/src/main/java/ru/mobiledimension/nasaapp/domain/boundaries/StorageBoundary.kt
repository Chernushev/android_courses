package ru.mobiledimension.nasaapp.domain.boundaries

import ru.mobiledimension.nasaapp.domain.dto.APOD
import java.util.*

interface StorageBoundary {
    suspend fun getApod(date: Date): APOD?
    suspend fun saveApod(apod: APOD)
}