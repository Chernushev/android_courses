package ru.mobiledimension.nasaapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_gallery.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR

class GalleryActivity : AppCompatActivity() {
    private val nasaApi = NetworkService.service.getNasaApi()
    private val listAPODs = mutableListOf<APOD>()
    private val adapter: GalleryAdapter by lazy {
        GalleryAdapter(Glide.with(this))
    }
    private val range = 0..5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }
        titleTV.text = getString(R.string.activity_gallary_title)

        galleryRV.layoutManager = LinearLayoutManager(this)
        galleryRV.adapter = adapter

        val calendar = Calendar.getInstance()

        for (i in range) {
            calendar.add(DAY_OF_MONTH, -2)
            requestAPOD(calendar.get(YEAR), calendar.get(MONTH), calendar.get(DAY_OF_MONTH))
        }
    }


    private fun requestAPOD(year: Int, month: Int, day:Int) {
        nasaApi.getAPOD(date = "${year}-${month}-${day}")
            .enqueue(
                object : Callback<APOD> {
                    override fun onFailure(call: Call<APOD>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<APOD>, response: Response<APOD>) {
                        Log.wtf("response", response.body().toString())
                        response.body()?.let {
                            listAPODs.add(it)
                            if(listAPODs.size == 5) {
                                adapter.setData(listAPODs.sortedBy { it.date })
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            )
    }
}