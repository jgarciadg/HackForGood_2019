package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.repository.remote.APIClient
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by justo on 15/03/2019.
 */
class UserRepository {
    private val userService = APIClient.Single.getUserService()
    private val errorsLiveData = MutableLiveData<List<String>>()

    fun registerUser(user: User): LiveData<List<String>> {
        userService.registerUser(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Log.d("Register User", response.toString())
                errorsLiveData.postValue(listOf("No errors"))
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.d("Register User", t?.message)
                errorsLiveData.postValue(listOf("Some error"))
            }
        })

        return errorsLiveData
    }
}