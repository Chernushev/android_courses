package ru.mobiledimension.nasaapp.presentation.gallery

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_gallery.*
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.*
import ru.mobiledimension.nasaapp.domain.dto.APOD
import ru.mobiledimension.nasaapp.presentation.base.BaseActivity

class GalleryActivity: BaseActivity(), GalleryView {
    private val presenter: GalleryPresenter by kodein.instance(arg = this)

    private val adapter: GalleryAdapter by lazy {
        GalleryAdapter(Glide.with(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }
        titleTV.text = getString(R.string.activity_gallary_title)

        galleryRV.layoutManager = LinearLayoutManager(this)
        galleryRV.adapter = adapter
        presenter.requestApods()
    }

    override fun showApods(apods: List<APOD>) {
            adapter.setData(apods)
            adapter.notifyDataSetChanged()
    }
}