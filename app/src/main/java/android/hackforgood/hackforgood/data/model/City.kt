package android.hackforgood.hackforgood.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by justo on 21/03/2019.
 */
data class City(val id: Int,
                @SerializedName("nombre")
                var name: String,
                @SerializedName("latitud")
                var lat: Float,
                @SerializedName("longitud")
                var long: Float)