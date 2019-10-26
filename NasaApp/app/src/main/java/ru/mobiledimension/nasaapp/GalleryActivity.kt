package ru.mobiledimension.nasaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_apod.titleTV
import kotlinx.android.synthetic.main.activity_apod.toolbar
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {
    private val adapter: GallaryAdapter by lazy {
        GallaryAdapter(Glide.with(this))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        toolbar.setNavigationIcon(R.drawable.icn_back)
        toolbar.setNavigationOnClickListener { finish() }
        titleTV.text = getString(R.string.activity_gallery_title)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }
}