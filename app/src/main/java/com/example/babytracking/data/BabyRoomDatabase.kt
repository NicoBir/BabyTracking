package com.example.babytracking.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BabyUser::class), version = 1)
abstract class BabyRoomDatabase : RoomDatabase() {
    abstract fun userDao(): BabyDao
}