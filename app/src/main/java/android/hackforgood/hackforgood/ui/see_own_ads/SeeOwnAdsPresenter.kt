package android.hackforgood.hackforgood.ui.see_own_ads

import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 21/03/2019.
 */
class SeeOwnAdsPresenter(private val view: SeeOwnAds_MVP.View,
                         private val model: SeeOwnAds_MVP.Model) : SeeOwnAds_MVP.Presenter {
    override fun viewLoaded() {
        try {
            if(view is Fragment)
                model.getAdds().observe((view as Fragment), Observer {
                    view.setDataToShow(it)
                })
        } catch (e: Exception) {
            model.getAdds().observe((view as AppCompatActivity), Observer {
                view.setDataToShow(it)
            })
        }

        try {
            if(view is Fragment)
                model.getCenters().observe((view as Fragment), Observer {
                    view.setDataCenter(it!!)
                })
        } catch (e: Exception) {
            model.getCenters().observe((view as AppCompatActivity), Observer {
                view.setDataCenter(it!!)
            })
        }

        model.getCities().observe((view as Fragment), Observer {
            view.setDataCity(it!!)
        })
    }

}