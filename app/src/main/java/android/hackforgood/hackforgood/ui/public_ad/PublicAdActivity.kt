package android.hackforgood.hackforgood.ui.public_ad

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.public_ad.PublicAdModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_public_ad.*
import java.util.*

class PublicAdActivity : AppCompatActivity(), PublicAd_MVP.View, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var presenter: PublicAd_MVP.Presenter
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog

    private var string_date = ""
    private var string_time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_ad)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        setupMVP()

        setupDatePickerDialog()
        setupTimePickerDialog()

        dateEditText.setOnClickListener {
            presenter.dateEditTextSelected()
        }
        dateEditText.setKeyListener(null)

        timeEditText.setOnClickListener {
            presenter.timeEditTextSelected()
        }
        timeEditText.setKeyListener(null)

        buttonPublic.setOnClickListener {
            val max_people = maxPeopleEditText.text.toString()
            presenter.publicAdButtonSelected(string_time, string_date, max_people)
        }
    }

    override fun showTimePickerDialog() {
        timePickerDialog.show()
    }

    override fun showDatePickerDialog() {
        datePickerDialog.show()
    }

    override fun showErrors(errors: List<String>) {
        errorTextView.setText(errors[0])
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        string_date = formatDate(year, month, dayOfMonth)
        dateEditText.setText(string_date)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        string_time = formatHour(hourOfDay, minute)
        timeEditText.setText(string_time)
    }

    private fun setupDatePickerDialog() {
        val date = Date()
        val calendar = GregorianCalendar()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        datePickerDialog = DatePickerDialog(this, 0, this, year, month, day)
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
    }

    private fun setupTimePickerDialog() {
        val date = Date()
        val calendar = GregorianCalendar()
        calendar.time = date
        val hour = calendar.get(Calendar.MONTH)
        val minute = calendar.get(Calendar.MINUTE)
        timePickerDialog = TimePickerDialog(this, 0, this, hour, minute, true)
    }


    private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
        val month_real = month + 1
        var string_month = month_real.toString()
        if (month_real < 10)
            string_month = "0$month_real"

        var string_day = dayOfMonth.toString()
        if (dayOfMonth < 10)
            string_day = "0$dayOfMonth"

        return "$string_day/$string_month/$year"
    }

    private fun formatHour(hourOfDay: Int, minute: Int): String {
        var string_hour = hourOfDay.toString()
        if (hourOfDay < 10)
            string_hour = "0$hourOfDay"

        var string_minute = minute.toString()
        if (minute < 10)
            string_minute = "0$minute"

        return "$string_hour:$string_minute"
    }

    private fun setupMVP() {
        presenter = PublicAdPresenter(this, PublicAdModel())
    }
}