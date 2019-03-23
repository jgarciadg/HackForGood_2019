package android.hackforgood.hackforgood.ui.search_travel

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City

/**
 * Created by justo on 16/03/2019.
 */
interface SearchTravel_MVP {
    interface View {
        fun showTimePickerDialog()
        fun showDatePickerDialog()
        fun showErrors(errors: List<String>)
        fun setCentersArray(data: List<Center>)
        fun setCitiesArray(data: List<City>)
        fun setCitiesData(data: List<String>)
        fun setCenterData(data: List<String>)
    }

    interface Presenter {
        fun timeEditTextSelected()
        fun dateEditTextSelected()
        fun loadCities()
        fun loadCenters()
        fun searchButtonSelected(hour: String, date: String, idCenter: Int, idLocalidad: Int)
    }

    interface Model {
        fun checkErrors(hour: String, date: String): LiveData<List<String>>

        fun loadCities(): LiveData<List<City>>
        fun loadCenters(): LiveData<List<Center>>
    }
}