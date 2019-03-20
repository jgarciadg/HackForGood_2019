package android.hackforgood.hackforgood.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by justo on 15/03/2019.
 */
@Entity(tableName = "user")
data class User(val username: String,
                val password: String,
                val name: String = "",
                val lastName: String = "",
                val age: Int = 0,
                val sex: String = "",
                @PrimaryKey(autoGenerate = true) val idUser: Int = 10,
                val imageUrl: String = "",
                val rating: Int = 0)