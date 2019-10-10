package ru.mobiledimension.nasaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val nasaApi = NetworkService.service.getNasaApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        magicBtn.setOnClickListener { requestAPOD() }
    }


    private fun requestAPOD() {
        nasaApi.getAPOD(date = dateET.text.toString())
            .enqueue(
                object : Callback<APOD> {
                    override fun onFailure(call: Call<APOD>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<APOD>, response: Response<APOD>) {
                        Log.wtf("response", response.body().toString())
                        response.body()?.url?.let {
                            Glide.with(imageIV)
                                .load(it)
                                .into(imageIV)
                        }
                    }
                }
            )
    }
}
