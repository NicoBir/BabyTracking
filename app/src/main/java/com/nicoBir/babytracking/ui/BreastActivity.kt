package com.nicoBir.babytracking.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nicoBir.babytracking.R
import kotlinx.android.synthetic.main.breast_layout.*
import kotlinx.android.synthetic.main.sleep_edit_layout.*


class BreastActivity : AppCompatActivity() {
    
    
    private var breastHour: Int = 0
    private var breastMinute: Int = 0
    private var breastAmpm: String = "AM"
    private var breast: String = ""
    
    enum class BREAST(val breast: Int) {
        LEFT(R.string.left_breast), RIGHT(R.string.right_breast), UNKNOWN(R.string.unknown_breast)
    }
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.breast_layout)
        
        breastTimeClick()
        breastSelection()
        breastSave()
        
    }
    
    private fun breastTimeClick() {
        val timePicker = findViewById<TimePicker>(R.id.sleep_timepick_hour)
        timePicker.setOnTimeChangedListener { _, pickerHour, pickerMinute ->
            breastHour = pickerHour
            breastMinute = pickerMinute
            
            // AM_PM decider logic
            when {
                breastHour == 0 -> {
                    breastHour += 12
                    breastAmpm = "AM"
                }
                breastHour == 12 -> breastAmpm = "PM"
                breastHour > 12 -> {
                    breastHour -= 12
                    breastAmpm = "PM"
                }
                else -> breastAmpm = "AM"
            }
        }
    }
    
    private fun breastSelection() {
        
        val breastSpinnerAdapter = ArrayAdapter.createFromResource(
            this, R.array.array_breast,
            android.R.layout.simple_spinner_item
        )
        
        
        breastSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_breast.adapter = breastSpinnerAdapter
        
        spinner_breast.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View, pos: Int, id: Long
                ) {
                    val selection = parent.getItemAtPosition(pos) as String
                    if (! TextUtils.isEmpty(selection)) {
                        if (selection == getString(R.string.left_breast)) {
                            breast = getString(BREAST.LEFT.breast)
                        } else if (selection == getString(R.string.right_breast)) {
                            breast = getString(BREAST.RIGHT.breast)
                        } else {
                            breast = getString(BREAST.UNKNOWN.breast)
                        }
                    }
                }
                
                // Because AdapterView is an abstract class, onNothingSelected must be defined
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    breast = getString(BREAST.UNKNOWN.breast)
                }
            }
    }
    
    
    private fun breastSave() {
        
        val breastButton: Button = findViewById(R.id.breast_button_save)
        val lengthEdit: EditText = findViewById(R.id.breast_length)
        val babyEdit: EditText = findViewById(R.id.breast_baby)
        val motherEdit: EditText = findViewById(R.id.breast_mother)
        
        
        breastButton.setOnClickListener {
            val lengthText: String = lengthEdit.text.toString().trim()
            val lenghtInt: Int = Integer.parseInt(lengthText)
            val babyText = babyEdit.text.toString().trim()
            val motherText = motherEdit.text.toString().trim()
            val hourVal = if (breastHour < 10) "0" + breastHour else breastHour
            val minVal = if (breastMinute < 10) "0" + breastMinute else breastMinute
            val timeText = "$hourVal : $minVal $breastAmpm"
            
            if (lengthText.isEmpty()) {
                lengthEdit.error = "Length of breastfeeding required"
                lengthEdit.requestFocus()
                return@setOnClickListener
            } else if (lenghtInt <= 0) {
                lengthEdit.error = "Time cannot be negative or null"
                lengthEdit.requestFocus()
                return@setOnClickListener
            } else if (lenghtInt > 60) {
                lengthEdit.error = "Time cannot exceed 60 minutes"
                lengthEdit.requestFocus()
                return@setOnClickListener
            }
            
            if (babyText.isEmpty()) {
                babyEdit.error = "Quality for baby required"
                babyEdit.requestFocus()
                return@setOnClickListener
            }
            
            if (motherText.isEmpty()) {
                motherEdit.error = "Quality for mother required"
                motherEdit.requestFocus()
                return@setOnClickListener
            }
            
            
            Toast.makeText(
                this@BreastActivity,
                "Hour = $timeText, \n Length = $lengthText"
                + ", \n Breast = $breast, \n Session for baby = $babyText"
                + ", \n Session for mother = $motherText",
                Toast.LENGTH_SHORT
            ).show()
        }
        
    }
    
}
