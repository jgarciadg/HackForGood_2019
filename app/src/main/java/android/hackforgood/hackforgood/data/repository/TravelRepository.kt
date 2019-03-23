package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.hackforgood.hackforgood.data.model.AcceptTravelRequest
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Travel
import android.hackforgood.hackforgood.data.repository.local.Hack4GoodDatabase
import android.hackforgood.hackforgood.data.repository.remote.APIClient
import android.support.v7.app.AppCompatActivity
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by justo on 21/03/2019.
 */
class TravelRepository(private val context: Context) {
    private val userDao = Hack4GoodDatabase.getInstance(context)?.userDao()
    private var travelService = APIClient.Single.getTravelService()
    private var errorLiveData = MutableLiveData<Boolean>()
    private var adLiveData = MutableLiveData<Ad>()
    private var travelsLiveData = MutableLiveData<List<Travel>>()
    private var travelsSolicitedLiveData = MutableLiveData<List<Travel>>()
    private var travellersLiveData = MutableLiveData<List<Travel>>()
    private var acceptLiveData = MutableLiveData<Boolean>()

    fun requestTravel(idAd: Int, hour: String): LiveData<Boolean> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            travelService.createTravel(it!!.username, hour, idAd).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    errorLiveData.postValue(true)
                }

                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    Log.d("", "Travel Requested")
                    errorLiveData.postValue(false)
                }

            })
        })

        return errorLiveData
    }

    fun getTravelsRequested(): LiveData<List<Travel>> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            travelService.getTravelsRequested(it!!.username).enqueue(object : Callback<List<Travel>> {
                override fun onFailure(call: Call<List<Travel>>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<List<Travel>>?, response: Response<List<Travel>>?) {
                    travelsLiveData.postValue(response?.body())
                }

            })
        })

        return travelsLiveData
    }

    fun getTravelsSolicited(idAd: Int): LiveData<List<Travel>> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            travelService.getTravelsSolicited(it!!.username, idAd).enqueue(object : Callback<List<Travel>> {
                override fun onFailure(call: Call<List<Travel>>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<List<Travel>>?, response: Response<List<Travel>>?) {
                    travelsSolicitedLiveData.postValue(response?.body())
                }

            })
        })

        return travelsSolicitedLiveData
    }

    fun getTravellers(idAd: Int): LiveData<List<Travel>> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            travelService.getTravellers(it!!.username, idAd).enqueue(object : Callback<List<Travel>> {
                override fun onFailure(call: Call<List<Travel>>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<List<Travel>>?, response: Response<List<Travel>>?) {
                    travellersLiveData.postValue(response?.body())
                }

            })
        })

        return travellersLiveData
    }

    fun acceptTravel(idAd: Int, idTravel: Int): LiveData<Boolean> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            if (it != null) {
                travelService.acceptTravel(it.username, idAd, idTravel, AcceptTravelRequest(true))
                        .enqueue(object : Callback<Void> {
                            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                                acceptLiveData.postValue(false)
                            }

                            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                                acceptLiveData.postValue(true)
                            }

                        })
            }
        })

        return acceptLiveData
    }
}