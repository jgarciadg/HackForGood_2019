package android.hackforgood.hackforgood.ui.login

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.User

/**
 * Created by justo on 15/03/2019.
 */
interface Login_MVP {
    interface View {
        fun showErrors(errors: List<String>)
    }

    interface Presenter {
        fun viewLoaded()
        fun registerButtonClicked()
        fun loginButtonClicked(username: String, password: String)
    }

    interface Model {
        fun checkLoginIsCorrect(username: String, password: String): LiveData<List<String>>
        fun loginUser(username: String, password: String): LiveData<List<String>>
        fun getUser(): LiveData<User>
    }
}