package android.hackforgood.hackforgood.data.repository.remote

import android.hackforgood.hackforgood.data.repository.remote.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by justo on 15/03/2019.
 */
class APIClient {
    object Single {
        private val BASE_URL = "http://qss.unex.es/"

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        fun getUserService(): UserService {
            return getRetrofit().create(UserService::class.java)
        }
    }
}