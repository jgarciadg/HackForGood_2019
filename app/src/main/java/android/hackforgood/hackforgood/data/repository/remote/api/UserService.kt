package android.hackforgood.hackforgood.data.repository.remote.api

import android.hackforgood.hackforgood.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by justo on 15/03/2019.
 */
interface UserService {
    @POST("url")//TODO set URL to register User
    fun registerUser(@Body user: User): Call<Void>
}