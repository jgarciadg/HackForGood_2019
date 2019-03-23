package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.hackforgood.hackforgood.data.model.Ad
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
class AdRepository(private val context: Context) {
    private val adService = APIClient.Single.getAdService()
    private val userDao = Hack4GoodDatabase.getInstance(context)?.userDao()

    private val adsLiveData = MutableLiveData<List<Ad>>()
    private val adsRecommendedLiveData = MutableLiveData<List<Ad>>()
    private val adsNearOthersLiveData = MutableLiveData<List<Ad>>()
    private val adLiveData = MutableLiveData<Ad>()

    fun createAd(ad: Ad) {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            adService.createAd(it!!.username, ad).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    Log.d("AD", "Created")
                }
            })
        })
    }

    fun getOwnAds(): LiveData<List<Ad>> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            if (it != null) {
                adService.getOwnAds(it?.username!!).enqueue(object : Callback<List<Ad>> {
                    override fun onFailure(call: Call<List<Ad>>?, t: Throwable?) {
                        Log.d("", t.toString())
                    }

                    override fun onResponse(call: Call<List<Ad>>?, response: Response<List<Ad>>?) {
                        if (response?.body() != null)
                            adsLiveData.postValue(response.body())
                    }
                })
            }
        })

        return adsLiveData
    }

    fun getAdsRecommended(day: String, hour: String, idCenter: Int, idLocalidad: Int): LiveData<List<Ad>> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            adService.getAdsRecommended(it?.username!!, day, hour, idLocalidad, idCenter)
                    .enqueue(object : Callback<List<Ad>> {
                        override fun onFailure(call: Call<List<Ad>>?, t: Throwable?) {
                            Log.d("", t.toString())
                        }

                        override fun onResponse(call: Call<List<Ad>>?, response: Response<List<Ad>>?) {
                            adsRecommendedLiveData.postValue(response!!.body())
                        }
                    })
        })

        return adsRecommendedLiveData
    }

    fun getAdsNearOthers(day: String, idCenter: Int, idLocalidad: Int): LiveData<List<Ad>> {
        userDao!!.getUser().observe((context as AppCompatActivity), Observer {
            adService.getAdsNearOthers(it?.username!!, day, idLocalidad, idCenter)
                    .enqueue(object : Callback<List<Ad>> {
                        override fun onFailure(call: Call<List<Ad>>?, t: Throwable?) {
                            Log.d("", t.toString())
                        }

                        override fun onResponse(call: Call<List<Ad>>?, response: Response<List<Ad>>?) {
                            adsNearOthersLiveData.postValue(response!!.body())
                        }
                    })
        })

        return adsNearOthersLiveData
    }

    fun getAd(idAd: Int): LiveData<Ad> {
        adService.getAd(idAd)
                .enqueue(object : Callback<Ad> {
                    override fun onFailure(call: Call<Ad>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<Ad>?, response: Response<Ad>?) {
                        adLiveData.postValue(response?.body())
                    }

                })
        return adLiveData
    }
}