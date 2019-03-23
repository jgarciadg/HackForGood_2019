package android.hackforgood.hackforgood.ui.search_travel

import android.arch.lifecycle.Observer
import android.content.Intent
import android.hackforgood.hackforgood.ui.see_ads_nearest.SeeAdsNearestActivity
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 16/03/2019.
 */
class SearchTravelPresenter(private val view: SearchTravel_MVP.View, private val model: SearchTravel_MVP.Model) : SearchTravel_MVP.Presenter {
    val lifecycleOwner = view as AppCompatActivity

    override fun searchButtonSelected(hour: String, date: String, idCenter: Int, idLocalidad: Int) {
        model.checkErrors(hour, date).observe(lifecycleOwner, Observer {
            if (it!!.isNotEmpty())
                view.showErrors(it)
            else {
                val intent = Intent(lifecycleOwner, SeeAdsNearestActivity::class.java)
                intent.putExtra("hour", hour)
                intent.putExtra("day", date)
                intent.putExtra("idCenter", idCenter)
                intent.putExtra("idLocalidad", idLocalidad)
                lifecycleOwner.startActivity(intent)
            }
        })
    }

    override fun loadCities() {
        model.loadCities().observe(lifecycleOwner, Observer {
            val dataString = mutableListOf<String>()
            it!!.forEach {
                dataString.add(it.name)
            }

            view.setCitiesData(dataString)
            view.setCitiesArray(it)
        })
    }

    override fun loadCenters() {
        model.loadCenters().observe(lifecycleOwner, Observer {
            val dataString = mutableListOf<String>()
            it!!.forEach {
                dataString.add(it.name)
            }

            view.setCenterData(dataString)
            view.setCentersArray(it)
        })
    }

    override fun timeEditTextSelected() {
        view.showTimePickerDialog()
    }

    override fun dateEditTextSelected() {
        view.showDatePickerDialog()
    }
}