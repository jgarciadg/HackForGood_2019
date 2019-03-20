package android.hackforgood.hackforgood.ui.public_ad

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity

/**
 * Created by justo on 20/03/2019.
 */
class PublicAdPresenter(private val view: PublicAd_MVP.View, private val model: PublicAd_MVP.Model) : PublicAd_MVP.Presenter {

    override fun publicAdButtonSelected(string_time: String, string_date: String, max_people: String) {
        model.checkErrors(string_time, string_date, max_people).observe((view as AppCompatActivity), Observer {
            if (it!!.isNotEmpty())
                view.showErrors(it)
            else {
                //Do public
            }
        })
    }

    override fun dateEditTextSelected() {
        view.showDatePickerDialog()
    }

    override fun timeEditTextSelected() {
        view.showTimePickerDialog()
    }
}