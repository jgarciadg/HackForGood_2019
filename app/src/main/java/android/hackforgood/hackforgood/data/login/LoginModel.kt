package android.hackforgood.hackforgood.data.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.hackforgood.hackforgood.ui.login.Login_MVP

/**
 * Created by justo on 15/03/2019.
 */
class LoginModel(private val context: Context) : Login_MVP.Model {
    private val correctLoginLiveData = MutableLiveData<List<String>>()

    override fun checkLoginIsCorrect(username: String, password: String): LiveData<List<String>> {
        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        correctLoginLiveData.postValue(listOf())
                    }
                },
                5000
        )

        return correctLoginLiveData
    }
}