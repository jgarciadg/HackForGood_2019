package android.hackforgood.hackforgood.ui.see_ads_nearest

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City

/**
 * Created by justo on 22/03/2019.
 */
interface SeeAdsNearest_MVP {
    interface View {
        fun setDataToShow(data: List<Ad>)
        fun setDataCenter(data: List<Center>)
        fun setDataToShowOthers(data: List<Ad>)
        fun showErrorInRequestAd()
        fun showAdRequested()
        fun setDataCities(it: List<City>)
    }

    interface Presenter {
        fun viewLoaded(day: String, hour: String, idCenter: Int, idLocalidad: Int)
        fun requestTravelButtonClicked(idAd: Int, hour: String)
    }

    interface Model {
        fun getOthersAds(day: String, idCenter: Int, idLocalidad: Int): LiveData<List<Ad>>
        fun getCenters(): LiveData<List<Center>>
        fun requestTravel(hour: String, idAd: Int): LiveData<Boolean>
        fun getAdsRecommended(day: String, hour: String, idLocalidad: Int, idCenter: Int): LiveData<List<Ad>>
        fun getCities(): LiveData<List<City>>
    }
}