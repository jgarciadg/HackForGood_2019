package android.hackforgood.hackforgood.data.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.hackforgood.hackforgood.data.model.User

/**
 * Created by justo on 18/03/2019.
 */
@Database(entities = arrayOf(User::class), version = 1)
abstract class Hack4GoodDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: Hack4GoodDatabase? = null

        fun getInstance(context: Context): Hack4GoodDatabase? {
            if (INSTANCE == null) {
                synchronized(Hack4GoodDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            Hack4GoodDatabase::class.java, "hack4good.db").build()
                }
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}