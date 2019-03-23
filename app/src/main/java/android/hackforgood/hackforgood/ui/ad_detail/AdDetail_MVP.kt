package android.hackforgood.hackforgood.ui.ad_detail

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.model.Travel
import android.hackforgood.hackforgood.data.model.User

/**
 * Created by justo on 22/03/2019.
 */
interface AdDetail_MVP {
    interface View {
        fun setDataTravels(data: List<Travel>)
        fun setDataUsers(data: List<User>)
        fun setDataCenters(centers: List<Center>)
        fun setDataCities(cities: List<City>)
        fun showAcceptedPerson(travelId: Int)
    }

    interface Presenter {
        fun viewLoaded(idAd: Int)
        fun acceptTravel(idAd: Int, travelId: Int)
    }

    interface Model {
        fun getTravelsSolicited(idAd: Int): LiveData<List<Travel>>
        fun getTravellers(idAd: Int): LiveData<List<Travel>>
        fun getUserById(idUser: Int): LiveData<User>
        fun getCenters(): LiveData<List<Center>>
        fun getCities(): LiveData<List<City>>
        fun acceptPerson(idAd: Int, travelId: Int): LiveData<Boolean>
    }
}