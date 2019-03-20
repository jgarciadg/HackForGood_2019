package android.hackforgood.hackforgood.data.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.repository.UserRepository
import android.hackforgood.hackforgood.ui.login.Login_MVP
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 15/03/2019.
 */
class LoginModel(context: Context) : Login_MVP.Model {
    private val correctLoginLiveData = MutableLiveData<List<String>>()
    private val userRepository = UserRepository(context)
    private val lifecycleOwner = context as AppCompatActivity

    override fun getUser(): LiveData<User> {
        return userRepository.getUser()
    }

    override fun checkLoginIsCorrect(username: String, password: String): LiveData<List<String>> {
        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        val errors = checkErrors(username, password)
                        correctLoginLiveData.postValue(errors)
                    }
                },
                100
        )
        return correctLoginLiveData
    }

    override fun loginUser(username: String, password: String): LiveData<List<String>> {
        return userRepository.loginUser(username, password)
    }


    private fun checkErrors(username: String, password: String): List<String> {
        val errors = mutableListOf<String>()

        if (username.contains(" "))
            errors.add("El nombre de usuario no puede contener espacios")
        else if (username.isBlank() || username.isEmpty())
            errors.add("El campo nombre de usuario no puede estar vacío")

        if (password.contains(" "))
            errors.add("La contraseña no puede contener espacios")
        else if (password.isBlank() || password.isEmpty())
            errors.add("El campo contraseña no puede estar vacío")

        return errors
    }
}