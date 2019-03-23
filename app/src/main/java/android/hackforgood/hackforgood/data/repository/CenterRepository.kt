package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.repository.remote.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by justo on 21/03/2019.
 */
class CenterRepository {
    private val centerLiveData = MutableLiveData<List<Center>>()
    private val centerService = APIClient.Single.getCenterService()

    fun getCenters(): LiveData<List<Center>> {
        centerService.getCenters().enqueue(object : Callback<List<Center>> {
            override fun onFailure(call: Call<List<Center>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Center>>?, response: Response<List<Center>>?) {
                if(response?.body() != null)
                    centerLiveData.postValue(response?.body()!!)
            }

        })

        return centerLiveData
    }
}