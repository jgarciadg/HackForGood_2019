package android.hackforgood.hackforgood.data.repository.remote.api

import android.hackforgood.hackforgood.data.model.AcceptTravelRequest
import android.hackforgood.hackforgood.data.model.Travel
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by justo on 21/03/2019.
 */
interface TravelService {
    @POST("ad/{idAd}/travel")
    fun createTravel(@Header("Authorization") username: String, @Body hora_cita: String, @Path("idAd") idAd: Int): Call<Void>

    @GET("ad/{idAd}/solicited")
    fun getTravelsSolicited(@Header("Authorization") username: String, @Path("idAd") idAd: Int): Call<List<Travel>>

    @GET("ad/{idAd}/travellers")
    fun getTravellers(@Header("Authorization") username: String, @Path("idAd") idAd: Int): Call<List<Travel>>

    @GET("ad/travel")
    fun getTravelsRequested(@Header("Authorization") username: String): Call<List<Travel>>

    @PUT("ad/{idAd}/travel/{idTravel}/confirm")
    fun acceptTravel(@Header("Authorization") username: String, @Path("idAd") idAd: Int, @Path("idTravel") idTravel: Int, @Body data: AcceptTravelRequest): Call<Void>
}