package android.hackforgood.hackforgood.data.repository.remote

import android.hackforgood.hackforgood.data.repository.remote.api.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by justo on 15/03/2019.
 */
class APIClient {
    object Single {
        private val BASE_URL = "http://qss.unex.es:2072/"

        private fun getRetrofit(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        fun getUserService(): UserService {
            return getRetrofit().create(UserService::class.java)
        }

        fun getCityService(): CityService {
            return getRetrofit().create(CityService::class.java)
        }

        fun getCenterService(): CenterService {
            return getRetrofit().create(CenterService::class.java)
        }

        fun getAdService(): AdService {
            return getRetrofit().create(AdService::class.java)
        }

        fun getTravelService(): TravelService {
            return getRetrofit().create(TravelService::class.java)
        }
    }
}