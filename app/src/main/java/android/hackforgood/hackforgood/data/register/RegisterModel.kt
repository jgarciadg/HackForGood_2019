package android.hackforgood.hackforgood.data.register

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.repository.UserRepository
import android.hackforgood.hackforgood.ui.register.Register_MVP

/**
 * Created by justo on 15/03/2019.
 */
class RegisterModel(private val context: Context) : Register_MVP.Model {
    private val correctRegisterLiveData = MutableLiveData<List<String>>()
    private val userRepository = UserRepository()

    override fun checkIfRegisterIsCorrect(name: String, lastName: String, age: String, sex: String,
                                          username: String, password: String): LiveData<List<String>> {

        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        val errors = checkErrors(name, lastName, age, sex, username, password)
                        correctRegisterLiveData.postValue(errors)
                    }
                },
                5000
        )

        return correctRegisterLiveData
    }

    override fun registerUser(user: User): LiveData<List<String>> {
        return userRepository.registerUser(user)
    }

    private fun checkErrors(name: String, lastName: String, age: String, sex: String,
                            username: String, password: String): List<String> {
        val errors = mutableListOf<String>()

        if (age.isEmpty() || age.isBlank())
            errors.add("El campo Edad no puede estar vacío.")

        //TODO check if username exists in api

        return errors
    }
}