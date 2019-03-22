package android.hackforgood.hackforgood.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by justo on 15/03/2019.
 */
@Entity(tableName = "user")
data class User(
        @SerializedName("username")
        var username: String,
        @SerializedName("password")
        var password: String,
        @SerializedName("firstname")
        var firstName: String = "",
        @SerializedName("lastname")
        var lastName: String = "",
        @SerializedName("years")
        var years: Int = 0,
        @SerializedName("genre")
        var genre: String = "",
        @SerializedName("id")
        @PrimaryKey(autoGenerate = true) val id: Int = 10,
        var idUser: Int = 10,
        @SerializedName("photo")
        var photo: String = "",
        @SerializedName("telephone")
        var telephone: String = "",
        @SerializedName("avg_judgment")
        var avgJudgment: Int = 0,
        @SerializedName("number_judgment")
        var numberJudgment: Int = 0)