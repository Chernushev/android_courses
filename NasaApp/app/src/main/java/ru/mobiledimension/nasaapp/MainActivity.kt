package ru.mobiledimension.nasaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apodBtn.setOnClickListener {
            val intent = Intent(this, ApodActivity::class.java)
            startActivity(intent)
        }

        galleryBtn.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        exitBtn.setOnClickListener { finish() }
    }
}
