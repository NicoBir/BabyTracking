package com.example.babytracking.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BabyDao {
    @Query("SELECT * FROM BabyUser")
    fun getAll(): List<BabyUser>

    @Query("SELECT * FROM BabyUser WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<BabyUser>

    @Query("SELECT * FROM BabyUser WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): BabyUser

    @Insert
    fun insertAll(vararg users: BabyUser)

    @Delete
    fun delete(user: BabyUser)
}