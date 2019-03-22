package android.hackforgood.hackforgood.data.search_travel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.repository.CenterRepository
import android.hackforgood.hackforgood.data.repository.CityRepository
import android.hackforgood.hackforgood.ui.search_travel.SearchTravel_MVP

/**
 * Created by justo on 16/03/2019.
 */
class SearchTravelModel : SearchTravel_MVP.Model {
    private val citiesRepository = CityRepository()
    private val centerRepository = CenterRepository()

    private val correctData = MutableLiveData<List<String>>()

    override fun checkErrors(hour: String, date: String): LiveData<List<String>> {
        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        val errors = checkErrorsPri(hour, date)
                        correctData.postValue(errors)
                    }
                },
                100
        )

        return correctData
    }

    private fun checkErrorsPri(hour: String, date: String): List<String> {
        val errors = mutableListOf<String>()

        if (date.isEmpty())
            errors.add("El campo fecha no puede estar vacío")

        if (hour.isEmpty())
            errors.add("El campo hora no puede estar vacío")

        return errors
    }

    override fun loadCities(): LiveData<List<City>> {
        return citiesRepository.getCities()
    }

    override fun loadCenters(): LiveData<List<Center>> {
        return centerRepository.getCenters()
    }
}