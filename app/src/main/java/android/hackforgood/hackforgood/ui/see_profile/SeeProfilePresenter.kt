package android.hackforgood.hackforgood.ui.see_profile

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 20/03/2019.
 */
class SeeProfilePresenter(private val view: SeeProfile_MVP.View,
                          private val model: SeeProfile_MVP.Model) : SeeProfile_MVP.Presenter {
    override fun getUserInfo() {
        model.getUser().observe((view as AppCompatActivity), Observer {
            view.showUserInfo(it!!)
        })
    }
}