package android.hackforgood.hackforgood.ui.login

import android.arch.lifecycle.LiveData

/**
 * Created by justo on 15/03/2019.
 */
interface Login_MVP {
    interface View {
        fun showErrors(errors: List<String>)
    }

    interface Presenter {
        fun registerButtonClicked()
        fun loginButtonClicked(username: String, password: String)
    }

    interface Model {
        fun checkLoginIsCorrect(username: String, password: String): LiveData<List<String>>
    }
}