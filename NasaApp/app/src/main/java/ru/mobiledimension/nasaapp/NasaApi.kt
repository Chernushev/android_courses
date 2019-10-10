package ru.mobiledimension.nasaapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val nasaApiKey = "A1dgZrXUk2uAihpYr9iNlWzPR1q7GiUDSlkXYPU1"
interface NasaApi {
    @GET("planetary/apod")
    fun getAPOD(@Query("date") date: String,
                @Query("api_key") apiKey: String = nasaApiKey): Call<APOD>
}