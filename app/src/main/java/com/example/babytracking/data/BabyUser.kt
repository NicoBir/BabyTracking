package com.example.babytracking.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BabyUser(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @NonNull @ColumnInfo(name = "first_name") val firstName: String?,
    @NonNull @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "date_of_birth") val birth: String?
)