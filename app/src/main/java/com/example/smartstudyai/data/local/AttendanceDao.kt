package com.example.smartstudyai.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [AttendanceEntity].
 */
@Dao
interface AttendanceDao {
    /**
     * Retrieves attendance records for all subjects.
     */
    @Query("SELECT * FROM attendance")
    fun getAllAttendance(): Flow<List<AttendanceEntity>>

    /**
     * Inserts or updates an attendance record.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: AttendanceEntity)

    /**
     * Updates the class counts for a specific subject.
     */
    @Query("UPDATE attendance SET attendedClasses = :attended, totalClasses = :total WHERE id = :id")
    suspend fun updateAttendanceCount(id: Int, attended: Int, total: Int)

    /**
     * Deletes an attendance record.
     */
    @Delete
    suspend fun deleteAttendance(attendance: AttendanceEntity)
}