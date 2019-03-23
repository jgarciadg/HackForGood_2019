package android.hackforgood.hackforgood.data.see_ads_nearest

import android.arch.lifecycle.LiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.repository.AdRepository
import android.hackforgood.hackforgood.data.repository.CenterRepository
import android.hackforgood.hackforgood.data.repository.CityRepository
import android.hackforgood.hackforgood.data.repository.TravelRepository
import android.hackforgood.hackforgood.ui.see_ads_nearest.SeeAdsNearest_MVP

/**
 * Created by justo on 22/03/2019.
 */
class SeeAdsNearestModel(context: Context) : SeeAdsNearest_MVP.Model {
    private val adRepository = AdRepository(context)
    private val centerRepository = CenterRepository()
    private val travelRepository = TravelRepository(context)
    private val cityRepository = CityRepository()

    override fun requestTravel(hour: String, idAd: Int): LiveData<Boolean> {
        return travelRepository.requestTravel(idAd, hour)
    }

    override fun getAdsRecommended(day: String, hour: String, idCenter: Int, idLocalidad: Int): LiveData<List<Ad>> {
        return adRepository.getAdsRecommended(day, hour, idCenter, idLocalidad)
    }

    override fun getOthersAds(day: String, idCenter: Int, idLocalidad: Int): LiveData<List<Ad>> {
        return adRepository.getAdsNearOthers(day, idCenter, idLocalidad)
    }

    override fun getCenters(): LiveData<List<Center>> {
        return centerRepository.getCenters()
    }

    override fun getCities(): LiveData<List<City>> {
        return cityRepository.getCities()
    }
}