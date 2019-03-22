package android.hackforgood.hackforgood.data.public_ad

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.repository.AdRepository
import android.hackforgood.hackforgood.data.repository.CenterRepository
import android.hackforgood.hackforgood.data.repository.CityRepository
import android.hackforgood.hackforgood.ui.public_ad.PublicAd_MVP

/**
 * Created by justo on 20/03/2019.
 */
class PublicAdModel(context: Context) : PublicAd_MVP.Model {
    private val correctData = MutableLiveData<List<String>>()
    private val citiesRepository = CityRepository()
    private val centerRepository = CenterRepository()
    private val adRepository = AdRepository(context)

    override fun publicAd(ad: Ad) {
        adRepository.createAd(ad)
    }

    override fun checkErrors(hour: String, date: String, max_people: String): LiveData<List<String>> {
        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        val errors = checkErrorsPri(hour, date, max_people)
                        correctData.postValue(errors)
                    }
                },
                100
        )

        return correctData
    }

    private fun checkErrorsPri(hour: String, date: String, max_people: String): List<String> {
        val errors = mutableListOf<String>()

        if (date.isEmpty())
            errors.add("El campo fecha no puede estar vacío")

        if (hour.isEmpty())
            errors.add("El campo hora no puede estar vacío")

        if (max_people.isEmpty())
            errors.add("El campo número máximo de acompañantes no puede estar vacío")
        else if (max_people.toInt() > 4)
            errors.add("El número máximo de acompañantes no puede ser mayor que 4, sin contar al conductor")

        return errors
    }

    override fun loadCities(): LiveData<List<City>> {
        return citiesRepository.getCities()
    }

    override fun loadCenters(): LiveData<List<Center>> {
        return centerRepository.getCenters()
    }
}