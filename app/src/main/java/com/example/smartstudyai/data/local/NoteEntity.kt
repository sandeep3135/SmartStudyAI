package com.example.smartstudyai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a study note in the database.
 *
 * @property id Unique identifier for the note.
 * @property title The title of the note.
 * @property content The main body text of the note.
 * @property subject The academic subject associated with the note.
 * @property createdAt Timestamp when the note was created.
 */
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val subject: String,
    val createdAt: Long = System.currentTimeMillis()
)