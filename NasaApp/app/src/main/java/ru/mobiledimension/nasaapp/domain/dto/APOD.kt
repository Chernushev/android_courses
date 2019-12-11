package ru.mobiledimension.nasaapp.domain.dto


data class APOD(
    val date: String,
    val copyright: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
    val id: Int? //new field for migration
)