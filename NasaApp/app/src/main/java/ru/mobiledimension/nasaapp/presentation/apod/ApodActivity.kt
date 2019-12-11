package ru.mobiledimension.nasaapp.presentation.apod

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_apod.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mobiledimension.nasaapp.*
import ru.mobiledimension.nasaapp.data.db.room.ApodDao
import ru.mobiledimension.nasaapp.data.network.NetworkService
import ru.mobiledimension.nasaapp.domain.dto.APOD
import ru.mobiledimension.nasaapp.presentation.NasaApplication

class ApodActivity: AppCompatActivity(), ApodView {
    private val nasaApi = NetworkService.service.getNasaApi()

    private val dao: ApodDao by lazy {
        NasaApplication.getDatabase().apodDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }
        titleTV.text = getString(R.string.activity_apod_title)
        apodLoadBN.setOnClickListener { requestAPOD() }
    }

    private fun requestAPOD() {
        nasaApi.getAPOD(date = "${yearET.text}-${monthET.text}-${dayET.text}")
            .enqueue(
                object : Callback<APOD> {
                    override fun onFailure(call: Call<APOD>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<APOD>, response: Response<APOD>) {
                        Log.wtf("response", response.body().toString())
                        response.body()?.url?.let {
                            Glide.with(apodIV)
                                .load(it)
                                .into(apodIV)
                        }

                        response.body()?.let {
                            dao.insert(it)
                        }
                    }
                }
            )
    }
}