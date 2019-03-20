package android.hackforgood.hackforgood.ui.search_travel

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 16/03/2019.
 */
class SearchTravelPresenter(private val view: SearchTravel_MVP.View, private val model: SearchTravel_MVP.Model) : SearchTravel_MVP.Presenter {
    override fun searchButtonSelected(hour: String, date: String) {
        model.checkErrors(hour, date).observe((view as AppCompatActivity), Observer {
            if (it!!.isNotEmpty())
                view.showErrors(it)
            else {
                //Do Search
            }
        })
    }

    override fun timeEditTextSelected() {
        view.showTimePickerDialog()
    }

    override fun dateEditTextSelected() {
        view.showDatePickerDialog()
    }
}