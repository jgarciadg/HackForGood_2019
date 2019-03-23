package android.hackforgood.hackforgood.data.repository.remote.api

import android.hackforgood.hackforgood.data.model.Ad
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by justo on 21/03/2019.
 */
interface AdService {
    @POST("ad")
    fun createAd(@Header("Authorization") username: String, @Body ad: Ad): Call<Void>

    @GET("ad/{id}")
    fun getAd(@Path("id") id: Int): Call<Ad>

    @PUT("ad/{id}")
    fun endAd(@Path("id") id: Int, @Body finalized: Boolean): Call<Void>

    @GET("ad")
    fun getOwnAds(@Header("Authorization") username: String): Call<List<Ad>>

    @GET("ad/recommended")
    fun getAdsRecommended(@Header("Authorization") username: String,
                          @Query("day") day: String,
                          @Query("hour_appointment") hour: String,
                          @Query("id_localidad") idLocalidad: Int,
                          @Query("id_center") idCenter: Int): Call<List<Ad>>

    @GET("ad/allday")
    fun getAdsNearOthers(@Header("Authorization") username: String,
                         @Query("day") day: String,
                         @Query("id_localidad") idLocalidad: Int,
                         @Query("id_center") idCenter: Int): Call<List<Ad>>
}
