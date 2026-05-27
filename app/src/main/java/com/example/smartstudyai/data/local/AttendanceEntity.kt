package com.example.smartstudyai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tracks attendance for a specific subject.
 *
 * @property id Unique identifier.
 * @property subject Name of the academic subject.
 * @property totalClasses Total number of classes held.
 * @property attendedClasses Number of classes attended by the student.
 */
@Entity(tableName = "attendance")
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,
    val totalClasses: Int,
    val attendedClasses: Int
) {
    val percentage: Float
        get() = if (totalClasses > 0) (attendedClasses.toFloat() / totalClasses.toFloat()) * 100f else 0f
}