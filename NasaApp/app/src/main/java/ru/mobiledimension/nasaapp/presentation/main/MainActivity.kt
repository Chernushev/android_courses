package ru.mobiledimension.nasaapp.presentation.main

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.mobiledimension.nasaapp.R
import ru.mobiledimension.nasaapp.presentation.apod.ApodActivity
import ru.mobiledimension.nasaapp.presentation.base.BaseActivity
import ru.mobiledimension.nasaapp.presentation.gallery.GalleryActivity

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apodBN.setOnClickListener {
            val intent = Intent(this, ApodActivity::class.java)
            startActivity(intent)
        }

        galleryBN.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        exitBtn.setOnClickListener { finish() }
    }
}
