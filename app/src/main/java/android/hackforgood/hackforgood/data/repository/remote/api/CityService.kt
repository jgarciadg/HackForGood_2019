package android.hackforgood.hackforgood.data.repository.remote.api

import android.hackforgood.hackforgood.data.model.City
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by justo on 21/03/2019.
 */
interface CityService {
    @GET("localidad")
    fun getCities(): Call<List<City>>
}