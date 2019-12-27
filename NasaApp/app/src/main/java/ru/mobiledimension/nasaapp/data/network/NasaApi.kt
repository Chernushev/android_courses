package ru.mobiledimension.nasaapp.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mobiledimension.nasaapp.data.db.room.ApodEntity


const val nasaApiKey = "A1dgZrXUk2uAihpYr9iNlWzPR1q7GiUDSlkXYPU1"
interface NasaApi {
    @GET("planetary/apod")
    suspend fun getAPOD(@Query("date") date: String,
                @Query("api_key") apiKey: String = nasaApiKey): ApodEntity
}