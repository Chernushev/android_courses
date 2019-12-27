package ru.mobiledimension.nasaapp.presentation.base

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Kodein
import ru.mobiledimension.nasaapp.presentation.NasaApplication


abstract class BaseActivity: AppCompatActivity(), BaseView{
    val kodein = Kodein.lazy {
        extend((application as NasaApplication).kodein)
    }
}