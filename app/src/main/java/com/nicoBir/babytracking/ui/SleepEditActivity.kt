package com.nicoBir.babytracking.ui


import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.nicoBir.babytracking.R
import com.nicoBir.babytracking.db.Sleep
import com.nicoBir.babytracking.db.SleepDatabase
import kotlinx.android.synthetic.main.sleep_edit_layout.*


class SleepEditActivity : AppCompatActivity() {
    
    private var sleepHour: Int = 0
    private var sleepMinute: Int = 0
    private var sleepAmpm: String = "AM"
    
    
   
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_edit_layout)
        
        sleepTimeClick()
        sleepButtonSave()
        
    }
    
    
    
    private fun sleepButtonSave() {
        sleep_button_save.setOnClickListener {
            
            val hourVal = if (sleepHour < 10) "0" + sleepHour else sleepHour
            val minVal = if (sleepMinute < 10) "0" + sleepMinute else sleepMinute
            val timeText = "$hourVal : $minVal $sleepAmpm"
            val lengthText: String = sleep_length.text.toString().trim()
            val lenghtInt: Int = Integer.parseInt(lengthText)
            val qualText: String = sleep_quality.text.toString().trim()
            val lulText: String = if (sleep_check_lullaby.isChecked()) "Yes" else "No"
    
            
            
            
            if (lengthText.isEmpty()) {
                sleep_length.error = "Length of sleep required"
                sleep_length.requestFocus()
                return@setOnClickListener
            } else if (lenghtInt <= 0){
                sleep_length.error = "Time cannot be negative or null"
                sleep_length.requestFocus()
                return@setOnClickListener
            } else if (lenghtInt > 24){
                sleep_length.error = "Time cannot exceed 24 hours"
                sleep_length.requestFocus()
                return@setOnClickListener
            }
            
            if (qualText.isEmpty()) {
                sleep_quality.error = "Quality of sleep required"
                sleep_quality.requestFocus()
                return@setOnClickListener
            }
            
            val sleep = Sleep(timeText, lengthText, qualText, lulText)
            
            class SaveSleep : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg params: Void?): Void? {
                    SleepDatabase(this@SleepEditActivity).getSleepDao().insertSleep(sleep)
                    return null
                }
                
                override fun onPostExecute(result: Void?) {
                    super.onPostExecute(result)
                    
                    Toast.makeText(this@SleepEditActivity, "Sleep Data Saved", Toast.LENGTH_LONG)
                        .show()
                }
            }
            SaveSleep().execute()
        }
        
    }
    
    private fun sleepTimeClick() {
        val timePicker = findViewById<TimePicker>(R.id.sleep_timepick_hour)
        timePicker.setOnTimeChangedListener { _, pickerHour, pickerMinute ->
            sleepHour = pickerHour
            sleepMinute = pickerMinute
            
            // AM_PM decider logic
            when {
                sleepHour == 0 -> {
                    sleepHour += 12
                    sleepAmpm = "AM"
                }
                sleepHour == 12 -> sleepAmpm = "PM"
                sleepHour > 12 -> {
                    sleepHour -= 12
                    sleepAmpm = "PM"
                }
                else -> sleepAmpm = "AM"
            }
        }
    }
    
}
