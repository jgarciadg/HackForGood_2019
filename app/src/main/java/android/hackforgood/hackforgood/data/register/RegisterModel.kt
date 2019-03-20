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
class RegisterModel(context: Context) : Register_MVP.Model {
    private val correctRegisterLiveData = MutableLiveData<List<String>>()
    private val userRepository = UserRepository(context)

    override fun checkIfRegisterIsCorrect(name: String, lastName: String, age: String, sex: String,
                                          username: String, password: String): LiveData<List<String>> {

        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        val errors = checkErrors(name, lastName, age, sex, username, password)
                        correctRegisterLiveData.postValue(errors)
                    }
                },
                100
        )

        return correctRegisterLiveData
    }

    override fun registerUser(user: User): LiveData<List<String>> {
        return userRepository.registerUser(user)
    }

    private fun checkErrors(name: String, lastName: String, age: String, sex: String,
                            username: String, password: String): List<String> {
        val errors = mutableListOf<String>()

        if (name.isBlank() || name.isEmpty())
            errors.add("El campo nombre no puede estar vacío")
        if (lastName.isBlank() || lastName.isEmpty())
            errors.add("El campo apellidos no puede estar vacío")
        if (age.isEmpty() || age.isBlank())
            errors.add("El campo Edad no puede estar vacío")

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