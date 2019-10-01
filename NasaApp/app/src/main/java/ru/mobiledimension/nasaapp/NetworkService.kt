package ru.mobiledimension.nasaapp

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


class NetworkService {
    private val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor(HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY })


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getNasaApi(): NasaApi = retrofit.create(NasaApi::class.java)

    companion object {
        const val baseUrl = "https://api.nasa.gov/"

        val service: NetworkService by lazy {
            NetworkService()
        }
    }
}