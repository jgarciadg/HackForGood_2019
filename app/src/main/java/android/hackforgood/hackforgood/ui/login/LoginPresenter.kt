package android.hackforgood.hackforgood.ui.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.hackforgood.hackforgood.ui.navigation_drawer.NavigationDrawerActivity
import android.hackforgood.hackforgood.ui.register.RegisterActivity
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 15/03/2019.
 */
class LoginPresenter(private val view: Login_MVP.View,
                     private val model: Login_MVP.Model) : Login_MVP.Presenter {

    private val lifecycleOwner = view as AppCompatActivity

    override fun viewLoaded() {
        model.getUser().observe(lifecycleOwner, Observer {
            if (it != null) {
                val intent = Intent(lifecycleOwner, NavigationDrawerActivity::class.java)
                lifecycleOwner.startActivity(intent)
            }
        })
    }

    override fun loginButtonClicked(username: String, password: String) {
        model.checkLoginIsCorrect(username, password).observe(lifecycleOwner, Observer {
            if (it!!.isNotEmpty())
                view.showErrors(it)
            else {
                model.loginUser(username, password).observe(lifecycleOwner, Observer {
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

    override fun registerButtonClicked() {
        val intent = Intent(lifecycleOwner, RegisterActivity::class.java)
        lifecycleOwner.startActivity(intent)
    }
}