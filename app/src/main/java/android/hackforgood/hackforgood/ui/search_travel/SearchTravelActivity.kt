package android.hackforgood.hackforgood.ui.search_travel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.search_travel.SearchTravelModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_search_travel.*
import java.util.*


class SearchTravelActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, SearchTravel_MVP.View {
    private lateinit var presenter: SearchTravel_MVP.Presenter
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog

    private var string_date = ""
    private var string_time = ""
    private var idCenter = -1
    private var idLocalidad = -1

    private var centers: List<Center>? = null
    private var cities: List<City>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_travel)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        window.statusBarColor = resources.getColor(R.color.colorSecondary)

        setupMVP()

        setupDatePickerDialog()
        setupTimePickerDialog()

        editTextDate.setOnClickListener {
            presenter.dateEditTextSelected()
        }
        editTextDate.setKeyListener(null)

        editTextHour.setOnClickListener {
            presenter.timeEditTextSelected()
        }
        editTextHour.setKeyListener(null)

        buttonSearch.setOnClickListener {
            val city = cities!!.filter { city -> city.name == citySpinner.selectedItem.toString() }.single()
            val center  = centers!!.filter { center -> center.name == centerSpinner.selectedItem.toString() }.single()
            idLocalidad = city.id
            idCenter = center.id
            presenter.searchButtonSelected(string_time, string_date, idCenter, idLocalidad)
        }

        presenter.loadCities()
        presenter.loadCenters()
    }

    override fun setCentersArray(data: List<Center>) {
        centers = data
    }

    override fun setCitiesArray(data: List<City>) {
        cities = data
    }

    override fun setCitiesData(data: List<String>) {
        val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, data)
        citySpinner.setAdapter(adapter)
    }

    override fun setCenterData(data: List<String>) {
        val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, data)
        centerSpinner.setAdapter(adapter)
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
        editTextDate.setText(string_date)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        string_time = formatHour(hourOfDay, minute)
        editTextHour.setText(string_time)
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

    private fun setupMVP() {
        presenter = SearchTravelPresenter(this, SearchTravelModel())
    }

    private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
        val month_real = month + 1
        var string_month = month_real.toString()
        if (month_real < 10)
            string_month = "0$month_real"

        var string_day = dayOfMonth.toString()
        if (dayOfMonth < 10)
            string_day = "0$dayOfMonth"

        return "$year-$string_month-$string_day"
    }

    private fun formatHour(hourOfDay: Int, minute: Int): String {
        var string_hour = hourOfDay.toString()
        if (hourOfDay < 10)
            string_hour = "0$hourOfDay"

        var string_minute = minute.toString()
        if (minute < 10)
            string_minute = "0$minute"

        return "$string_hour:$string_minute:00"
    }
}
