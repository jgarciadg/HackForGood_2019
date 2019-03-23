package android.hackforgood.hackforgood.data.ad_detail

import android.arch.lifecycle.LiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.model.Travel
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.repository.CenterRepository
import android.hackforgood.hackforgood.data.repository.CityRepository
import android.hackforgood.hackforgood.data.repository.TravelRepository
import android.hackforgood.hackforgood.data.repository.UserRepository
import android.hackforgood.hackforgood.ui.ad_detail.AdDetail_MVP

/**
 * Created by justo on 22/03/2019.
 */
class AdDetailModel(private val context: Context): AdDetail_MVP.Model {
    private val travelRepository = TravelRepository(context)
    private val userRepository = UserRepository(context)
    private val centerRepository = CenterRepository()
    private val citiesRepository = CityRepository()

    override fun getTravelsSolicited(idAd: Int): LiveData<List<Travel>> {
        return travelRepository.getTravelsSolicited(idAd)
    }

    override fun getTravellers(idAd: Int): LiveData<List<Travel>> {
        return travelRepository.getTravellers(idAd)
    }

    override fun getUserById(idUser: Int): LiveData<User> {
        return userRepository.getUserById(idUser)
    }

    override fun getCenters(): LiveData<List<Center>> {
        return centerRepository.getCenters()
    }

    override fun getCities(): LiveData<List<City>> {
        return citiesRepository.getCities()
    }

    override fun acceptPerson(idAd: Int, travelId: Int): LiveData<Boolean> {
        return travelRepository.acceptTravel(idAd, travelId)
    }

}