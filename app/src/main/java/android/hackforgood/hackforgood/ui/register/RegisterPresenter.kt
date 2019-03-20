package android.hackforgood.hackforgood.ui.register

import android.arch.lifecycle.Observer
import android.content.Intent
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.ui.navigation_drawer.NavigationDrawerActivity
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 15/03/2019.
 */
class RegisterPresenter(private val view: Register_MVP.View,
                        private val model: Register_MVP.Model) : Register_MVP.Presenter {

    private val lifecycleOwner = view as AppCompatActivity

    override fun registerButtonClicked(name: String, lastName: String, age: String, sex: String, username: String, password: String) {
        model.checkIfRegisterIsCorrect(name, lastName, age, sex, username, password)
                .observe(lifecycleOwner, Observer {
                    if (it!!.isNotEmpty())
                        view.showErrors(it)
                    else {
                        val user = User(username, password, name, lastName, age.toInt(), sex)
                        model.registerUser(user).observe(lifecycleOwner, Observer {

                            if (it!!.isNotEmpty())
                                view.showErrors(it)
                            else {
                                val intent = Intent(lifecycleOwner, NavigationDrawerActivity::class.java)
                                lifecycleOwner.startActivity(intent)
                                lifecycleOwner.finish()
                            }
                        })
                    }
                })
    }

}