package android.hackforgood.hackforgood.ui.see_ads_nearest

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 22/03/2019.
 */
class SeeAdsNearestPresenter(private val view: SeeAdsNearest_MVP.View,
                             private val model: SeeAdsNearest_MVP.Model) : SeeAdsNearest_MVP.Presenter {

    override fun requestTravelButtonClicked(idAd: Int, hour: String) {
        model.requestTravel(hour, idAd).observe((view as AppCompatActivity), Observer {
            if(it!!)
                view.showErrorInRequestAd()
            else
                view.showAdRequested()
        })
    }

    override fun viewLoaded(day: String, hour: String, idCenter: Int, idLocalidad: Int) {
        model.getAdsRecommended(day, hour, idCenter, idLocalidad).observe((view as AppCompatActivity), Observer {
            view.setDataToShow(it!!)
        })

        model.getOthersAds(day, idCenter, idLocalidad).observe((view as AppCompatActivity), Observer {
            view.setDataToShowOthers(it!!)
        })

        model.getCenters().observe((view as AppCompatActivity), Observer {
            view.setDataCenter(it!!)
        })

        model.getCities().observe((view as AppCompatActivity), Observer {
            view.setDataCities(it!!)
        })
    }
}