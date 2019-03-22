package android.hackforgood.hackforgood.ui.public_ad

import android.arch.lifecycle.Observer
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 20/03/2019.
 */
class PublicAdPresenter(private val view: PublicAd_MVP.View, private val model: PublicAd_MVP.Model) : PublicAd_MVP.Presenter {


    val lifecycleOwner = view as AppCompatActivity
    var cities = listOf<City>()
    var citiesString = mutableListOf<String>()
    var centers = listOf<Center>()
    var centersString = mutableListOf<String>()

    override fun publicAdButtonSelected(string_time: String, string_date: String, string_time_go: String, max_people: String, indexCity: Int, indexCenter: Int) {
        model.checkErrors(string_time, string_date, max_people).observe((view as AppCompatActivity), Observer {
            if (it!!.isNotEmpty())
                view.showErrors(it)
            else {
                val idCity = cities[indexCity].id
                val idCenter = centers[indexCenter].id

                val ad = Ad(idCity, idCenter, string_date, string_time_go, string_time, max_people.toInt())
                model.publicAd(ad)
            }
        })
    }

    override fun loadCities() {
        model.loadCities().observe(lifecycleOwner, Observer {
            cities = it!!

            citiesString = mutableListOf<String>()
            it.forEach {
                citiesString.add(it.name)
            }

            view.setCitiesData(citiesString)
        })
    }

    override fun loadCenters() {
        model.loadCenters().observe(lifecycleOwner, Observer {
            centers = it!!

            centersString = mutableListOf<String>()
            it.forEach {
                centersString.add(it.name)
            }

            view.setCenterData(centersString)
        })
    }

    override fun dateEditTextSelected() {
        view.showDatePickerDialog()
    }

    override fun timeEditTextSelected() {
        view.showTimePickerDialog()
    }

    override fun timeGoEditTextSelected() {
        view.showTimeGoPickerDialog()
    }
}