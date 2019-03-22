package android.hackforgood.hackforgood.ui.register

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.User

/**
 * Created by justo on 15/03/2019.
 */
interface Register_MVP {
    interface View {
        fun showErrors(errors: List<String>)
    }

    interface Presenter {
        fun registerButtonClicked(name: String, lastName: String, age: String, sex: String, username: String, password: String, phone: String)
    }

    interface Model {
        fun checkIfRegisterIsCorrect(name: String, lastName: String, age: String, sex: String, username: String, password: String)
                : LiveData<List<String>>

        fun registerUser(user: User): LiveData<List<String>>
    }
}