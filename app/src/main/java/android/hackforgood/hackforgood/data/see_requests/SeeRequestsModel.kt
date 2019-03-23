package android.hackforgood.hackforgood.data.see_requests

import android.arch.lifecycle.LiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.model.Travel
import android.hackforgood.hackforgood.data.repository.AdRepository
import android.hackforgood.hackforgood.data.repository.CenterRepository
import android.hackforgood.hackforgood.data.repository.CityRepository
import android.hackforgood.hackforgood.data.repository.TravelRepository
import android.hackforgood.hackforgood.ui.see_requests.SeeRequests_MVP

/**
 * Created by justo on 22/03/2019.
 */
class SeeRequestsModel(context: Context): SeeRequests_MVP.Model {
    private val centerRepository = CenterRepository()
    private val adRepository = AdRepository(context)
    private val travelRepository = TravelRepository(context)
    private val citiesRepository = CityRepository()

    override fun getCenters(): LiveData<List<Center>> {
        return centerRepository.getCenters()
    }

    override fun getAd(idAd: Int): LiveData<Ad> {
        return adRepository.getAd(idAd)
    }

    override fun getTravels(): LiveData<List<Travel>> {
        return travelRepository.getTravelsRequested()
    }

    override fun getCities(): LiveData<List<City>> {
        return citiesRepository.getCities()
    }
}