package android.hackforgood.hackforgood.ui.see_requests

import android.arch.lifecycle.Observer
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Travel
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 22/03/2019.
 */
class SeeRequestsPresenter(private val view: SeeRequests_MVP.View,
                           private val model: SeeRequests_MVP.Model) : SeeRequests_MVP.Presenter {
    private val lifecycleOwner = (view as AppCompatActivity)
    private val ads = mutableListOf<Ad>()

    override fun viewLoaded() {
        model.getTravels().observe(lifecycleOwner, Observer {
            if (it != null) {
                view.setDataTravels(it!!)
                val travels = it

                for (travel: Travel in it) {
                    model.getAd(travel.idAd).observe(lifecycleOwner, Observer {
                        ads.add(it!!)

                        if (ads.size == travels.size)
                            view.setDataAds(ads)
                    })
                }
            }
        })

        model.getCenters().observe(lifecycleOwner, Observer {
            view.setDataCenter(it!!)
        })

        model.getCities().observe(lifecycleOwner, Observer {
            view.setDataCities(it!!)
        })

    }
}