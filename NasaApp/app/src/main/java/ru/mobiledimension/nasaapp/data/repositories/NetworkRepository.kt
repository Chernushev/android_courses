package ru.mobiledimension.nasaapp.data.repositories

import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.M
import org.kodein.di.generic.instance
import ru.mobiledimension.nasaapp.data.network.NasaApi

class NetworkRepository (val kodein: Kodein) {
    private val nasaApi: NasaApi by kodein.instance()
}