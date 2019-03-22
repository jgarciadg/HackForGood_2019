package android.hackforgood.hackforgood.ui.see_own_ads

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 21/03/2019.
 */
class SeeOwnAdsPresenter(private val view: SeeOwnAds_MVP.View,
                         private val model: SeeOwnAds_MVP.Model) : SeeOwnAds_MVP.Presenter {
    override fun viewLoaded() {
        model.getAdds().observe((view as AppCompatActivity), Observer {
            view.setDataToShow(it!!)
        })

        model.getCenters().observe((view as AppCompatActivity), Observer {
            view.setDataCenter(it!!)
        })
    }

}