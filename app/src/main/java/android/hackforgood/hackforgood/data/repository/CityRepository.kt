package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.repository.remote.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by justo on 21/03/2019.
 */
class CityRepository {
    private val citiesLiveData = MutableLiveData<List<City>>()
    private val cityService = APIClient.Single.getCityService()

    fun getCities(): LiveData<List<City>> {
        cityService.getCities().enqueue(object : Callback<List<City>> {
            override fun onFailure(call: Call<List<City>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<City>>?, response: Response<List<City>>?) {
                citiesLiveData.postValue(response!!.body()!!)
            }

        })

        return citiesLiveData
    }
}