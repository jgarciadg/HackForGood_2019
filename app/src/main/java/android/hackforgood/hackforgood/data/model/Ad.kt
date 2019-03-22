package android.hackforgood.hackforgood.data.model

import com.google.gson.annotations.SerializedName

data class Ad(
        @SerializedName("id_localidad")
        var idLocalidad: Int,
        @SerializedName("id_center")
        var idCenter: Int,
        var day: String,
        @SerializedName("time_go")
        var timeGo: String,
        @SerializedName("time_arrive_provisional")
        var timeArriveProvisional: String,
        @SerializedName("max_persons")
        var maxPersons: Int,
        @SerializedName("time_last_appointment")
        var timeLastAppointment: String = "",
        var confirmed_persons: Int = 0,
        var solicited_persons: Int = 0,
        var finalized: Boolean = false,
        var id: Int = 10,
        var id_creator: Int = 10
)