package android.hackforgood.hackforgood.ui.search_travel

import android.arch.lifecycle.LiveData

/**
 * Created by justo on 16/03/2019.
 */
interface SearchTravel_MVP {
    interface View {
        fun showTimePickerDialog()
        fun showDatePickerDialog()
        fun showErrors(errors: List<String>)
    }

    interface Presenter {
        fun timeEditTextSelected()
        fun dateEditTextSelected()
        fun searchButtonSelected(hour: String, date: String)
    }

    interface Model {
        fun checkErrors(hour: String, date: String): LiveData<List<String>>
    }
}