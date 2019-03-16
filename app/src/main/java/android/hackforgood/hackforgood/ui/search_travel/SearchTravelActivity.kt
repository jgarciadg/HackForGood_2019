package android.hackforgood.hackforgood.ui.search_travel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.hackforgood.hackforgood.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search_travel.*
import java.util.*


@Suppress("DEPRECATION")
class SearchTravelActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_travel)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        setupDatePickerDialog()
        setupTimePickerDialog()

        editTextDate.setOnClickListener {
            datePickerDialog.show()
        }
        editTextDate.setKeyListener(null)

        editTextHour.setOnClickListener {
            timePickerDialog.show()
        }
        editTextHour.setKeyListener(null)

        buttonSearch.setOnClickListener {
            Toast.makeText(this, "Enviando", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        editTextDate.setText("$dayOfMonth/${month + 1}/$year")
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        editTextHour.setText("$hourOfDay:$minute")
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
}
