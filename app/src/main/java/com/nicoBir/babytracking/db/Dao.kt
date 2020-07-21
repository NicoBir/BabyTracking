package com.nicoBir.babytracking.db

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SleepDao {
    
    @Insert
    fun insertSleep(sleep: Sleep)
    
    @Insert
    fun insertAll(vararg sleeps: Sleep)
    
    @Delete
    fun delete(sleep: Sleep)
    
    @Query("SELECT * FROM sleep ORDER BY id DESC")
    fun getSleep(): List<Sleep>
    
}