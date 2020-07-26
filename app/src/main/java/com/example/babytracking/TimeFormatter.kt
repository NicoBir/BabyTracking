package com.example.babytracking

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
/*
This object allows the formatting of the different time variables so that they can be accessed and read.
*/
object TimeFormatter {
    @SuppressLint("NewApi")
    fun formatDate(dateToFormat: Date): String? {

        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(dateToFormat)
    }


    fun formatHour(hourInput: Int, minuteInput: Int): String {
        var hour: Int = hourInput
        val minute: Int = minuteInput
        var ampm: String = "AM"
        when {
            hour == 0 -> {
                hour += 12
                ampm = "AM"
            }
            hour == 12 -> ampm = "PM"
            hour > 12 -> {
                hour -= 12
                ampm = "PM"
            }
            else -> ampm = "AM"
        }

        val hourFormat: String = if (hour < 10) "0" + hour else "$hour"
        val minuteFormat: String = if (minute < 10) "0" + minute else "$minute"
        val formatText: String = "$hourFormat:$minuteFormat $ampm"

        return formatText
    }

    @SuppressLint("NewApi")
    fun makeTimeStamp(cal: Calendar): Timestamp {
        val localDateTime = LocalDateTime.of(
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH) + 1,
            cal.get(Calendar.DAY_OF_MONTH),
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE)
        )
        val zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
        val timestamp = Timestamp(zonedDateTime.toInstant().toEpochMilli())

        return timestamp
    }

}
