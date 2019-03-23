package android.hackforgood.hackforgood.data.repository.remote.api

import android.hackforgood.hackforgood.data.model.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by justo on 15/03/2019.
 */
interface UserService {
    @POST("registry")
    fun registerUser(@Body user: User): Call<Void>

    @POST("login")
    fun loginUser(@Body loginUser: User): Call<Void>

    @GET("user")
    fun getUser(@Header("Authorization") username: String): Call<User>

    @GET("user/{idUser}")
    fun getUserById(@Path("idUser") idUser: Int): Call<User>
}