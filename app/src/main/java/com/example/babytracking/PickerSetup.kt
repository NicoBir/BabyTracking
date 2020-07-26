package com.example.babytracking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.TextView
import com.example.babytracking.TimeFormatter
import kotlinx.android.synthetic.main.sleep_layout.*
import java.util.*

/*
This object allows the creation of date and time pickers.
*/
object PickerSetup {

     fun setupDatePicker(context: Context, date_TextView: TextView, cal: Calendar) {

        date_TextView!!.text = "--/--/----"

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, yearDate, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, yearDate)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                date_TextView!!.text = TimeFormatter.formatDate(cal.time)
            }

        date_TextView!!.setOnClickListener {
            DatePickerDialog(
                context,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

     fun setupTimePicker(context: Context, time_TextView: TextView, cal: Calendar) {

        time_TextView!!.text = "--:-- --"

        val timeSetListener =
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minuteOfHour ->

                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minuteOfHour)
                time_TextView.text = TimeFormatter.formatHour(hourOfDay, minuteOfHour)
            }

        time_TextView!!.setOnClickListener {
            TimePickerDialog(
                context,
                timeSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                false
            ).show()
        }
    }

}
