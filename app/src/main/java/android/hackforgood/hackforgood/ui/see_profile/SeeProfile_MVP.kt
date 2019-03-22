package android.hackforgood.hackforgood.ui.see_profile

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.User

/**
 * Created by justo on 20/03/2019.
 */
interface SeeProfile_MVP {
    interface View {
        fun showUserInfo(user: User)
    }

    interface Presenter {
        fun getUserInfo()
    }

    interface Model {
        fun getUser(): LiveData<User>
    }
}