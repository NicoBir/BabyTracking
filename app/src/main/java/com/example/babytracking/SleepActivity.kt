package com.example.babytracking

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sleep_layout.*
import java.sql.Timestamp
import java.util.*


/*
This screen will allow to enter the data about the sleeping schedule of the baby.
The first data required is the date and time when the session occurred.
Those two are taken care of by the objects PickerSetup, which allow to pick the date and time,
and TimeFormatter, which format the time so that we can read it.
The other data that can be entered in this screen are how long did the baby sleep,
how was their sleep and whether or not a lullaby was required to make them sleep.
*/

class SleepActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_layout)

        PickerSetup.setupDatePicker(this, sleep_Date, cal)
        PickerSetup.setupTimePicker(this, sleep_Time, cal)
        sleep_Save_Button.setOnClickListener {
            setupSaveButton()
        }
    }


    @Suppress("DEPRECATION")
    @SuppressLint("NewApi")
    private fun setupSaveButton() {

        val lengthText = sleep_Length.text.toString().trim()

        if (lengthText.isEmpty()) {
            sleep_Length.error = "Length of sleep required"
            sleep_Length.requestFocus()
            return}

        val lengthInt: Int = lengthText.toInt()
        val qualText: String = sleep_Quality.text.toString().trim()
        val lulText: String = if (sleep_Check_Lullaby.isChecked()) "Yes" else "No"

        val timestamp: Timestamp = TimeFormatter.makeTimeStamp(cal)
        val date = Date(timestamp.time)

         if (lengthInt <= 0) {
            sleep_Length.error = "Time cannot be negative or null"
            sleep_Length.requestFocus()
            return
        } else if (lengthInt > 24) {
            sleep_Length.error = "Time cannot exceed 24 hours"
            sleep_Length.requestFocus()
            return
        }

        if (qualText.isEmpty()) {
            sleep_Quality.error = "Quality of sleep required"
            sleep_Quality.requestFocus()
            return
        }

        val userInput =
            """
            TimeStamp = $timestamp
            Date = ${TimeFormatter.formatDate(date)}
            Hour = ${TimeFormatter.formatHour(timestamp.hours, timestamp.minutes)}
            Length = $lengthInt
            Quality of sleep = $qualText
            Lullaby? = $lulText
            """

        Toast.makeText(
            this@SleepActivity,
            userInput,
            Toast.LENGTH_SHORT
        ).show()
    }

}

