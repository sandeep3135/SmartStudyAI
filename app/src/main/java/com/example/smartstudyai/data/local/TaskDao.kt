package com.example.smartstudyai.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the [TaskEntity].
 */
@Dao
interface TaskDao {
    /**
     * Retrieves all tasks, showing incomplete ones first, then by ID descending.
     */
    @Query("SELECT * FROM tasks ORDER BY isCompleted ASC, id DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    /**
     * Inserts or updates a task.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    /**
     * Updates the completion status of a specific task.
     */
    @Query("UPDATE tasks SET isCompleted = :isCompleted WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: Int, isCompleted: Boolean)

    /**
     * Deletes a task from the database.
     */
    @Delete
    suspend fun deleteTask(task: TaskEntity)
}