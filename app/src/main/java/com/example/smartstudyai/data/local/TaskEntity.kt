package com.example.smartstudyai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a study task or to-do item.
 *
 * @property id Unique identifier for the task.
 * @property title Brief description of the task.
 * @property date Due date or scheduled date for the task.
 * @property isCompleted Status of task completion.
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val date: String,
    val isCompleted: Boolean = false
)