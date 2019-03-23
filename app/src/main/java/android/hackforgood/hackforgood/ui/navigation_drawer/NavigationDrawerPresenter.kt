package android.hackforgood.hackforgood.ui.navigation_drawer

import android.arch.lifecycle.Observer
import android.hackforgood.hackforgood.data.repository.local.Hack4GoodDatabase

/**
 * Created by justo on 21/03/2019.
 */
class NavigationDrawerPresenter(private val view: NavigationDrawerActivity) {
    private val userDao = Hack4GoodDatabase.getInstance(view)?.userDao()

    fun viewLoaded() {
        userDao!!.getUser().observe(view, Observer {
            if(it != null) {
                view.loadImageView("http://qss.unex.es:2072/user/photo/${it!!.photo}")
                view.loadName("${it.firstName} ${it.lastName}")
            }
        })
    }
}