package ru.mobiledimension.nasaapp.presentation.apod

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_apod.*
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.*
import ru.mobiledimension.nasaapp.presentation.base.BaseActivity
import java.util.*

class ApodActivity: BaseActivity(), ApodView {
    private val presenter: ApodPresenter by kodein.instance(arg = this)

    private val glide: RequestManager by lazy {
        Glide.with(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }
        titleTV.text = getString(R.string.activity_apod_title)
        apodLoadBN.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.set(
                yearET.text.toString().toInt(),
                monthET.text.toString().toInt(),
                dayET.text.toString().toInt()
            )
            presenter.requestApod(calendar.time)
        }
    }

    override fun showPhoto(url: String) {
        glide.load(url).into(apodIV)
    }
}