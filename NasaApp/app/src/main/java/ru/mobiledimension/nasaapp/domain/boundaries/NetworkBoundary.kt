package ru.mobiledimension.nasaapp.domain.boundaries

import ru.mobiledimension.nasaapp.domain.dto.APOD
import java.util.*

interface NetworkBoundary {
    suspend fun getApod(date: Date): APOD?
}