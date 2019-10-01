package ru.mobiledimension.nasaapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("planetary/apod")
    fun getAPOD(@Query("api_key") apiKey: String): Call<APOD>
}