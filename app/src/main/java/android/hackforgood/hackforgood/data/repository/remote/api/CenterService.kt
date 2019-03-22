package android.hackforgood.hackforgood.data.repository.remote.api

import android.hackforgood.hackforgood.data.model.Center
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by justo on 21/03/2019.
 */
interface CenterService {
    @GET("center")
    fun getCenters(): Call<List<Center>>
}