package android.hackforgood.hackforgood.ui.see_ads_nearest

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 22/03/2019.
 */
class SeeAdsNearestPresenter(private val view: SeeAdsNearest_MVP.View,
                             private val model: SeeAdsNearest_MVP.Model) : SeeAdsNearest_MVP.Presenter {
    override fun viewLoaded(day: String, hour: String, idCenter: Int) {
        model.getAdsRecommended(day, hour, idCenter).observe((view as AppCompatActivity), Observer {
            view.setDataToShow(it!!)
        })

        model.getOthersAds(day, idCenter).observe((view as AppCompatActivity), Observer {
            view.setDataToShowOthers(it!!)
        })

        model.getCenters().observe((view as AppCompatActivity), Observer {
            view.setDataCenter(it!!)
        })
    }
}