package com.example.smartstudyai.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the [NoteEntity].
 * Handles all database operations related to notes.
 */
@Dao
interface NoteDao {
    /**
     * Retrieves all notes from the database, ordered by creation date descending.
     */
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    /**
     * Inserts a new note or replaces an existing one if there's a conflict.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    /**
     * Deletes a specific note from the database.
     */
    @Delete
    suspend fun deleteNote(note: NoteEntity)
}