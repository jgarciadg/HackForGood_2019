package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.repository.local.Hack4GoodDatabase
import android.hackforgood.hackforgood.data.repository.remote.APIClient
import android.os.AsyncTask
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by justo on 15/03/2019.
 */
class UserRepository(context: Context) {
    private val errorsLiveData = MutableLiveData<List<String>>()

    private val userService = APIClient.Single.getUserService()
    private val userDao = Hack4GoodDatabase.getInstance(context)?.userDao()

    fun getUser(): LiveData<User> {
        return userDao!!.getUser()
    }

    fun loginUser(username: String, password: String): LiveData<List<String>> {
        userService.loginUser(User(username, password)).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Log.d("Login User", response.toString())
                if(response!!.code() == 200){
                    errorsLiveData.postValue(listOf())

                    userService.getUser(username).enqueue(object : Callback<User> {
                        override fun onFailure(call: Call<User>?, t: Throwable?) {
                        }

                        override fun onResponse(call: Call<User>?, response: Response<User>?) {
                            val user = response!!.body()!!
                            user.password = password
                            registerUserInDB(user)
                        }

                    })
                }else
                    errorsLiveData.postValue(listOf("El usuario o la contraseña no son correctas"))
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.d("Login User", t?.message)
            }
        })

        return errorsLiveData
    }

    fun registerUser(user: User): LiveData<List<String>> {
        user.genre = user.genre[0].toString()
        userService.registerUser(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Log.d("Register User", response.toString())
                if (response!!.code() == 201) {
                    errorsLiveData.postValue(listOf())
                    registerUserInDB(user)
                } else
                    errorsLiveData.postValue(listOf("El registro ha fallado"))

            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.d("Register User", t?.message)
            }
        })

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