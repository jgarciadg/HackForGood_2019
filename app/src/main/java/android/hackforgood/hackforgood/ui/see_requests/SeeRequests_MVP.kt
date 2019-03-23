package android.hackforgood.hackforgood.ui.see_requests

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.*

/**
 * Created by justo on 22/03/2019.
 */
interface SeeRequests_MVP {
    interface View {
        fun setDataCenter(data: List<Center>)
        fun setDataAds(data: List<Ad>)
        fun setDataTravels(data: List<Travel>)
        fun setDataCities(it: List<City>)
    }

    interface Presenter {
        fun viewLoaded()
    }

    interface Model {
        fun getCenters(): LiveData<List<Center>>
        fun getTravels(): LiveData<List<Travel>>
        fun getAd(idTravel: Int): LiveData<Ad>
        fun getCities(): LiveData<List<City>>
    }
}