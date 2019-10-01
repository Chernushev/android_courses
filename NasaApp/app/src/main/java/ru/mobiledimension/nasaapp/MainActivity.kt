package ru.mobiledimension.nasaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        magicBtn.setOnClickListener {
            NetworkService
                .service
                .getNasaApi()
                .getAPOD("A1dgZrXUk2uAihpYr9iNlWzPR1q7GiUDSlkXYPU1")
                .enqueue(
                    object : Callback<APOD> {
                        override fun onFailure(call: Call<APOD>, t: Throwable) {
                            t.printStackTrace()
                        }

                        override fun onResponse(call: Call<APOD>, response: Response<APOD>) {
                            Log.wtf("response", response.body().toString())
                        }
                    }
                )
        }
    }
}
