package android.hackforgood.hackforgood.data.repository.remote.api

import android.hackforgood.hackforgood.data.model.Travel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by justo on 21/03/2019.
 */
interface TravelService {
    @POST("ad/{idAd}/travel")
    fun createTravel(@Header("Authorization") username: String, @Body hora_cita: String): Call<Travel>
}