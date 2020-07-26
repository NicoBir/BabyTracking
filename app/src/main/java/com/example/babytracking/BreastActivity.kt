package com.example.babytracking

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.breast_layout.*
import kotlinx.android.synthetic.main.sleep_layout.*
import java.sql.Timestamp
import java.util.*

/*
This screen will allow to enter the data about the breastfeeding sessions of the baby.
The first data required is the date and time when the session occurred.
Those two are taken care of by the objects PickerSetup, which allow to pick the date and time,
and TimeFormatter, which format the time so that we can read it.
The other data that can be entered in this screen are the length of the session,
at which breast did the baby drink, as well as how the session was for the baby and for the mother.
*/
class BreastActivity : AppCompatActivity() {

    private var breast: String = ""
    private var cal: Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.breast_layout)

        PickerSetup.setupDatePicker(this, breast_Date, cal)
        PickerSetup.setupTimePicker(this, breast_Time, cal)
        setupBreastSpinner()

        breast_Save_Button.setOnClickListener {
            saveInput()
        }
    }

    private fun setupBreastSpinner() {

        breast_Spinner ?: return
        val breastSpinnerAdapter = ArrayAdapter.createFromResource(
            this, R.array.array_breast,
            android.R.layout.simple_spinner_item
        )

        breastSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        breast_Spinner.adapter = breastSpinnerAdapter


        breast_Spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View, pos: Int, id: Long
                ) {
                    if (!TextUtils.isEmpty(parent.getItemAtPosition(pos) as String)) {
                        breast = breast_Spinner.selectedItem.toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    breast = getString(R.string.unknown_breast)
                }
            }

    }


    @Suppress("DEPRECATION")
    @SuppressLint("NewApi")
    private fun saveInput() {

        val lengthText: String = breast_Length.text.toString().trim()

        if (lengthText.isEmpty()) {
            breast_Length.error = "Length of breastfeeding required"
            breast_Length.requestFocus()
            return
        }

        val lengthInt: Int = lengthText.toInt()
        val sessionQualForBabyText: String = breast_Quality_Baby.text.toString().trim()
        val sessionQualForMotherText: String = breast_Quality_Mother.text.toString().trim()

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

        if (sessionQualForBabyText.isEmpty()) {
            sleep_Quality.error = "Quality of the session for the baby required"
            sleep_Quality.requestFocus()
            return
        }

        if (sessionQualForMotherText.isEmpty()) {
            sleep_Quality.error = "Quality of the session for the mother required"
            sleep_Quality.requestFocus()
            return
        }


        val userInput =
            """
            TimeStamp = $timestamp
            Date = ${TimeFormatter.formatDate(date)}
            Hour = ${TimeFormatter.formatHour(timestamp.hours, timestamp.minutes)}
            Length = $lengthInt
            Breast = $breast
            Session for baby = $sessionQualForBabyText
            Session for mother = $sessionQualForMotherText
            """

        Toast.makeText(
            this@BreastActivity,
            userInput,
            Toast.LENGTH_SHORT
        ).show()
    }
}




