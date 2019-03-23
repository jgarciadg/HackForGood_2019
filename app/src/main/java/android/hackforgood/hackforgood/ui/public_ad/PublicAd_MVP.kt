package android.hackforgood.hackforgood.ui.public_ad

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City

/**
 * Created by justo on 20/03/2019.
 */
interface PublicAd_MVP {
    interface View {
        fun showTimePickerDialog()
        fun showDatePickerDialog()
        fun showTimeGoPickerDialog()

        fun showErrors(errors: List<String>)

        fun setCitiesData(data: List<String>)
        fun setCenterData(data: List<String>)
    }

    interface Presenter {
        fun dateEditTextSelected()
        fun timeEditTextSelected()
        fun timeGoEditTextSelected()

        fun publicAdButtonSelected(string_time: String, string_date: String, string_time_go: String, max_people: String, citySelected: String, centerSelected: String)

        fun loadCities()
        fun loadCenters()
    }

    interface Model {
        fun checkErrors(hour: String, date: String, max_people: String): LiveData<List<String>>
        fun publicAd(ad: Ad)

        fun loadCities(): LiveData<List<City>>
        fun loadCenters(): LiveData<List<Center>>
    }
}