package com.nicoBir.babytracking.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Sleep::class],
    version = 1
)
abstract class SleepDatabase : RoomDatabase() {
    
    abstract fun getSleepDao(): SleepDao
    
    companion object {
        
        @Volatile
        private var instance: SleepDatabase? = null
        private val LOCK = Any()
        
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SleepDatabase::class.java,
            "sleepdatabase"
        ).build()
        
    }
}