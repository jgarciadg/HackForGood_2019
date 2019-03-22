package android.hackforgood.hackforgood.data.model

import com.google.gson.annotations.SerializedName

data class Travel(
        val acepted: Boolean = false,
        val creator: Boolean = false,
        @SerializedName("hora_cita")
        val hourCita: String,
        @SerializedName("id_ad")
        val idAd: Int,
        @SerializedName("id_user")
        val idUser: Int,
        @SerializedName("revised")
        val revised: Boolean = false,
        @SerializedName("valoration")
        val valoration: Int = -1
)