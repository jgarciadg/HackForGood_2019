package android.hackforgood.hackforgood.ui.public_ad

import android.arch.lifecycle.LiveData

/**
 * Created by justo on 20/03/2019.
 */
interface PublicAd_MVP {
    interface View {
        fun showTimePickerDialog()
        fun showDatePickerDialog()
        fun showErrors(errors: List<String>)
    }

    interface Presenter {
        fun dateEditTextSelected()
        fun timeEditTextSelected()
        fun publicAdButtonSelected(string_time: String, string_date: String, max_people: String)
    }

    interface Model {
        fun checkErrors(hour: String, date: String, max_people: String): LiveData<List<String>>
    }
}