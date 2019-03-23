package android.hackforgood.hackforgood.ui.ad_detail

import android.arch.lifecycle.Observer
import android.hackforgood.hackforgood.data.model.User
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 22/03/2019.
 */
class AdDetailPresenter(private val view: AdDetail_MVP.View, private val model: AdDetail_MVP.Model) : AdDetail_MVP.Presenter {
    private var lifecycleOwner = view as AppCompatActivity
    private var users = mutableListOf<User>()

    override fun viewLoaded(idAd: Int) {
        model.getTravelsSolicited(idAd).observe(lifecycleOwner, Observer {
            if (it != null) {
                val travels = it
                it.forEach {
                    model.getUserById(it.idUser).observe(lifecycleOwner, Observer {
                        users.add(it!!)
                        if (users.size == travels.size)
                            view.setDataUsers(users)
                    })
                }

                view.setDataTravels(it)
            }
        })

        model.getCenters().observe(lifecycleOwner, Observer {
            if (it != null)
                view.setDataCenters(it)
        })

        model.getCities().observe(lifecycleOwner, Observer {
            if (it != null)
                view.setDataCities(it)
        })

    }

    override fun acceptTravel(idAd: Int, travelId: Int) {
        model.acceptPerson(idAd, travelId).observe(lifecycleOwner, Observer {
            view.showAcceptedPerson(travelId)
        })
    }

}