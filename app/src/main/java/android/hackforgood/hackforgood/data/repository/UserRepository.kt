package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.hackforgood.hackforgood.common.DBWorker
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.repository.local.Hack4GoodDatabase
import android.hackforgood.hackforgood.data.repository.remote.APIClient
import android.os.AsyncTask

/**
 * Created by justo on 15/03/2019.
 */
class UserRepository(context: Context) {
    private lateinit var dbWorker: DBWorker
    private val errorsLiveData = MutableLiveData<List<String>>()

    private val userService = APIClient.Single.getUserService()
    private val userDao = Hack4GoodDatabase.getInstance(context)?.userDao()

    fun getUser(): LiveData<User> {
        return userDao!!.getUser()
    }

    fun loginUser(username: String, password: String): LiveData<List<String>> {
        /*
        userService.loginUser(User(username, password)).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Log.d("Login User", response.toString())
                if(response!!.code() == 200){
                    errorsLiveData.postValue(listOf())
                    registerUserInDB(User(username, password))
                }else
                    errorsLiveData.postValue(listOf("El usuario o la contraseña no son correctas"))
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.d("Login User", t?.message)
            }
        })
        */

        //TODO drop Timer when server is ready
        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        errorsLiveData.postValue(listOf())
                        registerUserInDB(User(username, password))
                    }
                },
                100
        )
        return errorsLiveData
    }

    fun registerUser(user: User): LiveData<List<String>> {
        /*userService.registerUser(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Log.d("Register User", response.toString())
                if (response!!.code() == 200) {
                    errorsLiveData.postValue(listOf())
                    registerUserInDB(User(user.username, user.password))
                } else
                    errorsLiveData.postValue(listOf("El registro ha fallado"))

            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.d("Register User", t?.message)
            }
        })*/

        //TODO drop Timer when server is ready
        java.util.Timer().schedule(
                object : java.util.TimerTask() {
                    override fun run() {
                        registerUserInDB(user)
                        errorsLiveData.postValue(listOf())
                    }
                },
                5000
        )

        return errorsLiveData
    }

    private fun registerUserInDB(user: User) {
        val asyncTask = object : AsyncTask<User, Unit, Unit>() {
            override fun doInBackground(vararg params: User?) {
                userDao?.saveUser(user)
            }
        }
        asyncTask.execute()
    }
}