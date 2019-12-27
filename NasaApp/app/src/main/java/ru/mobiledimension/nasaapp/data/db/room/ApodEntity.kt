package ru.mobiledimension.nasaapp.data.db.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "APOD")
data class ApodEntity (
    @PrimaryKey
    val date: String,
    val copyright: String?,
    val explanation: String,
    val hdurl: String?,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
    @ColumnInfo(name = "id")
    val id: Int? //new field for migration
)