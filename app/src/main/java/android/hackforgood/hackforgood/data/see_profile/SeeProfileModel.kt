package android.hackforgood.hackforgood.data.see_profile

import android.arch.lifecycle.LiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.repository.local.Hack4GoodDatabase
import android.hackforgood.hackforgood.ui.see_profile.SeeProfile_MVP

/**
 * Created by justo on 20/03/2019.
 */
class SeeProfileModel(context: Context) : SeeProfile_MVP.Model {
    private val userDao = Hack4GoodDatabase.getInstance(context)?.userDao()

    override fun getUser(): LiveData<User> {
        return userDao!!.getUser()
    }
}