package android.hackforgood.hackforgood.data.repository.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.hackforgood.hackforgood.data.model.User

/**
 * Created by justo on 18/03/2019.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Insert(onConflict = REPLACE)
    fun saveUser(user: User)
}