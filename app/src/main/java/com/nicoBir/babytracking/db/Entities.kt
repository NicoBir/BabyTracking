package com.nicoBir.babytracking.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sleep(
        @ColumnInfo(name = "Time of sleep") val time: String,
        @ColumnInfo(name = "Length of sleep") val length: String,
        @ColumnInfo(name = "Quality of sleep") val quality: String,
        @ColumnInfo(name = "Lullaby") val lullaby: String
                ) {
    
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}